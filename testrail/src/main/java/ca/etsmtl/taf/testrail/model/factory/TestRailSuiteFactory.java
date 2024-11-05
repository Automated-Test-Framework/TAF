package ca.etsmtl.taf.testrail.model.factory;

import ca.etsmtl.taf.testrail.model.entity.TestRailSuite;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

public class TestRailSuiteFactory {
    // TODO
    public static TestRailSuite create(String name) {
        TestRailSuite suite = new TestRailSuite();
        suite.setName(name);
        return suite;
    }

    public static TestRailSuite create(JSONObject projectJson) throws JSONException {
        /*
         * Create a TestRailSuite object from a JSON object
         * @param projectJson : JSON object containing the project data
         * @return TestRailSuite object
         * @throws JSONException if the JSON object is not correct
         * Tests : TODO
         * Assisted by IA (copilot & chatGPT & intellij)
         * */

        TestRailSuite suite = new TestRailSuite();

        // nullable = false
        if (projectJson.has("name") && !projectJson.isNull("name")) {
            suite.setName(projectJson.getString("name"));
        } else {
            throw new JSONException("name is required");
        }

        if (projectJson.has("id") && !projectJson.isNull("id")) {
            suite.setTRId(projectJson.getInt("id"));
        }

        if (projectJson.has("description") && !projectJson.isNull("description")) {
            suite.setDescription(projectJson.getString("description"));
        }

        if (projectJson.has("is_baseline") && !projectJson.isNull("is_baseline")) {
            suite.setIsBaseline(projectJson.getBoolean("is_baseline"));
        }

        if (projectJson.has("is_completed") && !projectJson.isNull("is_completed")) {
            suite.setIsCompleted(projectJson.getBoolean("is_completed"));
        }

        if (projectJson.has("completed_on") && !projectJson.isNull("completed_on")) {
            suite.setCompletedOn(Timestamp.valueOf(String.valueOf(projectJson.getLong("completed_on"))));
        }

        if (projectJson.has("url") && !projectJson.isNull("url")) {
            suite.setUrl(projectJson.getString("url"));
        }

        if (projectJson.has("is_master") && !projectJson.isNull("is_master")) {
            suite.setIsMaster(projectJson.getBoolean("is_master"));
        }

        return suite;
    }
}
