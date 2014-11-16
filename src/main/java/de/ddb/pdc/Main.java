package de.ddb.pdc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import de.ddb.pdc.answerer.AnswererService;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.metadata.MetaFetcherImpl;

/**
 * The main and configuration class.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Main implements CommandLineRunner {

  @Value("${ddb.apikey}")
  private String ddbApiKey;

  @Autowired
  private AnswererService answererService;

  @Bean
  public MetaFetcher metaFetcher() {
    RestTemplate template = new RestTemplate();
    return new MetaFetcherImpl(template, this.ddbApiKey);
  }


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

    // test data
    DDBItem testItem =
        new DDBItem("I am a fairy", "LITERARY_OR_ARTISTIC_WORK", "Austria",
            1920, 1956, "Göthe", 1880, 1940, "Estonia");


    PDCResult result = this.answererService.getResult("de", testItem);
    System.out.println("is public domain: " + result.isPublicDomain());
  }
}
