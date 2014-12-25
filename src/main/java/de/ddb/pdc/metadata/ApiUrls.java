package de.ddb.pdc.metadata;

/**
 * Utility class for generating URLs for DDB API endpoints.
 */
public class ApiUrls {

  private static final String API_URL =
      "https://api.deutsche-digitale-bibliothek.de";

  /**
   * Returns an absolute URL for a DDB API /search request.
   *
   * @param query     search query in Solr syntax
   * @param startItem start number of items to return
   * @param maxItems  maximum number of items to return
   * @param sort      sort order of items
   * @param apiKey    DDB API key for authentication
   * @return          corresponding URL
   */
  public static String searchUrl(String query,int startItem, int maxItems,
    String sort, String apiKey) {
    return url(apiKey, "/search",
        "query", query,
        "offset", Integer.toString(startItem),
        "rows", Integer.toString(maxItems),
        "sort", sort); //"relevance"
  }

  /**
   * Returns an absolute URL for a DDB API /items/{id}/aip request.
   *
   * @param itemId ID of the item to query for
   * @param apiKey DDB API key for authentication
   * @return       corresponding URL
   */
  public static String itemAipUrl(String itemId, String apiKey) {
    return url(apiKey, String.format("/items/%s/aip", itemId));
  }

  /**
   * Constructs a DDB API URL from an authentication key, a path and
   * a sequence of alternating query parameter keys and values. For
   * instance, the following call:
   *
   *   url(apiKey,
   *       "/search",
   *       "query", "goethe",
   *       "max", "10")
   *
   * will be converted to the following URL:
   *
   *   https://api.deutsche-digitale-bibliothek.de/search?query=goethe&max=10&oauth_consumer_key=...
   *
   * @param apiKey      DDB API key for authentication
   * @param path        path relative to the base API URL
   * @param queryParams query parameter keys and values
   * @return            corresponding URL
   */
  public static String url(String apiKey, String path, String... queryParams) {
    String url = API_URL + path;
    for (int key = 0; key < queryParams.length; key += 2) {
      url += (key == 0) ? "?" : "&";
      url += queryParams[key] + "=" + queryParams[key + 1];
    }
    url += (queryParams.length == 0) ? "?" : "&";
    url += "oauth_consumer_key=" + apiKey;
    return url;
  }

  /**
   *
   * @param dnbId
   * @param apiKey
   * @return
   */
  public static String entityUrl(String dnbId, String apiKey) {
    return url(apiKey, "/entities",
        "query", String.format("id:\"%s\"", dnbId));
  }
}
