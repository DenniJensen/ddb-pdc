/**
 * The MongoDB implementation of the StorageService.
 *
 * This class does not use a MongoTemplate.update method as all the relevant 
 * data of the record to be created is available when using the StorageService. 
 * A remove-insert implementation is used instead. The itemId is assumed to be 
 * globally unique. This is important as MongoDB may otherwise apply changes to
 * multiple records.
 *
 * TODO include support for secure mode (connection with authentication).
 */
package de.ddb.pdc.storage;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class MongoStorageServiceImpl implements StorageService {

  private final MongoTemplate mongoTemplate;
  private final String collectionName;

  /**
   * @Autowired ensures Spring injects the required Bean. @Value reads the
   * related key-value from the properties file.
   * @param mongoTemplate
   * @param collectionName
   */
  @Autowired
  public MongoStorageServiceImpl(MongoTemplate mongoTemplate,
          @Value("${collection.name:pdcData}") String collectionName) {
    this.mongoTemplate = mongoTemplate;
    this.collectionName = collectionName;
  }

  /**
   * Stores a single MongoDataModel record in the collection.
   *
   * @param record
   */
  @Override
  public void store(MongoDataModel record) {
    mongoTemplate.insert(record, collectionName);
  }
    
  /**
   * Updates a record by removing the existing record and calling the
   * {@link #store(MongoDataModel) store()} method to store the new record. 
   * The record that is removed is the first record that matches the itemId. 
   * TODO implement inspection of the WriteResult to ensure the old record was
   * removed.
   * 
   * @param newRecord
   */
  @Override
  public void update(MongoDataModel newRecord) {
    Query query = new Query();
    query.addCriteria(Criteria.where("itemId").is(newRecord.getItemId()));
    mongoTemplate.remove(query, collectionName);
    this.store(newRecord);
  }

  /**
   * Fetches the first MongoDataModel record from the collection that matches
   * the query. Implements the Query class so SQL-like constructs can be used.
   *
   * @param itemId
   * @return record
   */
  @Override
  public MongoDataModel fetch(String itemId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("itemId").is(itemId));
    return mongoTemplate.findOne(query, MongoDataModel.class, collectionName);
  }

  /**
   * Fetch all MongoDataModel records from the collection.
   *
   * @return list of MongoDataModel records
   */
  @Override
  public List<MongoDataModel> fetchAll() {
    return mongoTemplate.findAll(MongoDataModel.class, collectionName);
  }

  /**
   * Delete all records by dropping the collection.
   */
  @Override
  public void deleteAll() {
    mongoTemplate.dropCollection(collectionName);
  }
}
