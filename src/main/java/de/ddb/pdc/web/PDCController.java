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
import de.ddb.pdc.storage.StorageModel;
import de.ddb.pdc.storage.StorageService;

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
   * TODO catch exceptions here to return an appropriate response status to the
   * client.
   *
   * @param itemId DDB item ID
   * @return PDCResult serialized to standard JSON
   */
  @RequestMapping("/pdc/{itemId}")
  public PDCResult determinePublicDomain(@PathVariable String itemId) throws Exception {
    
    final PDCResult pdcResult;
    
    // fetch the requested item from storage
    StorageModel fetchedRecord = storageService.fetch(itemId);
    // if the item was found then return the requested information
    if (fetchedRecord != null) {      
      pdcResult = new PDCResult(
          fetchedRecord.isPublicDomain(), fetchedRecord.getTrace()
      );
    } else {
      // create a dbbItem for the requested itemId, populate the dbbItem
      DDBItem ddbItem = metaFetcher.fetchMetadata(itemId);
      
      // provide the meta data to the answerer service and get the result
      pdcResult = this.calculator.calculate(this.country, ddbItem);
      
      // add the result to storage, including related metadata
      // FIXME currently uses the metadata category, not pdcResult category as
      // the latter does not exist yet.
      // TODO add null checks before storing
      StorageModel newRecord = new StorageModel(
          itemId, ddbItem.getCategory(), ddbItem.getInstitution(),
          pdcResult.isPublicDomain(), pdcResult.getTrace()
      );
      storageService.store(newRecord);      
    }    
    return pdcResult;
  }
}
