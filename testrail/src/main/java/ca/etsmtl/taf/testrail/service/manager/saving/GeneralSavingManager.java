package ca.etsmtl.taf.testrail.service.manager.saving;

import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import ca.etsmtl.taf.testrail.model.factory.TestRailProjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GeneralSavingManager {

    @Autowired
    private ProjectSavingManager projectSavingManager;

    public TestRailProject createProject(String projectName) {
        // Create the project
        return TestRailProjectFactory.create(projectName);
    }

    public void saveData(TestRailProject project) {
        // Save the project
        DataResponseFromTR dataResponseFromTR = projectSavingManager.saveData(project);
    }

    public void deleteData(TestRailProject project) {
        // Delete the project
        DataResponseFromTR dataResponseFromTR = projectSavingManager.deleteData(project);
    }

}
