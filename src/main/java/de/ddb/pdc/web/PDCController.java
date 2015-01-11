package de.ddb.pdc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ddb.pdc.core.PublicDomainCalculator;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.storage.StorageService;
import java.util.TimeZone;
import java.util.Calendar;

/**
 * Provides an HTTP interface for public domain calculation.
 */
@RestController
public class PDCController {

  @Value("${ddb.country:de}")
  private String country;

  private final PublicDomainCalculator calculator;
  private final MetaFetcher metaFetcher;
  private final StorageService storageService;
  private final boolean storageEnabled;

  /**
   * Creates a PDCController.
   *
   * @param metaFetcher {@link MetaFetcher} to use for DBB API calls
   * @param calculator  to decide the public domain problem on an item for
   *                    a given country
   * @param storageService to access previously calculated item information
   * @param storageEnabledProperty  to control whether the storage service will
   *                                be used
   */
  @Autowired
  public PDCController(MetaFetcher metaFetcher,
      PublicDomainCalculator calculator, StorageService storageService,
      @Value("${ddb.storage.enable:true}") String storageEnabledProperty) {

    this.metaFetcher = metaFetcher;
    this.calculator = calculator;
    this.storageService = storageService;
    this.storageEnabled = Boolean.parseBoolean(storageEnabledProperty);
  }

  /**
   * Retrieves or calculates a public-domain evaluation of a DDB item.
   * Requests must be of the form
   *
   *   GET /pdc/ITEM_ID
   *
   * where ITEM_ID is the ID of an item in the DDB database. The result is
   * returned as a JSON object with the following attributes:
   *
   *   publicDomain - true the item is considered public-domain by the
   *                  calculator, or false if not.
   *
   *   trace        - An array of the questions asked and their answers.
   *                  Each question is represented as a JSON object with
   *                  two attributes - "question", which contains the
   *                  full text of the question, and "answer", which
   *                  is true if the answer was answered with "yes" and
   *                  false if the answer was "no".
   *
   * @param itemId DDB item ID
   * @return PDCResult serialized to standard JSON
   */
  @RequestMapping("/pdc/{itemId}")
  public PDCResult determinePublicDomain(@PathVariable String itemId)
      throws Exception {

    final PDCResult pdcResult;

    if (storageEnabled) {
      pdcResult = determineWithStorageService(itemId);
    } else {
      pdcResult = determineWithoutStorageService(itemId);
    }
    return pdcResult;
  }

  /**
   * Retrieves or calculates a public-domain evaluation of a DDB item by first
   * using the {@link StorageService} to fetch a stored and up to date
   * evaluation result if it exists.
   * If the evaluation result does not exist in storage then the evaluation
   * is calculated and stored. If the result exists but it is outdated, then
   * the evaluation is calculated and the result is updated.
   *
   */
  private PDCResult determineWithStorageService(String itemId) {
    PDCResult pdcResult;
    PDCResult fetchedResult = storageService.fetch(itemId);

    if (fetchedResult != null) {
      if (isOutdated(fetchedResult)) {
        DDBItem ddbItem = metaFetcher.fetchMetadata(itemId);
        pdcResult = this.calculator.calculate(this.country, ddbItem);
        storageService.update(pdcResult);
      } else {
        pdcResult = fetchedResult;
      }
    } else {
      DDBItem ddbItem = metaFetcher.fetchMetadata(itemId);
      pdcResult = this.calculator.calculate(this.country, ddbItem);
      storageService.store(pdcResult);
    }
    return pdcResult;
  }

  /**
   * Determines whether a {@link PDCResult} is outdated.
   * This is the case if both of the following conditions are true:
   * 1) the DDB item is not part of the public domain
   * 2) the year of the current request is greater than the year at which the
   * public-domain result was calculated and stored
   *
   */
  private boolean isOutdated(PDCResult fetchedResult) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    int requestYear = calendar.get(Calendar.YEAR);
    calendar.setTime(fetchedResult.getCreatedDate());
    int recordCreationYear = calendar.get(Calendar.YEAR);

    return ((requestYear > recordCreationYear)
        && (! fetchedResult.isPublicDomain()));
  }

  /**
   * Retrieves or calculates a public-domain evaluation of a DDB item without
   * using the {@link StorageService}.
   *
   */
  private PDCResult determineWithoutStorageService(String itemId) {
    DDBItem ddbItem = metaFetcher.fetchMetadata(itemId);
    PDCResult pdcResult = this.calculator.calculate(this.country, ddbItem);
    return pdcResult;
  }

}
