package de.ddb.pdc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ddb.pdc.answerer.AnswererService;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;

/**
 * Provides an HTTP interface for public domain calculation.
 */
@RestController
public class PDCController {

  @Value("${ddb.country}")
  private String country;

  private final AnswererService answererService;
  private final MetaFetcher metaFetcher;

  /**
   * Creates a PDCController.
   *
   * @param metaFetcher     {@link MetaFetcher} to use for DBB API calls
   * @param answererService to decide the public domain problem on an item for
   *                        a given country
   */
  @Autowired
  public PDCController(MetaFetcher metaFetcher,
      AnswererService answererService) {
    this.metaFetcher = metaFetcher;
    this.answererService = answererService;
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
   * @return PDCResult as JSON
   */
  @RequestMapping("/pdc/{itemId}")
  public String calculate(@PathVariable String itemId) throws Exception {

    // create a dbbItem for the requested itemId, populate the dbbItem
    DDBItem ddbItem = metaFetcher.fetchMetadata(itemId);

    // provide the meta data to the answerer service and get the result
    PDCResult pdcResult = this.answererService.getResult(this.country,ddbItem);

    return pdcResult.toJSONString();
  }
}
