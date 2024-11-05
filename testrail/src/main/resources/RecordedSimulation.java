package computerdatabase;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.AllowList;
import static io.gatling.javaapi.core.CoreDsl.DenyList;
import static io.gatling.javaapi.core.CoreDsl.RawFileBody;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.pause;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://computer-database.gatling.io")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Accept-Encoding", "gzip, deflate"),
    Map.entry("Content-Type", "application/json"),
    Map.entry("Proxy-Connection", "keep-alive"),
    Map.entry("User-Agent", "Mozilla/5.0 (Windows NT 10.0.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.190 Safari/537.36 CreativeCloud/6.4.0.361"),
    Map.entry("X-Goog-Update-AppId", "oimompecagnajdejgnnjijobebaeigek"),
    Map.entry("X-Goog-Update-Interactivity", "bg"),
    Map.entry("X-Goog-Update-Updater", "chromium-116.0.5845.190")
  );
  
  private Map<CharSequence, String> headers_1 = Map.ofEntries(
    Map.entry("Accept", "*/*"),
    Map.entry("Accept-Encoding", "identity"),
    Map.entry("Proxy-Connection", "Keep-Alive"),
    Map.entry("User-Agent", "Microsoft BITS/7.8")
  );
  
  private Map<CharSequence, String> headers_2 = Map.ofEntries(
    Map.entry("Accept", "*/*"),
    Map.entry("Accept-Encoding", "identity"),
    Map.entry("If-Unmodified-Since", "Wed, 04 Sep 2024 02:15:41 GMT"),
    Map.entry("Proxy-Connection", "Keep-Alive"),
    Map.entry("User-Agent", "Microsoft BITS/7.8")
  );
  
  private Map<CharSequence, String> headers_3 = Map.ofEntries(
    Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
    Map.entry("Accept-Encoding", "gzip, deflate, br, zstd"),
    Map.entry("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7"),
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "\"Google Chrome\";v=\"129\", \"Not=A?Brand\";v=\"8\", \"Chromium\";v=\"129\""),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "\"Windows\"")
  );
  
  private Map<CharSequence, String> headers_4 = Map.ofEntries(
    Map.entry("Connection", "Close"),
    Map.entry("User-Agent", "Microsoft NCSI")
  );
  
  private String uri1 = "http://www.msftconnecttest.com/connecttest.txt";
  
  private String uri3 = "http://update.googleapis.com/service/update2/json";
  
  private String uri4 = "http://msedge.b.tlu.dl.delivery.mp.microsoft.com/filestreamingservice/files/0c12dff9-696d-48d4-bbe8-7d8bdad98e65";

  private ScenarioBuilder scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .post(uri3 + "?cup2key=13:ETSC62eQPBdzjDvWT1nXYDpHCMJXLXm2vUlMeDZN_kI&cup2hreq=1066f8336924ff1f28a0742fff9d87b0f91dac3ab2ff0fea9b0799dc1c981fda")
        .headers(headers_0)
        .body(RawFileBody("computerdatabase/recordedsimulation/0000_request.json"))
        .resources(
          http("request_1")
            .head(uri4 + "?P1=1728104938&P2=404&P3=2&P4=KUdccXgREyurgJYdWoh42hOnQj1XBpkNDhYQiYUam19le6BbBS7%2fnP2llZRqz4libseByWx%2fQWUl%2f%2bbl6g%2fuvQ%3d%3d")
            .headers(headers_1),
          http("request_2")
            .get(uri4 + "?P1=1728104938&P2=404&P3=2&P4=KUdccXgREyurgJYdWoh42hOnQj1XBpkNDhYQiYUam19le6BbBS7%2fnP2llZRqz4libseByWx%2fQWUl%2f%2bbl6g%2fuvQ%3d%3d")
            .headers(headers_2)
        ),
      pause(7),
      http("request_3")
        .get("/computers")
        .headers(headers_3),
      pause(4),
      http("request_4")
        .get(uri1)
        .headers(headers_4),
      pause(1),
      http("request_5")
        .get("/computers")
        .headers(headers_3),
      pause(5),
      http("request_6")
        .get("/computers?f=apple")
        .headers(headers_3),
      pause(10),
      http("request_7")
        .get("/computers?p=1&f=apple")
        .headers(headers_3)
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(20))).protocols(httpProtocol);
  }
}
