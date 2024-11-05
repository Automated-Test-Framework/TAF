package ca.etsmtl.taf.testrail.service.collector;

import ca.etsmtl.taf.testrail.model.entity.GatlingTestCase;
import ca.etsmtl.taf.testrail.model.entity.TestRailCase;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TempTestCaseGatling {
    private static final Logger logger = Logger.getLogger(TempTestCaseGatling.class.getName());

    static {
        logger.setLevel(Level.OFF);
        try {
            String currentPath = System.getProperty("user.dir");
            logger.info("Chemin de l'exécution : " + currentPath);

            File logDir = new File("./logs");
            if (!logDir.exists()) {
                logDir.mkdirs(); // Crée le dossier "logs" si inexistant
            }

            FileHandler fileHandler = new FileHandler("./logs/TempTestCaseGatling.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.severe("Erreur de configuration du FileHandler.");
        }
    }

    private String scenarioName;
    private String baseUrl;
    private String userInjection;
    public GatlingTestCase parse(String SimulationFile) {
        logger.setLevel(Level.INFO);
        logger.info("SimulationFile=" + SimulationFile);
        String gatlingScript = "";
        try (InputStream inputStream = TempTestCaseGatling.class.getClassLoader().getResourceAsStream(SimulationFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gatlingScript += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern baseUrlPattern = Pattern.compile("baseUrl\\(\"([^\"]+)\"\\)");
        Pattern scenarioTitlePattern = Pattern.compile("scenario\\(\"([^\"]+)\"\\)");
        Pattern userInjectionPattern = Pattern.compile("setUp(\\(.*?);", Pattern.DOTALL);

        // Extract base URL
        Matcher baseUrlMatcher = baseUrlPattern.matcher(gatlingScript);
        if (baseUrlMatcher.find()) {
            this.baseUrl = baseUrlMatcher.group(1);
            logger.info("Base URL: " + baseUrlMatcher.group(1));
        } else {
            logger.warning("Base URL not found in the Gatling script.");
        }

        // Extract scenario title
        Matcher scenarioTitleMatcher = scenarioTitlePattern.matcher(gatlingScript);
        if (scenarioTitleMatcher.find()) {
            this.scenarioName = scenarioTitleMatcher.group(1);
            logger.info("Scenario Title: " + scenarioTitleMatcher.group(1));
        } else {
            logger.warning("Scenario title not found in the Gatling script.");
        }


        // Extract user injection
        Matcher userInjectionMatcher = userInjectionPattern.matcher(gatlingScript);
        if (userInjectionMatcher.find()) {
            this.userInjection = userInjectionMatcher.group(1).replaceAll("\\s", "");
            logger.info("User Injection: " + this.userInjection);
        } else {
            logger.warning("User injection not found in the Gatling script.");
        }

        // Stock in GatlingTestCase object
        GatlingTestCase gatlingTestCase = new GatlingTestCase();
        gatlingTestCase.setScenarioName(this.scenarioName);
        gatlingTestCase.setBaseUrl(this.baseUrl);
        gatlingTestCase.setUserInjection(this.userInjection);
        logger.info("GatlingTestCase: " + gatlingTestCase);

        return gatlingTestCase;
    }

    public GatlingTestCase parse() {
        return parse("SimpleSimulation.java");
    }

    public TestRailCase toTestRailCase(GatlingTestCase gatlingTestCase) {
        TestRailCase testRailCase = new TestRailCase();
        testRailCase.setTitle(gatlingTestCase.getScenarioName());
        testRailCase.setSectionId(1); // Default section
        return testRailCase;
    }
}