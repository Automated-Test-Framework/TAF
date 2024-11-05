package ca.etsmtl.taf.testrail.service.manager.query;

import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import ca.etsmtl.taf.testrail.model.factory.TestRailProjectFactory;
import ca.etsmtl.taf.testrail.service.manager.saving.DataResponseFromTR;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class ProjectQueryTestRail implements QueryTestRail {

    /*
     * Class that contains common requests about TestRail projects
     * This class doesn't save the data, it only queries TestRail
     * This class doesn't check if data is already loaded or in the database
     * Class build assisted by IA (copilot & chatGPT & intellij)
     * Tests : TODO
     * */

    private final HttpSenderToTestRail httpSenderToTestRail;


    public ProjectQueryTestRail() {
        this.httpSenderToTestRail = new HttpSenderToTestRail(); // TODO : adapt to each user and environment
    }


    @Override
    public DataResponseFromTR add(String projectName) {
        /*
         * Add a new project to TestRail
         * @param projectName : name of the project to add
         * @return TestRailProject object
         * @Use getByName to get the project added
         * Tests : TODO
         * Assisted by IA (copilot & chatGPT & intellij)
         * */

        // Send a post request to add a new project
        String route = "add_project";
        SendResultData result = httpSenderToTestRail.postSender(route, "{\"name\":\"" + projectName + "\"}");

        // Check if the response is successful

        if (result.getStatusCode() != 200) {
            System.err.println("Erreur lors de l'ajout du projet dans TR");
            return new DataResponseFromTR(1, null);
        }

        // Convert the JSON response to a TestRailData object
        try {
            JSONObject projectJson = new JSONObject(result.getResponse().body());
            TestRailProject project = TestRailProjectFactory.create(projectJson); // Create a TestRailData object (TestRailProject) from the JSON*
            return new DataResponseFromTR(0, project); // Return the project and the status code of the response
        } catch (JSONException e) {
            e.printStackTrace(); // TODO: Améliorer la gestion des erreurs
            return new DataResponseFromTR(2, null);
        }
    }

    @Override
    public DataResponseFromTR delete(int projectId) {
        /*
         * Delete a project from TestRail
         * @param projectId : id of the project to delete
         * @return TestRailProject object
         * Tests : TODO
         * Assisted by IA (copilot & chatGPT & intellij)
         * */

        // Send a post request to delete a project
        String route = "delete_project/" + projectId;
        SendResultData result = httpSenderToTestRail.postSender(route, "");

        // Check if the response is successful
        if (result.getStatusCode() != 200) {
            System.err.println("Erreur lors de la suppression du projet dans TR");
            return new DataResponseFromTR(1, null);
        }

        return new DataResponseFromTR(0, null);
    }

}