package ca.etsmtl.taf.testrail.service.collector;

import ca.etsmtl.taf.testrail.model.entity.GatlingTestCase;
import ca.etsmtl.taf.testrail.model.entity.TestRailCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ContextConfiguration(classes = TestConfig.class)
//@DataJpaTest(properties = {
//        "spring.jpa.hibernate.ddl-auto=create-drop",
//        "spring.datasource.url=jdbc:h2:mem:testdb"
//})
public class TestTempTestCaseGatling {
    private String defaultName;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void test() {
        TempTestCaseGatling tempTestCaseGatling = new TempTestCaseGatling();
        GatlingTestCase gatlingTestCase = tempTestCaseGatling.parse();
        assertNotNull(gatlingTestCase);
        assertEquals("My First Scenario", gatlingTestCase.getScenarioName());
        assertEquals("https://wikijs.fornoff.fr", gatlingTestCase.getBaseUrl());
        assertEquals("(Scenario.injectOpen(rampUsers(10).during(5))).protocols(httpProtocol)", gatlingTestCase.getUserInjection());
    }

    @Test
    public void testWithFileInRessources() {
        String fileName = "SimpleSimulation.java";
        TempTestCaseGatling tempTestCaseGatling = new TempTestCaseGatling();
        GatlingTestCase gatlingTestCase = tempTestCaseGatling.parse(fileName);
        assertNotNull(gatlingTestCase);
        assertEquals("My First Scenario", gatlingTestCase.getScenarioName());
        assertEquals("https://wikijs.fornoff.fr", gatlingTestCase.getBaseUrl());
        assertEquals("(Scenario.injectOpen(rampUsers(10).during(5))).protocols(httpProtocol)", gatlingTestCase.getUserInjection());
    }

    @Test
    public void testToTestRailCase() {
        TempTestCaseGatling tempTestCaseGatling = new TempTestCaseGatling();
        GatlingTestCase gatlingTestCase = tempTestCaseGatling.parse();
        assertNotNull(gatlingTestCase);

        TestRailCase testRailCase = tempTestCaseGatling.toTestRailCase(gatlingTestCase);
        assertEquals("My First Scenario", testRailCase.getTitle());
        assertEquals(1, testRailCase.getSectionId());
    }

    @Test
    public void testRecordedSimulation() {
        String fileName = "RecordedSimulation.java";
        TempTestCaseGatling tempTestCaseGatling = new TempTestCaseGatling();
        GatlingTestCase gatlingTestCase = tempTestCaseGatling.parse(fileName);
        assertNotNull(gatlingTestCase);
        assertEquals("RecordedSimulation", gatlingTestCase.getScenarioName());
        assertEquals("https://computer-database.gatling.io", gatlingTestCase.getBaseUrl());
        assertEquals("(scn.injectOpen(atOnceUsers(20))).protocols(httpProtocol)", gatlingTestCase.getUserInjection());
    }

    @Test
    public void testComputerDatabaseSimulation() {
        String fileName = "ComputerDatabaseSimulation.java";
        TempTestCaseGatling tempTestCaseGatling = new TempTestCaseGatling();
        GatlingTestCase gatlingTestCase = tempTestCaseGatling.parse(fileName);
        assertNotNull(gatlingTestCase);
        assertEquals("Users", gatlingTestCase.getScenarioName());
        assertEquals("https://computer-database.gatling.io", gatlingTestCase.getBaseUrl());
        assertEquals("(users.injectOpen(rampUsers(10).during(10)),admins.injectOpen(rampUsers(2).during(10))).protocols(httpProtocol)", gatlingTestCase.getUserInjection());
    }
}