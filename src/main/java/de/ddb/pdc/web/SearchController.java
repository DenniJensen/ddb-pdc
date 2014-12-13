package de.ddb.pdc.web;

import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.metadata.DDBItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides an HTTP interface for DDB item search .
 */
@RestController
public class SearchController {
  private MetaFetcher metaFetcher;

  /**
   * Creates a SearchController.
   *
   * @param metaFetcher {@link MetaFetcher} to use for DBB API calls
   */
  @Autowired
  public SearchController(MetaFetcher metaFetcher) {
    this.metaFetcher = metaFetcher;
  }

  /**
   * Searches the DDB and returns the found items as a JSON array of
   * item objects. Requests must be of the form
   *
   *   GET /search?q=QUERY&max=NUM
   *
   * where QUERY is the string to search for (e.g. part of a title)
   * and NUM the maximum number of items to return.
   *
   * @param query      string to search for in item metadata
   * @param maxResults maximum number of matching items to return
   *                   (must be larger than 0)
   * @return           found items
   */
  @RequestMapping("/search")
  public DDBItem[] search(
      @RequestParam(value = "q", required = true) String query,
      @RequestParam(value = "max", required = true) int maxResults) {
    return metaFetcher.searchForItems(query, 0, maxResults, "relevance");
  }
}
