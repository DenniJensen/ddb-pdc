package de.ddb.pdc;

import de.ddb.pdc.answerer.AnswererService;
import de.ddb.pdc.answerer.AnswererServiceImpl;
import de.ddb.pdc.metadata.DDBItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main and configuration class.
 */
@EnableAutoConfiguration
@ComponentScan
public class Main implements CommandLineRunner {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  /**
   * 
   * @param args
   * @throws Exception 
   */
  @Override
  public void run(String... args) throws Exception {
    AnswererService answererService = new AnswererServiceImpl();
    
    // test data
    DDBItem testItem = new DDBItem(
        "I am a fairy", "LITERARY_OR_ARTISTIC_WORK", "Austria", 1920, 1956,
        "Göthe", 1880, 1940, "Estonia"
    );
    
    answererService.getResult("de", testItem);
  }
}
