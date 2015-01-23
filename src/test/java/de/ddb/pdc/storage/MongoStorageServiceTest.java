package de.ddb.pdc.storage;

import de.ddb.pdc.Main;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.core.AnsweredQuestion;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.core.Question;
import de.ddb.pdc.metadata.DDBItem;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
public class MongoStorageServiceTest {

  @Autowired
  private StorageService storageService;

  @Test
  public void testStoreAndFetch() {
    final String itemID = "156987";
    final String title = "Title";
    final String subtitle = "Subtitle";
    final String imageUrl = "/image";
    final String category = "Movies";
    final String institution = "Insti";
    final Boolean publicDomain = false;
    final List<AnsweredQuestion> trace = new ArrayList<>();
    AnsweredQuestion answeredQuestionA = new AnsweredQuestion(
            Question.AUTHOR_ANONYMOUS, Answer.NO, null);
    AnsweredQuestion answeredQuestionB = new AnsweredQuestion(
            Question.PERFORMED_MORE_THAN_50_YEARS_AGO, Answer.YES, null);
    trace.add(answeredQuestionA);
    trace.add(answeredQuestionB);

    DDBItem metadata = new DDBItem(itemID);
    metadata.setTitle(title);
    metadata.setSubtitle(subtitle);
    metadata.setImageUrl(imageUrl);
    metadata.setCategory(category);
    metadata.setInstitution(institution);

    PDCResult newEntry = new PDCResult(publicDomain, trace, metadata);
    storageService.store(newEntry);

    PDCResult storedEntry = storageService.fetch(itemID);
    boolean check = compareTwoEntries(newEntry, storedEntry);
    Assert.assertEquals(true, check);
  }

  @Test
  public void testStoreAndUpdate(){

    final String itemID = "8963254";
    final String title = "Title";
    final String subtitle = "Subtitle";
    final String imageUrl = "/image";
    final String category = "Movies";
    final String institution = "Insti";
    final Boolean publicDomain = false;
    final List<AnsweredQuestion> trace = new ArrayList<>();
    AnsweredQuestion answeredQuestionA = new AnsweredQuestion(
            Question.COUNTRY_OF_ORIGIN_EEA, Answer.YES, null);
    AnsweredQuestion answeredQuestionB = new AnsweredQuestion(
            Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO, Answer.NO, null);
    trace.add(answeredQuestionA);
    trace.add(answeredQuestionB);

    DDBItem metadata = new DDBItem(itemID);
    metadata.setTitle(title);
    metadata.setSubtitle(subtitle);
    metadata.setImageUrl(imageUrl);
    metadata.setCategory(category);
    metadata.setInstitution(institution);

    PDCResult newEntry = new PDCResult(publicDomain, trace, metadata);
    storageService.store(newEntry);

    final List<AnsweredQuestion> newTrace = new ArrayList<>();
    AnsweredQuestion newAnsweredQuestionA = new AnsweredQuestion(
            Question.COUNTRY_OF_ORIGIN_EEA, Answer.YES, null);
    AnsweredQuestion newAnsweredQuestionB = new AnsweredQuestion(
            Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO, Answer.YES, null);
    newTrace.add(newAnsweredQuestionA);
    newTrace.add(newAnsweredQuestionB);

    PDCResult updatedEntry = new PDCResult(
        Boolean.TRUE, newTrace, metadata
    );
    storageService.update(updatedEntry);

    PDCResult storedEntry = storageService.fetch(itemID);
    Assert.assertTrue(compareTwoEntries(updatedEntry, storedEntry));
  }

  @Test
  public void testDeleteAll() {
    List <PDCResult> entriesBefore = storageService.fetchAll();
    Assert.assertEquals(false, entriesBefore.isEmpty());
    storageService.deleteAll();
    List <PDCResult> entriesAfter = storageService.fetchAll();
    Assert.assertEquals(true, entriesAfter.isEmpty());
  }

  /**
   * Compares two Entries and return true if these have the same values.
   * The expected record's creationDate should be less than the actual record's
   * creationDate, as the latter is created later on in time.
   *
   * @param mdm1
   * @param mdm2
   *
   * @return true if entries are equal
   */
  private boolean compareTwoEntries(PDCResult mdm1, PDCResult mdm2){
    return (
      (mdm1.getItemId().equals(mdm2.getItemId())) &&
      (mdm1.getTitle().equals(mdm2.getTitle())) &&
      (mdm1.getSubtitle().equals(mdm2.getSubtitle())) &&
      (mdm1.getImageUrl().equals(mdm2.getImageUrl())) &&
      (mdm1.getItemCategory().equals(mdm2.getItemCategory())) &&
      (mdm1.getInstitution().equals(mdm2.getInstitution())) &&
      (mdm1.isPublicDomain().equals(mdm2.isPublicDomain())) &&
      (mdm1.getCreatedDate().compareTo(mdm2.getCreatedDate()) == 0 ) &&
      (compareTwoTraces(mdm1.getTrace(), mdm2.getTrace()))
    );
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

}
