package ca.etsmtl.taf.testrail.service.manager.saving;

import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import ca.etsmtl.taf.testrail.service.data.TestRailProjectService;
import ca.etsmtl.taf.testrail.service.manager.query.ProjectQueryTestRail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectSavingManager {

    @Autowired
    private ProjectQueryTestRail projectQueryTestRail;

    @Autowired
    private TestRailProjectService testRailProjectService;

    private DataResponseFromTR newData(TestRailProject project) {
        // Send the project to the TestRail

        TestRailProject trProject;

        DataResponseFromTR response = projectQueryTestRail.add(project.getName()); // TODO : Improve by adding other fields

        if (response.getStatusCode() == 0) {
            trProject = response.getData();
            trProject.setIsSaved(true);
            trProject = testRailProjectService.save(trProject);// Save the project in the database
            return new DataResponseFromTR(0, trProject);
        } else {
            /*
             * Even if the project is not added to TestRail, we can save it in the database to allow the user to add it later
             * */
            trProject = project;
            trProject.setIsSaved(false);
            testRailProjectService.save(trProject); // Save the project in the database
            return new DataResponseFromTR(1, trProject); // TODO : Améliorer la gestion des erreurs
        }
    }

    protected DataResponseFromTR saveData(TestRailProject project) {
        // Check if the project already exists in the database
        Optional<TestRailProject> trProject = testRailProjectService.findByName(project.getName());

        if (trProject.isPresent()) {
            // The project already exists in the database
            // TODO : depending on if the project is the same or if the project isn't saved in TestRail, we can update the project
            return new DataResponseFromTR(2, trProject.get());
        } else {
            // The project doesn't exist in the database
            return newData(project);
        }

    }

    protected DataResponseFromTR deleteData(TestRailProject project) {
        // Check if the project already exists in the database
        Optional<TestRailProject> trProject = testRailProjectService.findByName(project.getName());

        if (trProject.isPresent()) {
            // The project already exists in the database
            if (trProject.get().getTRId() != null && trProject.get().getIsSaved()) {
                // The project is saved in TestRail delete it
                DataResponseFromTR response = projectQueryTestRail.delete(trProject.get().getTRId());
                if (response.getStatusCode() == 0) {
                    // The project is deleted from TestRail
                    testRailProjectService.deleteByTafId(trProject.get().getId());
                    return new DataResponseFromTR(0, null);
                } else {
                    // The project is not deleted from TestRail
                    // TODO : Améliorer la gestion des erreurs
                    System.err.println("Erreur lors de la suppression du projet dans TR " + response.getStatusCode());
                    return new DataResponseFromTR(1, null);
                }
            } else {
                // The project is not saved in TestRail
                testRailProjectService.deleteByTafId(trProject.get().getId());
                return new DataResponseFromTR(0, null);
            }
        }
        // The project doesn't exist in the database
        return new DataResponseFromTR(2, null);
    }
}
