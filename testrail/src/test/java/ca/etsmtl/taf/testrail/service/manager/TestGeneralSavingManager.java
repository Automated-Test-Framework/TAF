package ca.etsmtl.taf.testrail.service.manager;


import ca.etsmtl.taf.testrail.TestConfig;
import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import ca.etsmtl.taf.testrail.service.data.TestRailProjectService;
import ca.etsmtl.taf.testrail.service.manager.saving.GeneralSavingManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = TestConfig.class)
@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
public class TestGeneralSavingManager {
    @Autowired
    private GeneralSavingManager generalSavingManager;

    @Autowired
    private TestRailProjectService testRailProjectService;

    @Test
    public void mainStory() {
        String name = "GeneralSavingManager" + System.currentTimeMillis();
        generalSavingManager.saveData(generalSavingManager.createProject(name));
        TestRailProject project = testRailProjectService.findByName(name).orElse(null);
        assertNotNull(project);
        assertEquals(name, project.getName());
        assertEquals(true, project.getIsSaved()); // TODO : Only if the connection to TestRail is successful

        generalSavingManager.deleteData(project);
        project = testRailProjectService.findByName(name).orElse(null);
        assertNull(project);


    }

}
