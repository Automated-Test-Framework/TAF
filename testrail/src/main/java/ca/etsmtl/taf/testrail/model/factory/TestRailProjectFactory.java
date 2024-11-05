package ca.etsmtl.taf.testrail.model.factory;

import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


public class TestRailProjectFactory {
    public static TestRailProject create(String name) {
        TestRailProject project = new TestRailProject();
        project.setName(name);
        return project;
    }

    public static TestRailProject create(JSONObject projectJson) throws JSONException {
        /*
        * Create a TestRailProject object from a JSON object
        * @param projectJson : JSON object containing the project data
        * @return TestRailProject object
        * @throws JSONException if the JSON object is not correct
        * Tests : TODO
        * Assisted by IA (copilot & chatGPT & intellij)
        * */

        TestRailProject project = new TestRailProject();

        // nullable = false
        if (projectJson.has("name") && !projectJson.isNull("name")) {
            project.setName(projectJson.getString("name"));
        } else {
            throw new JSONException("name is required");
        }

        if (projectJson.has("id") && !projectJson.isNull("id")) {
            project.setTRId(projectJson.getInt("id"));
        }

        if (projectJson.has("announcement") && !projectJson.isNull("announcement")) {
            project.setAnnouncement(projectJson.getString("announcement"));
        }

        if (projectJson.has("show_announcement") && !projectJson.isNull("show_announcement")) {
            project.setShowAnnouncement(projectJson.getBoolean("show_announcement"));
        }

        if (projectJson.has("is_completed") && !projectJson.isNull("is_completed")) {
            project.setIsCompleted(projectJson.getBoolean("is_completed"));
        }

        if (projectJson.has("completed_on") && !projectJson.isNull("completed_on")) {
            project.setCompletedOn(Timestamp.valueOf(String.valueOf(projectJson.getLong("completed_on"))));
        }

        if (projectJson.has("suite_mode") && !projectJson.isNull("suite_mode")) {
            project.setSuiteMode(projectJson.getInt("suite_mode"));
        }

        if (projectJson.has("default_role_id") && !projectJson.isNull("default_role_id")) {
            project.setDefaultRoleId(projectJson.getInt("default_role_id"));
        }

        if (projectJson.has("case_statuses_enabled") && !projectJson.isNull("case_statuses_enabled")) {
            project.setCaseStatusesEnabled(projectJson.getBoolean("case_statuses_enabled"));
        }

        if (projectJson.has("url") && !projectJson.isNull("url")) {
            project.setUrl(projectJson.getString("url"));
        }

        return project;
    }
}
