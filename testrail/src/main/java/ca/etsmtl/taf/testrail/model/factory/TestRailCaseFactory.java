package ca.etsmtl.taf.testrail.model.factory;

import ca.etsmtl.taf.testrail.model.entity.TestRailCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
public class TestRailCaseFactory {

    public static TestRailCase create(String title) {
        TestRailCase testRailCase = new TestRailCase();
        testRailCase.setTitle(title);
        return testRailCase;
    }

    public static TestRailCase create(JSONObject projectJson) throws JSONException {
        /*
         * Create a TestRailCase object from a JSON object
         * @param caseJson : JSON object containing the case data
         * @return TestRailCase object
         * @throws JSONException if the JSON object is not correct
         * Tests : TODO
         * Assisted by IA (copilot & chatGPT & intellij)
         * */

        TestRailCase testRailCase = new TestRailCase();

        // nullable = false
        if (projectJson.has("title") && !projectJson.isNull("title")) {
            testRailCase.setTitle(projectJson.getString("title"));
        } else {
            throw new JSONException("title is required");
        }

        if (projectJson.has("id") && !projectJson.isNull("id")) {
            testRailCase.setTRId(projectJson.getInt("id"));
        }

        if (projectJson.has("section_id") && !projectJson.isNull("section_id")) {
            testRailCase.setSectionId(projectJson.getInt("section_id"));
        }

        if (projectJson.has("type_id") && !projectJson.isNull("type_id")) {
            testRailCase.setTypeId(projectJson.getInt("type_id"));
        }

        if (projectJson.has("priority_id") && !projectJson.isNull("priority_id")) {
            testRailCase.setPriorityId(projectJson.getInt("priority_id"));
        }

        if (projectJson.has("milestone_id") && !projectJson.isNull("milestone_id")) {
            testRailCase.setMilestoneId(projectJson.getInt("milestone_id"));
        }

        if (projectJson.has("refs") && !projectJson.isNull("refs")) {
            testRailCase.setRefs(projectJson.getString("refs"));
        }

        if (projectJson.has("created_by") && !projectJson.isNull("created_by")) {
            testRailCase.setCreatedBy(projectJson.getInt("created_by"));
        }

        if (projectJson.has("created_on") && !projectJson.isNull("created_on")) {
            testRailCase.setCreatedOn(Timestamp.valueOf(String.valueOf(projectJson.getLong("created_on"))));
        }

        if (projectJson.has("updated_by") && !projectJson.isNull("updated_by")) {
            testRailCase.setUpdatedBy(projectJson.getInt("updated_by"));
        }

        if (projectJson.has("updated_on") && !projectJson.isNull("updated_on")) {
            testRailCase.setUpdatedOn(Timestamp.valueOf(String.valueOf(projectJson.getLong("updated_on"))));
        }

        if (projectJson.has("estimate") && !projectJson.isNull("estimate")) {
            testRailCase.setEstimate(Timestamp.valueOf(String.valueOf(projectJson.getLong("estimate"))));
        }

        if (projectJson.has("estimate_forecast") && !projectJson.isNull("estimate_forecast")) {
            testRailCase.setEstimateForecast(Timestamp.valueOf(String.valueOf(projectJson.getLong("estimate_forecast"))));
        }

        if (projectJson.has("suite_id") && !projectJson.isNull("suite_id")) {
            testRailCase.setSuiteId(projectJson.getInt("suite_id"));
        }

        if (projectJson.has("template_id") && !projectJson.isNull("template_id")) {
            testRailCase.setTemplateId(projectJson.getInt("template_id"));
        }

        return testRailCase;
    }


}
