package computerdatabase;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class SimpleSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://wikijs.fornoff.fr")
                    .acceptHeader("application/json")
                    .contentTypeHeader("application/json");

    ScenarioBuilder Scenario = scenario("My First Scenario")
            .exec(http("Request 1")
                    .get("/computers/"));

    {
        setUp(
                Scenario.injectOpen(rampUsers(10).during(5))
        ).protocols(httpProtocol);
    }
}