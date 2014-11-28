package de.ddb.pdc.metadata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  class for methods of meta fetcher
 */
public final class Utility {

  private Utility(){}

  /**
   * @param string  input for regex
   * @param regex   for use
   * @return        the first string applied by regex or an empty string
   */
  public static String useRegex(String string, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);
    if (matcher.find()) {
      return matcher.group(0);
    }
    return "";
  }
}
