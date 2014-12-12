package de.ddb.pdc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.metadata.MetaFetcherImpl;

/**
 * The main and configuration class.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Main {

  @Value("${ddb.apikey}")
  private String ddbApiKey;

  /**
   * The Spring RestTemplate instance which is used for communicating
   * with the DDB API. It is injected as a bean to make it mockable
   * in integration tests.
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  /**
   * The singleton {@link MetaFetcher} instance.
   */
  @Bean
  public MetaFetcher metaFetcher(RestTemplate restTemplate) {
    return new MetaFetcherImpl(restTemplate, ddbApiKey);
  }

  /**
   * The main method.
   *
   * @param args command-line arguments (interpreted by Spring Boot)
   */
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }
}
