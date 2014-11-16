package de.ddb.pdc.metadata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
/**
 * Implementation of the {@link MetaFetcher} interface.
 */
public class MetaFetcherImpl implements MetaFetcher {

  private static final String URL =
    "https://www.deutsche-digitale-bibliothek.de";
  private static final String APIURL =
    "https://api.deutsche-digitale-bibliothek.de";
  private static final String SEARCH = "/search?";
  private static final String AUTH = "oauth_consumer_key=";
  private static final String SORTROWS = "&sort=RELEVANCE&rows=";
  private static final String QUERY = "&query=";
  private static final String ITEM = "/items/";
  private static final String ENTITY = "/entity/";
  private static final String AIP = "/aip?";

  private RestTemplate restTemplate;
  private String authKey;

  /**
   * Creates a new MetaFetcherImpl.
   *
   * @param restTemplate RestTemplate to use for issuing requests
   * @param authKey      authentication Key for the DDB API
   */
  public MetaFetcherImpl(RestTemplate restTemplate, String authKey) {
    this.restTemplate = restTemplate;
    this.authKey = authKey;
  }

  /**
   * {@inheritDoc}
   */
  public DDBItem[] searchForItems(String query, int maxCount)
      throws RestClientException {
    String modifiedQuery = query.replace(" ", "+");
    String url = APIURL + SEARCH + AUTH + authKey + SORTROWS + maxCount + QUERY
      + modifiedQuery;
    ResultsOfJSON roj = restTemplate.getForObject(url, ResultsOfJSON.class);
    return getDDBItems(roj);
  }


  /**
   * {@inheritDoc}
   */
  public void fetchMetadata(DDBItem ddbItem) throws RestClientException {
    String url = APIURL + ITEM + ddbItem.getId() + AIP + AUTH + authKey;
    String urlEntity = APIURL + ENTITY + ddbItem.getId(); // works without key
    ResultsOfJSON roj = restTemplate.getForObject(url, ResultsOfJSON.class);
    ResultsOfJSON rojEntity = restTemplate.getForObject(urlEntity, 
      ResultsOfJSON.class);
    fillDDBItemMetadataFromDDB(ddbItem, roj);
  }
  
  /**
   * @param ddbItem filled with information
   * @param roj store information of the ddb aip request
   */
  private void fillDDBItemMetadataFromDDB(DDBItem ddbItem, ResultsOfJSON roj) {
    RDFItem rdfitem = roj.getEdm().getRdf();
    String publishedYear = (String) rdfitem.getProvidedCHO().get("issued");
    
    int year = 8000;
    try {
      if (publishedYear != null) {
        year = Integer.parseInt(publishedYear.split(",")[0]);
        
      }
    } catch (NumberFormatException e) {
      //test
    }
    ddbItem.setPublishedYear(year);
    
    // some Agent input are represented as ArrayList or LinkedHashMap
    if (rdfitem.getAgent().getClass() == ArrayList.class) {
      ArrayList<LinkedHashMap> alAgent =  (ArrayList) rdfitem.getAgent();
      for (int idx = 0; idx < alAgent.size(); idx++) {
        if (alAgent.get(idx).get("@about").toString().startsWith("http")) {
          String authorid = alAgent.get(idx).get("@about").toString()
            .replace("http://d-nb.info/gnd/", "");
          Author author = new Author(authorid);
          int birth = Integer.parseInt(roj.getResults().get(0).getDateOfBirth()
            .split(" ")[2]);
          author.setBirthYear(birth);
          author.setPlaceOfBirth(roj.getResults().get(0).getPlaceOfBirth()
            .get(0));
          int death = Integer.parseInt(roj.getResults().get(0).getDateOfDeath()
            .split(" ")[2]);
          author.setDeathYear(death);
          author.setPlaceOfDeath(roj.getResults().get(0).getPlaceOfDeath()
            .get(0));
          ddbItem.setAuthor(author);
        }
      }
    }
    
    // till now no testdata where author in LinkedHashMap
    if (rdfitem.getAgent().getClass() == LinkedHashMap.class) {
        //LinkedHashMap lhmAgent = (LinkedHashMap) rdf.get("Agent");    
    }
    ddbItem.setInstitute((String) rdfitem.getAggregation().get("provider"));
  }
  
  /**
   * @param roj results of the ddb search query
   * @return a list of ddbitems from roj
   */
  private DDBItem[] getDDBItems(ResultsOfJSON roj) {
    int maxResults = roj.getResults().size();
    DDBItem[] ddbItems = new DDBItem[maxResults];

    int idx = 0;
    for (SearchResultItem rsi : roj.getResults()) {
      DDBItem ddbItem = new DDBItem(rsi.getId());
      ddbItem.setTitle(deleteMatchTags(rsi.getTitle()));
      ddbItem.setSubtitle(deleteMatchTags(rsi.getSubtitle()));
      ddbItem.setImageUrl(URL + rsi.getThumbnail());
      ddbItem.setCategory(rsi.getCategory());
      ddbItem.setMedia(rsi.getMedia());
      ddbItem.setType(rsi.getType());
      ddbItems[idx] = ddbItem;
      idx++;
    }

    return ddbItems;
  }

  /**
   * Deletes the "<match>...</match>" markers in metadata values of search
   * result items. These are added by the DDB API to simplify highlighting
   * of matching substrings, but we don't need or want them.
   */
  public static String deleteMatchTags(String string) {
    return string.replace("<match>", "").replace("</match>", "");
  }
}
