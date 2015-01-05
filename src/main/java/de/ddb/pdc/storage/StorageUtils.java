package de.ddb.pdc.storage;

import de.ddb.pdc.core.PDCResult;

/**
 * Helper class for storage-related conversions.
 */
public class StorageUtils {

  public static StoredPDCResult toStoredPDCResult(PDCResult pdcResult) {
    return new StoredPDCResult(
        pdcResult.getItemId(),
        pdcResult.getItemCategory(),
        pdcResult.getInstitution(),
        pdcResult.isPublicDomain(),
        pdcResult.getTrace(),
        pdcResult.getCreatedDate()
    );
  }

}
