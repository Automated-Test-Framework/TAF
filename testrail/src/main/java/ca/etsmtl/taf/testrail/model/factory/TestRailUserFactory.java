package ca.etsmtl.taf.testrail.model.factory;

import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import ca.etsmtl.taf.testrail.model.entity.TestRailUser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


public class TestRailUserFactory {
    public static TestRailUser create(String name, String password, String email) {
        TestRailUser user = new TestRailUser();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    public static TestRailUser create(JSONObject projectJson) throws JSONException {
        /*
         * Create a TestRailUser object from a JSON object
         * @param projectJson : JSON object containing the user data
         * @return TestRailUser object
         * @throws JSONException if the JSON object is not correct
         * Tests : TODO
         * Assisted by IA (copilot & chatGPT & intellij)
         * */

        TestRailUser user = new TestRailUser();

        // nullable = false
        if (projectJson.has("username") && !projectJson.isNull("username")) {
            user.setUsername(projectJson.getString("username"));
        } else {
            throw new JSONException("username is required");
        }

        if (projectJson.has("password") && !projectJson.isNull("password")) {
            user.setPassword(projectJson.getString("password"));
        } else {
            throw new JSONException("password is required");
        }

        if (projectJson.has("email") && !projectJson.isNull("email")) {
            user.setEmail(projectJson.getString("email"));
        } else {
            throw new JSONException("email is required");
        }

        if (projectJson.has("id") && !projectJson.isNull("id")) {
            user.setTRId(projectJson.getInt("id"));
        }

        return user;
    }
}
