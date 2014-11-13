/** 
 * This is not a unit test, this is a real test. :D
 * Tests the store and fetch methods 
 * Test behaviour is specified by the included Configuration class and
 * environment properties are loaded from a designated test file.
 * TODO Make the Configuration class available to other test classes.
 * TODO finalize the test case for store and fetch.
 * TODO Implement the remaining test case for the update method.
 */
package de.ddb.pdc.storage;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * AnnotationConfigContextLoader loads bean definitions from annotated classes.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class StorageServiceTest {
  
  private StorageService storageService;
  
  public StorageServiceTest() {
  }
  
  @Autowired
  public void setStorageService(StorageService storageService) {
    this.storageService = storageService;
  }
  
  /**
   * Test of store method, of class StorageService.
   */
  @Test
  public void testStore() {
    MongoDataModel mdm = new MongoDataModel(
        "1234","movies","testMAYNE",true,null,new Date().toString()
    );
    storageService.store(mdm);
  }
  
  /**
   * Test of fetch method, of class StorageService.
   */
  @Test
  public void testFetch() {
    MongoDataModel mdm = storageService.fetch("1234");
    
  }

  /**
   * Configuration for creating the required Beans needed by the test class.
   * Environment configurations are loaded from a designated test file.
   * TODO Clarify why the configuration class must be static.
   */
  @Configuration
  @ComponentScan
  @PropertySource("classpath:test.application.properties")
  public static class TestConfiguration {
    
    @Value("${host.ip:127.0.0.1}")
    private String hostIp;
    
    @Value("${host.port:27017}")
    private int hostPort;
    
    @Value("${database:pdcTest}")
    private String database;
    
    @Value("${collection.name:pdcDataTest}")
    private String collectionName;
           
    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
      return new MongoTemplate(new MongoClient(hostIp, hostPort), database);
    }
    
    @Bean
    public StorageService storageService() throws UnknownHostException {
      return new MongoStorageServiceImpl(mongoTemplate(), collectionName);
    }
    
    /**
     * Refer to Spring documentation for Annotation Type PropertySource.
     * TODO change configuration class to avoid @Value as described in
     * @see <a href="https://jira.spring.io/browse/SPR-8539">Spring.io</a>.
     * @return something really long
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
    }
    
  }
  
}
