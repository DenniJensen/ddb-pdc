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

  @Bean
  public MetaFetcher metaFetcher() {
    RestTemplate template = new RestTemplate();
    return new MetaFetcherImpl(template, ddbApiKey);
  }


  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }
}
