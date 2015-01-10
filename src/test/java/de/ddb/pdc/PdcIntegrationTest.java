package de.ddb.pdc;

import de.ddb.pdc.metadata.ApiUrls;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PdcIntegrationTest {

  @Value("http://localhost:${local.server.port}")
  private String urlPrefix;

  @Value("${ddb.apikey}")
  private String dbbApiKey;

  @Autowired
  private RestTemplate serverRestTemplate;
  private MockRestServiceServer mockDdbApi;

  private final String DDB_DOMAIN = "https://www.deutsche-digitale-bibliothek.de";

  @Before
  public void setUp() {
    mockDdbApi = MockRestServiceServer.createServer(serverRestTemplate);
  }

  @Test
  public void search() throws Exception {
    String url = ApiUrls.searchUrl("goethe", 0, 3, "relevance", dbbApiKey);
    mockDdbApiRequest(url, "/ddb_search/goethe3.json");
    List results = getForObject("/search?q=goethe&max=3", List.class);

    assertEquals(3, results.size());

    String totalUrl;
    String urlSearch;
    Map result1 = (Map) results.get(0);
    assertEquals("TNPFDKO2VDGBZ72RWC6RKDNZYZQZP3XK", result1.get("id"));
    assertEquals("Goethe", result1.get("title"));
    assertEquals("Druckgraphik", result1.get("subtitle"));
    urlSearch = "/binary/TNPFDKO2VDGBZ72RWC6RKDNZYZQZP3XK/list/1.jpg";
    totalUrl = DDB_DOMAIN + urlSearch;
    assertEquals(totalUrl, result1.get("imageUrl"));

    Map result2 = (Map) results.get(1);
    assertEquals("ILL7O3BSG7ZXIZMX6RT23AR7FDT3NUPK", result2.get("id"));
    assertEquals("Goethe", result2.get("title"));
    assertEquals("Zeichnung", result2.get("subtitle"));
    urlSearch = "/binary/ILL7O3BSG7ZXIZMX6RT23AR7FDT3NUPK/list/1.jpg";
    totalUrl = DDB_DOMAIN + urlSearch;
    assertEquals(totalUrl, result2.get("imageUrl"));

    Map result3 = (Map) results.get(2);
    assertEquals("4XUDMRWXBMC7O3FPLATAK2HLCMLBBO22", result3.get("id"));
    assertEquals("Cornelie Goethe", result3.get("title"));
    assertEquals("Druckgraphik", result3.get("subtitle"));
    urlSearch =  "/binary/4XUDMRWXBMC7O3FPLATAK2HLCMLBBO22/list/1.jpg";
    totalUrl = DDB_DOMAIN + urlSearch;
    assertEquals(totalUrl, result3.get("imageUrl"));
  }

  private <T> T getForObject(String path, Class<T> resultClass) {
    RestTemplate client = new TestRestTemplate();
    return client.getForObject(urlPrefix + path, resultClass);
  }

  @Test
  public void determinePublicDomain() throws Exception {
    final String itemId = "UGTZDTFHRNELDDLG2BGYKJMSVIB4XSML";
    final String authorId = "http://d-nb.info/gnd/118540238";
    final String placeOfDeathId = "http://d-nb.info/gnd/4065105-8";

    String aipUrl = ApiUrls.itemAipUrl(itemId, dbbApiKey);
    mockDdbApiRequest(aipUrl, "/ddb_items_aip/" + itemId + ".xml");
    String authorUrl = ApiUrls.dnbUrl(authorId);
    mockDdbApiRequest(authorUrl, "/dnb/118540238.xml");
    String placeOfDeathUrl = ApiUrls.dnbUrl(placeOfDeathId);
    mockDdbApiRequest(placeOfDeathUrl, "/dnb/4065105-8.xml");

    Map result = getForObject("/pdc/" + itemId, Map.class);

    assertEquals(null, result.get("publicDomain"));
    List trace = (List) result.get("trace");
    assertEquals(9, trace.size());

    Map question1 = (Map) trace.get(0);
    assertEquals("Is the work any kind of work which the official body " +
        "publishing it intends to be generally received?",
        question1.get("question"));
    assertEquals("assumed no", question1.get("answer"));
    assertEquals("The answer is always assumed to be no.",
        question1.get("note"));

    Map question2 = (Map) trace.get(1);
    assertEquals("Is the work a court decision or officially issued " +
        "discussion formula?",
        question2.get("question"));
    assertEquals("assumed no", question2.get("answer"));
    assertEquals("The answer is always assumed to be no.",
        question2.get("note"));

    Map question3 = (Map) trace.get(2);
    assertEquals("Is the work an act of parliament which has entered the " +
        "parliamentary process?",
        question3.get("question"));
    assertEquals("assumed no", question3.get("answer"));
    assertEquals("The answer is always assumed to be no.",
        question3.get("note"));

    Map question4 = (Map) trace.get(3);
    assertEquals("Is the work a government directive?",
        question4.get("question"));
    assertEquals("assumed no", question4.get("answer"));
    assertEquals("The answer is always assumed to be no.",
        question4.get("note"));

    Map question5 = (Map) trace.get(4);
    assertEquals("Is the work an official decision or announcement by a " +
        "public authority?",
        question5.get("question"));
    assertEquals("assumed no", question5.get("answer"));
    assertEquals("The answer is always assumed to be no.",
        question5.get("note"));

    Map question6 = (Map) trace.get(5);
    assertEquals("Is the author natural person?", question6.get("question"));
    assertEquals("assumed yes", question6.get("answer"));
    assertEquals("The answer is always assumed to be yes.",
        question6.get("note"));

    Map question7 = (Map) trace.get(6);
    assertEquals("Is the nationality or place of residence of the author " +
        "the European Economic Area?",
        question7.get("question"));
    assertEquals("no", question7.get("answer"));
    assertEquals(null, question7.get("note"));

    Map question8 = (Map) trace.get(7);
    assertEquals("Is the country of origin the European Economic Area?",
        question8.get("question"));
    assertEquals("no", question8.get("answer"));
    assertEquals(null, question8.get("note"));

    Map question9 = (Map) trace.get(8);
    assertEquals("Is the country of origin the Berne TRIPTIS WCT?",
        question9.get("question"));
    assertEquals("assumed yes", question9.get("answer"));
    assertEquals("The answer is always assumed to be yes.",
        question9.get("note"));
  }

  private void mockDdbApiRequest(String url, String resourcePath)
      throws Exception {
    String escapedUrl = escapeUrl(url);
    String response = loadResource(resourcePath);
    MediaType type = responseTypeFromPath(resourcePath);
    mockDdbApi.expect(requestTo(escapedUrl))
        .andRespond(withSuccess(response, type));
  }

  private String escapeUrl(String url) {
    return url.replace("\"", "%22");
  }

  private MediaType responseTypeFromPath(String resourcePath) {
    if (resourcePath.endsWith(".json")) {
      return MediaType.APPLICATION_JSON;
    } else if (resourcePath.endsWith(".xml")) {
      return MediaType.APPLICATION_XML;
    } else {
      throw new IllegalArgumentException();
    }
  }

  private String loadResource(String path) throws Exception {
    try (InputStream stream = getClass().getResourceAsStream(path)) {
      if (stream == null) {
        throw new FileNotFoundException(path);
      }
      // http://stackoverflow.com/a/5445161/547173
      return new Scanner(stream).useDelimiter("\\A").next();
    }
  }
}
