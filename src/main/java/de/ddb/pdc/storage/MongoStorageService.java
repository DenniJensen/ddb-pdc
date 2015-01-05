package de.ddb.pdc.storage;

import de.ddb.pdc.core.PDCResult;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * The MongoDB implementation of the StorageService.
 *
 * This class does not provide a MongoTemplate.update method as all the
 * relevant data required for record creation is available when using the
 * StorageService.
 * A remove-insert implementation is used instead. The itemId is assumed to be
 * globally unique. This is important as MongoDB may otherwise apply changes to
 * multiple records.
 *
 */
@Component
public class MongoStorageService implements StorageService {

  private final MongoTemplate mongoTemplate;
  private final String collectionName;

  @Autowired
  public MongoStorageService(MongoTemplate mongoTemplate,
      @Value("${spring.data.mongodb.collection:pdcData}")
      String collectionName) throws UnknownHostException {

    this.mongoTemplate = mongoTemplate;
    this.collectionName = collectionName;
  }

  /**
   * Stores a single MongoDataModel record in the collection.
   *
   */
  @Override
  public void store(PDCResult record) {
    if (record != null) {
      StoredPDCResult storageRecord = StorageUtils.toStoredPDCResult(record);
      mongoTemplate.insert(storageRecord, collectionName);
    }
  }

  /**
   * Updates a record by removing the existing record and calling the
   * {@link store(PDCResult)} method to store the new record.
   * The record that is removed is the first record that matches the itemId.
   *
   */
  @Override
  public void update(PDCResult newRecord) {
    if (newRecord != null) {
      Query query = new Query();
      query.addCriteria(Criteria.where("itemId").is(newRecord.getItemId()));
      mongoTemplate.remove(query, collectionName);
      this.store(newRecord);
    }
  }

  /**
   * Fetches the first record from the collection that matches the query.
   *
   * Implements the Query class so SQL-like constructs can be used.
   * @return the target record or null if the record was not found.
   *
   */
  @Override
  public PDCResult fetch(String itemId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("itemId").is(itemId));
    StoredPDCResult fetchedRecord = mongoTemplate.findOne(
        query, StoredPDCResult.class, collectionName
    );
    if (fetchedRecord == null) {
      return null;
    } else {
      return new PDCResult(fetchedRecord);
    }
  }

  /**
   * Fetch all records from the collection.
   * A linked list is used to store the converted storage records.
   * @return list of MongoDataModel records
   */
  @Override
  public List<PDCResult> fetchAll() {
    List<StoredPDCResult> fetchedStorageRecords = mongoTemplate.findAll(
        StoredPDCResult.class, collectionName
    );
    List<PDCResult> fetchedRecords = new LinkedList();
    for (StoredPDCResult storageRecord : fetchedStorageRecords) {
      fetchedRecords.add(new PDCResult(storageRecord));
    }
    return fetchedRecords;
  }

  /**
   * Delete all records by dropping the collection.
   */
  @Override
  public void deleteAll() {
    mongoTemplate.dropCollection(collectionName);
  }
}
