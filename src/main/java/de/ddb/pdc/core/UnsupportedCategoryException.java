package de.ddb.pdc.core;

/**
 * An unsupported category was chosen. This error is thrown to indicate that you
 * called a method of a public domain calculator and passed a category as a
 * parameter that is not supported by the implementation you are using. Call the
 * getSupportedCategories method of your public domain calculator implementation
 * to know which categories are supported and to avoid this exception.
 * 
 * @author Frank Zechert
 */
public class UnsupportedCategoryException extends Exception {

  private static final long serialVersionUID = -3142564228568193171L;

  /**
   * The category that was unsupported and caused this exception.
   */
  private Category unsupportedCategory;

  /**
   * Create a new InvalidCategoryException.
   * 
   * @param unsupportedCategory The unsupported category that caused this
   *        exception.
   */
  public UnsupportedCategoryException(Category unsupportedCategory) {
    this.unsupportedCategory = unsupportedCategory;
  }

  /**
   * Gets the category that was unsupported and caused this exception
   * 
   * @return the unsupported category
   */
  public Category getUnsupportedCategory() {
    return this.unsupportedCategory;
  }
}
