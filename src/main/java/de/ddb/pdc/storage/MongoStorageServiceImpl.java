package de.ddb.pdc.storage;

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
public class MongoStorageServiceImpl implements StorageService {

  private final MongoTemplate mongoTemplate;
  private final String collectionName;

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
  public void store(StorageModel record) {
    mongoTemplate.insert(record, collectionName);
  }

  /**
   * Updates a record by removing the existing record and calling the
   * {@link #store(StorageModel) store()} method to store the new record.
   * The record that is removed is the first record that matches the itemId.
   *
   * @param newRecord
   */
  @Override
  public void update(StorageModel newRecord) {
    Query query = new Query();
    query.addCriteria(Criteria.where("itemId").is(newRecord.getItemId()));
    mongoTemplate.remove(query, collectionName);
    this.store(newRecord);
  }

  /**
   * Fetches the first StorageModel record from the collection that matches
   * the query. Implements the Query class so SQL-like constructs can be used.
   *
   * @param itemId
   * @return record
   */
  @Override
  public StorageModel fetch(String itemId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("itemId").is(itemId));
    return mongoTemplate.findOne(query, StorageModel.class, collectionName);
  }

  /**
   * Fetch all MongoDataModel records from the collection.
   *
   * @return list of MongoDataModel records
   */
  @Override
  public List<StorageModel> fetchAll() {
    return mongoTemplate.findAll(StorageModel.class, collectionName);
  }

  /**
   * Delete all records by dropping the collection.
   */
  @Override
  public void deleteAll() {
    mongoTemplate.dropCollection(collectionName);
  }
}
