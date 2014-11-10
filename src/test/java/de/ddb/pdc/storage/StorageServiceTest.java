/** 
 * This is not a unit test, this is a real test. :D
 */
package de.ddb.pdc.storage;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
        "1234","movies","institute",true,null,"2014-11-10"
    );
    storageService.store(mdm);
  }

  /**
   * Test of update method, of class StorageService.
   */
  @Test
  public void testUpdate() {
  }
  
  /**
   * Test of fetch method, of class StorageService.
   */
  @Test
  public void testFetch() {
  }

  /**
   * Test of fetchAll method, of class StorageService.
   */
  @Test
  public void testFetchAll() {
  }

  /**
   * Test of deleteAll method, of class StorageService.
   */
  @Test
  public void testDeleteAll() {
  }
  
  /**
   * Configuration for creating the required Beans needed by the test class.
   * 
   * TODO Clarify why the configuration class must be static.
   */
  @Configuration
  @ComponentScan  
  public static class TestConfiguration {
    
    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
      return new MongoTemplate(new MongoClient("localhost", 27017), "pdc");
    }
    
    @Bean
    public StorageService storageService() throws UnknownHostException {
      return new MongoStorageServiceImpl(mongoTemplate(), "pdcData");
    }
  }
  
}
