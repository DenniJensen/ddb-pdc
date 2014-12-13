package de.ddb.pdc;

import de.ddb.pdc.metadata.DdbApiUrls;
import org.junit.Before;
import org.junit.Ignore;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

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
    String ddbUrl = DdbApiUrls.searchUrl("goethe", 0, 3, "relevance", dbbApiKey);
    String ddbResponse = loadJsonReponse("/ddb_search/goethe3");
    mockDdbApi.expect(requestTo(ddbUrl))
        .andRespond(withSuccess(ddbResponse, MediaType.APPLICATION_JSON));

    RestTemplate client = new TestRestTemplate();
    String url = urlPrefix + "/search?q=goethe&max=3";
    List results = client.getForObject(url, List.class);

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

  private String loadJsonReponse(String path) throws Exception {
    InputStream stream = getClass().getResourceAsStream(path + ".json");
    InputStreamReader streamReader = new InputStreamReader(stream);
    try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
      return bufferedReader.readLine();
    }
  }


  @Ignore
  @Test
  public void searchItem() throws Exception {
    String ddbUrl = DdbApiUrls.itemAipUrl("3", dbbApiKey);
    String ddbResponse = loadJsonReponse("/pdc/3");
    mockDdbApi.expect(requestTo(ddbUrl))
            .andRespond(withSuccess(ddbResponse, MediaType.APPLICATION_JSON));
    RestTemplate client = new TestRestTemplate();
    String url = urlPrefix + "/search?q=1";

  }
}
