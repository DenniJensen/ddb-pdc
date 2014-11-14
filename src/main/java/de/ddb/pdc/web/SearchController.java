package de.ddb.pdc.web;

import de.ddb.pdc.core.PublicDomainCalculatorFactory;
import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.metadata.DDBItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
  private MetaFetcher metaFetcher;

  @Autowired
  public SearchController(MetaFetcher metaFetcher) {
    this.metaFetcher = metaFetcher;
  }

  @RequestMapping("/search")
  public DDBItem[] search(
      @RequestParam(value = "q", required = true) String query,
      @RequestParam(value = "max", required = true) int maxResults) {
    return metaFetcher.searchForItems(query, maxResults);
  }
}
