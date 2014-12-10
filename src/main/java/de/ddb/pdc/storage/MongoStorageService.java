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
public class MongoStorageService implements StorageService {

  private final MongoTemplate mongoTemplate;
  private final String collectionName;

  @Autowired
  public MongoStorageService(MongoTemplate mongoTemplate,
          @Value("${collection.name:pdcData}") String collectionName) {
    this.mongoTemplate = mongoTemplate;
    this.collectionName = collectionName;
  }

  /**
   * Stores a single MongoDataModel record in the collection.
   *
   */
  @Override
  public void store(PDCResultEntity record) {
    mongoTemplate.insert(record, collectionName);
  }

  /**
   * Updates a record by removing the existing record and calling the
   * {@link store(StorageModel)} method to store the new record.
   * The record that is removed is the first record that matches the itemId.
   *
   */
  @Override
  public void update(PDCResultEntity newRecord) {
    Query query = new Query();
    query.addCriteria(Criteria.where("itemId").is(newRecord.getItemId()));
    mongoTemplate.remove(query, collectionName);
    this.store(newRecord);
  }

  /**
   * Fetches the first StorageModel record from the collection that matches
   * the query. Implements the Query class so SQL-like constructs can be used.
   *
   */
  @Override
  public PDCResultEntity fetch(String itemId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("itemId").is(itemId));
    return mongoTemplate.findOne(query, PDCResultEntity.class, collectionName);
  }

  /**
   * Fetch all MongoDataModel records from the collection.
   *
   * @return list of MongoDataModel records
   */
  @Override
  public List<PDCResultEntity> fetchAll() {
    return mongoTemplate.findAll(PDCResultEntity.class, collectionName);
  }

  /**
   * Delete all records by dropping the collection.
   */
  @Override
  public void deleteAll() {
    mongoTemplate.dropCollection(collectionName);
  }
}
