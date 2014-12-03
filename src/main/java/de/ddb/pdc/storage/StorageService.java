/**
 * Storage service for storing and fetching PDC data models.
 */
package de.ddb.pdc.storage;

import java.util.List;

public interface StorageService {

  /**
   * Insert a single MongoDataModel record.
   *
   * @param record
   */
  public void store(StorageModel record);

  /**
   * Update an existing MongoDataModel.
   * 
   * @param record 
   */
  public void update(StorageModel record);
  
  /**
   * Find a single MongoDataModel record by its item ID.
   *
   * @param itemId
   * @return record
   */
  public StorageModel fetch(String itemId);

  /**
   * Fetch all records from storage.
   *
   * @return list of records
   */
  public List<StorageModel> fetchAll();

  /**
   * Remove all records from storage.
   */
  public void deleteAll();
}
