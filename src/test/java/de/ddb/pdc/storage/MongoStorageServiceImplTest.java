package de.ddb.pdc.storage;

import com.mongodb.MongoClient;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.core.AnsweredQuestion;
import de.ddb.pdc.core.Question;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class MongoStorageServiceImplTest {

  private StorageService storageService;

  public MongoStorageServiceImplTest() {
  }

  @Autowired
  public void setStorageService(StorageService storageService) {
    this.storageService = storageService;
  }

  @Test
  public void testStoreAndFetch() {
    final String itemID = "156987";
    final String itemCategory = "Movies";
    final String institute = "Insti";
    final boolean publicDomain = false;
    final List<AnsweredQuestion> trace = new ArrayList<>();
    AnsweredQuestion answeredQuestionA = new AnsweredQuestion(
            Question.AUTHOR_ANONYMOUS, Answer.NO, null);
    AnsweredQuestion answeredQuestionB = new AnsweredQuestion(
            Question.PERFORMED_MORE_THAN_50_YEARS_AGO, Answer.YES, null);
    trace.add(answeredQuestionA);
    trace.add(answeredQuestionB);

    StorageModel newEntry = new StorageModel(
        itemID,itemCategory,institute,publicDomain,trace);
    storageService.store(newEntry);

    StorageModel storedEntry = storageService.fetch(itemID);
    boolean check = compareTwoEntries(newEntry, storedEntry);
    Assert.assertEquals(true, check);
  }

  @Test
  public void testStoreAndUpdate(){
    final String itemID = "8963254";
    final String itemCategory = "Movies";
    final String institute = "Insti";
    final boolean publicDomain = false;
    final List<AnsweredQuestion> trace = new ArrayList<>();
    AnsweredQuestion answeredQuestionA = new AnsweredQuestion(
            Question.COUNTRY_OF_ORIGIN_EEA, Answer.YES, null);
    AnsweredQuestion answeredQuestionB = new AnsweredQuestion(
            Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO, Answer.NO, null);
    trace.add(answeredQuestionA);
    trace.add(answeredQuestionB);

    StorageModel newEntry = new StorageModel(
        itemID,itemCategory,institute,publicDomain,trace);
    storageService.store(newEntry);

    final List<AnsweredQuestion> newTrace = new ArrayList<>();
    AnsweredQuestion newAnsweredQuestionA = new AnsweredQuestion(
            Question.COUNTRY_OF_ORIGIN_EEA, Answer.YES, null);
    AnsweredQuestion newAnsweredQuestionB = new AnsweredQuestion(
            Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO, Answer.YES, null);
    newTrace.add(newAnsweredQuestionA);
    newTrace.add(newAnsweredQuestionB);
    StorageModel updatedEntry = new StorageModel(
            itemID, itemCategory, institute, true, newTrace);
    storageService.update(updatedEntry);

    StorageModel storedEntry = storageService.fetch(itemID);
    Assert.assertTrue(compareTwoEntries(updatedEntry, storedEntry));
  }

  @Test
  public void testDeleteAll() {
    List <StorageModel> entriesBefore= storageService.fetchAll();
    Assert.assertEquals(false, entriesBefore.isEmpty());
    storageService.deleteAll();
    List <StorageModel> entriesAfter = storageService.fetchAll();
    Assert.assertEquals(true, entriesAfter.isEmpty());
  }

  /**
   * Compares two Entries and return true if these have the same values
   *
   * @param mdm1
   * @param mdm2
   *
   * @return true if entries are equal
   */
  private boolean compareTwoEntries(StorageModel mdm1, StorageModel mdm2){
    boolean equal = false;

    if((mdm1.getItemId().equals(mdm2.getItemId())) &&
            (mdm1.getItemCategory().equals(mdm2.getItemCategory())) &&
            (mdm1.getInstitute().equals(mdm2.getInstitute())) &&
            (mdm1.isPublicDomain() == mdm2.isPublicDomain())&&
            (compareTwoTraces(mdm1.getTrace(), mdm2.getTrace())) &&
            (mdm1.getTimestampAsString().equals(mdm2.getTimestampAsString()))
            ){
      equal = true;
    }
    return equal;
  }

  /**
   * Compares two Traces and return true if these have the same values
   *
   * @param trace1
   * @param trace2
   *
   * @return true if traces are equal
   */
  private boolean compareTwoTraces(List<AnsweredQuestion> trace1,
          List<AnsweredQuestion> trace2){
    boolean equal = true;
    if(!( trace1.size() == trace2.size()) ){
      equal = false;
    }
    else{
      for(int i = 0; i < trace1.size(); i++){
        if(!( trace1.get(i).getQuestion().equals(trace2.get(i).getQuestion()) &&
                trace1.get(i).getAnswer().equals(trace2.get(i).getAnswer()))){
          equal = false;
          break;
        }
        if((!trace1.get(i).hasNote()) || (!trace2.get(i).hasNote()) ){
          if( (trace1.get(i).hasNote() && (!trace2.get(i).hasNote())) ||
                (trace2.get(i).hasNote() && (!trace1.get(i).hasNote())) ){
            equal = false;
            break;
          }
        }
        else{
          if(! (trace1.get(i).getNote().equals(trace2.get(i).getNote())) ){
            equal = false;
            break;
          }
        }
      }
    }
    return equal;
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
    public static PropertySourcesPlaceholderConfigurer
        propertyPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
    }

  }

}
