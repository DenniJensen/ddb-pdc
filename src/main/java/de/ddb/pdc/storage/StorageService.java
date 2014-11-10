/**
 * Storage service for storing and fetching PDC data models. TODO decouple the
 * MongoDataModel from the service.
 */
package de.ddb.pdc.storage;

import java.util.List;

public interface StorageService {

  /**
   * Insert a single MongoDataModel record.
   *
   * @param record
   */
  public void store(MongoDataModel record);

  /**
   * Find a single MongoDataModel record by its item ID.
   *
   * @param itemId
   * @return record
   */
  public MongoDataModel fetch(String itemId);

  /**
   * Fetch all records from storage.
   *
   * @return list of records
   */
  public List<MongoDataModel> fetchAll();

  /**
   * Remove all records from storage.
   */
  public void deleteAll();
}
