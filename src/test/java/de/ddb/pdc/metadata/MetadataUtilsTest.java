package de.ddb.pdc.metadata;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MetadataUtilsTest {

  @Test
  public void useRegexTest() {
    assertEquals("world", MetadataUtils.useRegex("Hello world", "world"));
    assertEquals("1", MetadataUtils.useRegex("Hello 12369", "\\d"));
    assertEquals("o", MetadataUtils.useRegex("Hello world", "[^Hel]"));
    assertEquals("", MetadataUtils.useRegex("Hello world", "banana"));
  }
}
