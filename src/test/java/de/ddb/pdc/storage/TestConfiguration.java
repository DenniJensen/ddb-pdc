package de.ddb.pdc.storage;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
* Configuration for creating the required Beans needed by the testing class.
* Environment configurations are loaded from a designated test file.
*/
@Configuration
@ComponentScan
@PropertySource("file:config/test.application.properties")
public class TestConfiguration {

  @Value("${spring.data.mongodb.host:127.0.0.1}")
  private String hostIp;

  @Value("${spring.data.mongodb.port:27017}")
  private int hostPort;

  @Value("${spring.data.mongodb.database:pdcTest}")
  private String database;

  @Value("${spring.data.mongodb.collection:pdcDataTest}")
  private String collectionName;

  @Bean
  public MongoTemplate mongoTemplate() throws UnknownHostException {
    return new MongoTemplate(new MongoClient(hostIp, hostPort), database);
  }

  @Bean
  public StorageService storageService() throws UnknownHostException {
    return new MongoStorageService(mongoTemplate(), collectionName);
  }

  /**
   * Refer to Spring documentation for Annotation Type PropertySource.
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer
      propertyPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

}
