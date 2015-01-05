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

  /**
   * Creates a PDCController.
   *
   * @param metaFetcher     {@link MetaFetcher} to use for DBB API calls
   * @param calculator to decide the public domain problem on an item for
   *                        a given country
   * @param storageService to access previously calculated item information
   */
  @Autowired
  public PDCController(MetaFetcher metaFetcher,
      PublicDomainCalculator calculator, StorageService storageService) {
    this.metaFetcher = metaFetcher;
    this.calculator = calculator;
    this.storageService = storageService;
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
   * A retrieved PDC result is re-calculated if both of the following
   * conditions are true:
   * 1) the DDB item is not part of the public domain
   * 2) the year of the current request is greater than the year at which the
   * public-domain result was calculated and stored
   *
   * @param itemId DDB item ID
   * @return PDCResult serialized to standard JSON
   */
  @RequestMapping("/pdc/{itemId}")
  public PDCResult determinePublicDomain(@PathVariable String itemId)
      throws Exception {

    PDCResult pdcResult = null;

    PDCResult fetchedResult = storageService.fetch(itemId);

    if (fetchedResult != null) {
      Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      int requestYear = calendar.get(Calendar.YEAR);
      calendar.setTime(fetchedResult.getCreatedDate());
      int recordCreationYear = calendar.get(Calendar.YEAR);

      if ((requestYear > recordCreationYear)
          && (! fetchedResult.isPublicDomain())) {
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
}
