package de.ddb.pdc.metadata;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DdbTimeSpanTest {

  @Test
  public void fromCenturyIds() {
    Set<String> elements = new HashSet<>();
    elements.add("time_61900");
    DdbTimeSpan timeSpan = DdbTimeSpan.fromIds(elements);
    assertEquals(1800, timeSpan.getMinYear());
    assertEquals(1899, timeSpan.getMaxYear());
  }

  @Test
  public void fromCenturySubsetIds() {
    Set<String> elements = new HashSet<>();
    elements.add("time_62020");
    elements.add("time_62030");
    DdbTimeSpan timeSpan = DdbTimeSpan.fromIds(elements);
    assertEquals(1911, timeSpan.getMinYear());
    assertEquals(1930, timeSpan.getMaxYear());
  }

  @Test
  public void fromMixedIds() {
    Set<String> elements = new HashSet<>();
    elements.add("time_61900");
    elements.add("time_61925");
    elements.add("time_61947");
    DdbTimeSpan timeSpan = DdbTimeSpan.fromIds(elements);
    assertEquals(1826, timeSpan.getMinYear());
    assertEquals(1875, timeSpan.getMaxYear());
  }
}
