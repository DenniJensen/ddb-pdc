package de.ddb.pdc.web;

import de.ddb.pdc.core.PublicDomainCalculatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides the REST endpoint for requesting public domain calculations.
 */
@RestController
public class PDCController {
  private PublicDomainCalculatorFactory calculatorFactory;

  @Autowired
  public PDCController(
      PublicDomainCalculatorFactory calculatorFactory) {
    this.calculatorFactory = calculatorFactory;
  }

  @RequestMapping("/pdc/{itemId}")
  public String calculate(@PathVariable String itemId) {
    // ...
    return "dummy";
  }
}
