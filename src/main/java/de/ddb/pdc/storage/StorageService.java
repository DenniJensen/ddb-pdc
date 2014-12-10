package de.ddb.pdc.storage;

import java.util.List;

/**
 * Storage service for storing and fetching PDC data models.
 */
public interface StorageService {

  /**
   * Insert a single MongoDataModel record.
   *
   * @param record
   */
  public void store(StoredPDCResult record);

  /**
   * Update an existing MongoDataModel.
   * 
   * @param record 
   */
  public void update(StoredPDCResult record);
  
  /**
   * Find a single MongoDataModel record by its item ID.
   *
   * @param itemId
   * @return record
   */
  public StoredPDCResult fetch(String itemId);

  /**
   * Fetch all records from storage.
   *
   * @return list of records
   */
  public List<StoredPDCResult> fetchAll();

  /**
   * Remove all records from storage.
   */
  public void deleteAll();
}
