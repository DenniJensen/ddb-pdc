package de.ddb.pdc.metadata;

import java.util.*;

/**
 * Represents a year range as defined in the DDB time vocabulary.
 *
 * https://api.deutsche-digitale-bibliothek.de/doku/display/ADD/Zeitvokabular
 */
public class DdbTimeSpan {

  private int minYear;
  private int maxYear;

  /**
   * Creates a new DdbTimeSpan.
   *
   * @param minYear lower bound (inlusive)
   * @param maxYear upper bound (inclusive)
   */
  public DdbTimeSpan(int minYear, int maxYear) {
    this.minYear = minYear;
    this.maxYear = maxYear;
  }

  /**
   * Returns the lower bound of the time span.
   *
   * @return least recent year in time span
   */
  public int getMinYear() {
    return minYear;
  }

  /**
   * Returns the (inclusive) upper bound of the time span.
   *
   * @return most recent year in time span
   */
  public int getMaxYear() {
    return maxYear;
  }

  /**
   * Converts a set of DDB time vocabulary element ID's (e.g. "time_61900")
   * to a time span. The time span described by the elements is assumed
   * be continuous (without gaps).
   *
   * Of the passed ID's, only those representing the most precise type of
   * time spans are considered. For instance, when "time_61900" (19th century),
   * "time_61947" (1826 - 1850) and "time_61975" (1851 - 1875) are passed,
   * only the latter two are used (resulting in the time span from 1826 to
   * 1876) because quarter centuries are more precise than complete centuries.
   *
   * Note that only time vocabulary items between 1800 and 2000 are supported.
   *
   * @param ids DDB time vocabulary element names
   * @return         matching DdbTimeSpan, or null if an unsupported ID
   *                 is encountered
   */
  public static DdbTimeSpan fromIds(Collection<String> ids) {
    SortedSet<String> idSet = new TreeSet<>(ids);
    SortedSet<String> centurySubsetIds = findCenturySubsetIds(idSet);
    if (!centurySubsetIds.isEmpty()) {
      return fromIdsWithSamePrecision(centurySubsetIds);
    } else {
      // Assume that only complete-century ID's were passed.
      return fromIdsWithSamePrecision(idSet);
    }
  }

  private static SortedSet<String> findCenturySubsetIds(Set<String> ids) {
    SortedSet<String> found = new TreeSet<>();
    for (String element : ids) {
      if (isCenturySubsetId(element)) {
        found.add(element);
      }
    }
    return found;
  }

  private static boolean isCenturySubsetId(String id) {
    return !id.endsWith("00");
  }

  private static DdbTimeSpan fromIdsWithSamePrecision(SortedSet<String> ids) {
    DdbTimeSpan min = fromSingleId(ids.first());
    DdbTimeSpan max = fromSingleId(ids.last());
    if (min == null || max == null) {
      return null;
    }
    return new DdbTimeSpan(min.getMinYear(), max.getMaxYear());
  }

  private static DdbTimeSpan fromSingleId(String id) {
    switch (id) {
      case "time_61900":
        return new DdbTimeSpan(1800, 1899);
      case "time_61907":
        return new DdbTimeSpan(1801, 1825);
      case "time_61925":
        return new DdbTimeSpan(1826, 1850);
      case "time_61947":
        return new DdbTimeSpan(1851, 1875);
      case "time_61975":
        return new DdbTimeSpan(1876, 1900);
      case "time_62000":
        return new DdbTimeSpan(1901, 2000);
      case "time_62010":
        return new DdbTimeSpan(1901, 1910);
      case "time_62020":
        return new DdbTimeSpan(1911, 1920);
      case "time_62030":
        return new DdbTimeSpan(1921, 1930);
      case "time_62040":
        return new DdbTimeSpan(1931, 1940);
      case "time_62050":
        return new DdbTimeSpan(1941, 1950);
      case "time_62060":
        return new DdbTimeSpan(1951, 1960);
      case "time_62070":
        return new DdbTimeSpan(1961, 1970);
      case "time_62080":
        return new DdbTimeSpan(1971, 1980);
      case "time_62090":
        return new DdbTimeSpan(1981, 1990);
      case "time_62095":
        return new DdbTimeSpan(1991, 2000);
      default:
        return null;
    }
  }
}
