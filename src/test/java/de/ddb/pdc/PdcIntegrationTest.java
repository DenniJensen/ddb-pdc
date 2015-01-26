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
import org.springframework.test.context.ActiveProfiles;
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
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
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

    Map result = getForObject("/search?q=goethe&max=3", Map.class);
    assertEquals(8756, result.get("maxSearchResults"));

    List items = (List) result.get("ddbItems");
    assertNotNull(items);
    assertEquals(3, items.size());

    String totalUrl;
    String urlSearch;
    Map item1 = (Map) items.get(0);
    assertEquals("TNPFDKO2VDGBZ72RWC6RKDNZYZQZP3XK", item1.get("id"));
    assertEquals("Goethe", item1.get("title"));
    assertEquals("Druckgraphik", item1.get("subtitle"));
    urlSearch = "/binary/TNPFDKO2VDGBZ72RWC6RKDNZYZQZP3XK/list/1.jpg";
    totalUrl = DDB_DOMAIN + urlSearch;
    assertEquals(totalUrl, item1.get("imageUrl"));

    Map item2 = (Map) items.get(1);
    assertEquals("ILL7O3BSG7ZXIZMX6RT23AR7FDT3NUPK", item2.get("id"));
    assertEquals("Goethe", item2.get("title"));
    assertEquals("Zeichnung", item2.get("subtitle"));
    urlSearch = "/binary/ILL7O3BSG7ZXIZMX6RT23AR7FDT3NUPK/list/1.jpg";
    totalUrl = DDB_DOMAIN + urlSearch;
    assertEquals(totalUrl, item2.get("imageUrl"));

    Map item3 = (Map) items.get(2);
    assertEquals("4XUDMRWXBMC7O3FPLATAK2HLCMLBBO22", item3.get("id"));
    assertEquals("Cornelie Goethe", item3.get("title"));
    assertEquals("Druckgraphik", item3.get("subtitle"));
    urlSearch =  "/binary/4XUDMRWXBMC7O3FPLATAK2HLCMLBBO22/list/1.jpg";
    totalUrl = DDB_DOMAIN + urlSearch;
    assertEquals(totalUrl, item3.get("imageUrl"));
  }

  private <T> T getForObject(String path, Class<T> resultClass) {
    RestTemplate client = new TestRestTemplate();
    return client.getForObject(urlPrefix + path, resultClass);
  }

  @Test
  public void determinePublicDomain() throws Exception {
    final String itemId = "UGTZDTFHRNELDDLG2BGYKJMSVIB4XSML";
    final String authorId = "http://d-nb.info/gnd/118540238";
    final String placeOfBirthId = "http://d-nb.info/gnd/4018118-2";

    String aipUrl = ApiUrls.itemAipUrl(itemId, dbbApiKey);
    mockDdbApiRequest(aipUrl, "/ddb_items_aip/" + itemId + ".xml");
    String authorUrl = ApiUrls.dnbUrl(authorId);
    mockDdbApiRequest(authorUrl, "/dnb/118540238.xml");
    String placeOfBirthUrl = ApiUrls.dnbUrl(placeOfBirthId);
    mockDdbApiRequest(placeOfBirthUrl, "/dnb/4018118-2.xml");

    Map result = getForObject("/pdc/" + itemId, Map.class);

    assertEquals(true, result.get("publicDomain"));

    List trace = (List) result.get("trace");
    assertEquals(10, trace.size());

    Map question1 = (Map) trace.get(0);
    assertEquals("Ist das Werk zur öffentlichen Verbreitung bestimmt?",
        question1.get("question"));
    assertEquals("assumed no", question1.get("answer"));
    assertEquals("Es wird immer davon ausgegangen, dass die Antwort nein ist. "
        + "Ein offizielles Werk, das für die Öffentlichkeit bestimmt ist, ist "
        + "immer ohne Beschränkungen öffentlich zugänglich.",
        question1.get("note"));

    Map question2 = (Map) trace.get(1);
    assertEquals("Ist das Werk ein Gerichtsurteil oder ein offiziell "
            + "veröffentlichter Beschluss?",
        question2.get("question"));
    assertEquals("assumed no", question2.get("answer"));
    assertEquals("Es wird immer davon ausgegangen, dass die Antwort nein ist. "
        + "Ein Gerichtsurteil oder ein Beschluss ist ohne weitere "
        + "Beschränkungen öffentlich zugänglich.",
        question2.get("note"));

    Map question3 = (Map) trace.get(2);
    assertEquals("Ist dieses Werk ein Gesetz, dass sich im parlamentarischen "
        + "Entscheidungsprozess befindet?",
        question3.get("question"));
    assertEquals("assumed no", question3.get("answer"));
    assertEquals("Es wird immer davon ausgegangen, dass die Antwort nein ist. "
        + "Ein vom Parlament beschlossenes Gesetz ist ohne weitere "
        + "Beschränkungen öffentlich zugänglich.",
        question3.get("note"));

    Map question4 = (Map) trace.get(3);
    assertEquals("Ist das Werk eine Anordnung?",
        question4.get("question"));
    assertEquals("assumed no", question4.get("answer"));
    assertEquals("Es wird immer davon ausgegangen, dass die Antwort nein ist. "
        + "Ein Regierungsbeschluss ist ohne weitere Beschränkungen öffentlich "
        + "zugänglich.",
        question4.get("note"));

    Map question5 = (Map) trace.get(4);
    assertEquals("Ist dieses Werk eine offizielle Entscheidung oder eine Bekanntgabe einer"
        + " öffentlichen Autorität?",
        question5.get("question"));
    assertEquals("assumed no", question5.get("answer"));
    assertEquals("Es wird immer davon ausgegangen, dass die Antwort nein ist. "
        + "Eine Bekanntgabe einer öffentlichen Autorität ist ohne "
        + "weitere Beschränkungen öffentlich zugänglich.",
        question5.get("note"));

    Map question6 = (Map) trace.get(5);
    assertEquals("Ist der Autor eine natürliche Person?",
        question6.get("question"));
    assertEquals("assumed yes", question6.get("answer"));
    assertEquals("Es wird immer davon ausgegangen, dass die Antwort ja ist.",
        question6.get("note"));

    Map question7 = (Map) trace.get(6);
    assertEquals("Ist die Staatsangehörigkeit oder der Wohnort des Autors der "
        + "Europäische Wirtschaftsraum?",
        question7.get("question"));
    assertEquals("yes", question7.get("answer"));
    assertEquals("Autor Goethe, Johann Wolfgang von ist aus de, welches Teil "
        + "der EU ist.",
        question7.get("note"));

    Map question8 = (Map) trace.get(7);
    assertEquals("Wurde das Werk veröffentlicht?",
        question8.get("question"));
    assertEquals("assumed yes", question8.get("answer"));
    assertEquals("Es wird davon ausgegangen, dass das Werk öffentlich "
        + "zugänglich ist, da es bekannt ist.",
        question8.get("note"));

    Map question9 = (Map) trace.get(8);
    assertEquals("Wurde dieses Werk innerhalb von 70 Jahren nach dem Tod des "
        + "letzten überlebenden Autor veröffentlicht?",
        question9.get("question"));
    assertEquals("yes", question9.get("answer"));
    assertEquals("Das Werk wurde (spätestens) 1849 veröffentlicht. Der Autor, "
        + "der am längsten überlebt hat, starb 1832. Es sind also 17 Jahre "
        + "vergangen.",
        question9.get("note"));

    Map question10 = (Map) trace.get(9);
    assertEquals("Ist der letzte überlebende Autor seit mehr als 70 Jahre tot?",
        question10.get("question"));
    assertEquals("yes", question10.get("answer"));
    assertEquals("Alle Autoren starben vor oder in 1832: Goethe, Johann "
        + "Wolfgang von starb 1832.",
        question10.get("note"));
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
