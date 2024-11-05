package ca.etsmtl.taf.testrail.service.data;

import ca.etsmtl.taf.testrail.TestConfig;
import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import ca.etsmtl.taf.testrail.model.factory.TestRailProjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = TestConfig.class)
@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
public class TestUnitTestRailProject {

    private TestRailProject testRailProject;
    private TestRailProject testRailProject2;
    private TestRailProject testRailProject3;
    private String defaultName;

    @Autowired
    private TestRailProjectService testRailProjectService;

    @BeforeEach
    public void setUp() {
        testRailProjectService.deleteAll(); // Drop all data from the table
        defaultName = "defaultName_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);
        testRailProject = TestRailProjectFactory.create(defaultName);
        testRailProject2 = TestRailProjectFactory.create(defaultName + "_2");
        testRailProject3 = TestRailProjectFactory.create(defaultName + "_3");
    }

    @Test
    public void testSave() {
        // Arrange
        testRailProject.setTRId(1);

        // Act
        TestRailProject savedTestRailProject = testRailProjectService.save(testRailProject);

        // Assert
        assertNotNull(savedTestRailProject);
        assertEquals(testRailProject, savedTestRailProject);
        assertEquals(defaultName, savedTestRailProject.getName());
    }

    public void beforeUpdate() {
        testRailProject.setTRId(2);
        testRailProjectService.save(testRailProject);
    }

    @Test
    public void testUpdateId() {
        /* Arrange */
        beforeUpdate();
        testRailProject.setTRId(3);

        /* Act */
        TestRailProject savedTestRailProject = testRailProjectService.save(testRailProject);

        /* Assert */
        assertNotNull(savedTestRailProject);
        assertEquals(testRailProject, savedTestRailProject);
        assertEquals(defaultName, savedTestRailProject.getName());
        assertEquals(3, savedTestRailProject.getTRId());
    }

    @Test
    public void testUpdateName() {
        /* Arrange */
        beforeUpdate();
        String updatedName = "updatedName_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailProject.setName(updatedName);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedName, updatedTestRailProject.getName());
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedTestRailProject, getTestRailProject);
    }

    @Test
    public void testUpdateAnnouncement() {
        /* Arrange */
        beforeUpdate();
        String updatedAnnouncement = "updatedAnnouncement_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailProject.setAnnouncement(updatedAnnouncement);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedAnnouncement, updatedTestRailProject.getAnnouncement());
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedTestRailProject, getTestRailProject);
    }

    @Test
    public void testUpdateShowAnnouncement() {
        /* Arrange */
        beforeUpdate();
        boolean updatedShowAnnouncement = true;

        /* Act */
        testRailProject.setShowAnnouncement(updatedShowAnnouncement);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedShowAnnouncement, updatedTestRailProject.getShowAnnouncement());
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedTestRailProject, getTestRailProject);
    }

    @Test
    public void testUpdateIsCompleted() {
        /* Arrange */
        beforeUpdate();
        boolean updatedIsCompleted = true;

        /* Act */
        testRailProject.setIsCompleted(updatedIsCompleted);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedIsCompleted, updatedTestRailProject.getIsCompleted());
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedTestRailProject, getTestRailProject);

    }

    @Test
    public void testUpdateCompletedOn() {
        /* Arrange */
        beforeUpdate();
        Timestamp updatedCompletedOn = new Timestamp(System.currentTimeMillis());

        /* Act */
        testRailProject.setCompletedOn(updatedCompletedOn);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedCompletedOn, updatedTestRailProject.getCompletedOn());
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedTestRailProject, getTestRailProject);

    }

    @Test
    public void testUpdateSuiteMode() {
        /* Arrange */
        beforeUpdate();
        int updatedSuiteMode = 1;

        /* Act */
        testRailProject.setSuiteMode(updatedSuiteMode);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedSuiteMode, updatedTestRailProject.getSuiteMode());
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedTestRailProject, getTestRailProject);
    }

    @Test
    public void testUpdateDefaultRoleId() {
        /* Arrange */
        beforeUpdate();
        int updatedDefaultRoleId = 1;

        /* Act */
        testRailProject.setDefaultRoleId(updatedDefaultRoleId);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedDefaultRoleId, updatedTestRailProject.getDefaultRoleId());
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedTestRailProject, getTestRailProject);
    }

    @Test
    public void testUpdateCaseStatusesEnabled() {
        /* Arrange */
        beforeUpdate();
        boolean updatedCaseStatusesEnabled = true;

        /* Act */
        testRailProject.setCaseStatusesEnabled(updatedCaseStatusesEnabled);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedCaseStatusesEnabled, updatedTestRailProject.getCaseStatusesEnabled());
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedTestRailProject, getTestRailProject);
    }

    @Test
    public void testUpdateUrl() {
        /* Arrange */
        beforeUpdate();
        String updatedUrl = "updatedUrl_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailProject.setUrl(updatedUrl);
        TestRailProject updatedTestRailProject = testRailProjectService.save(testRailProject);
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailProject);
        assertEquals(2, updatedTestRailProject.getTRId());
        assertEquals(updatedUrl, updatedTestRailProject.getUrl());
        assertEquals(testRailProject, updatedTestRailProject);
        assertEquals(updatedTestRailProject, getTestRailProject);
    }

    @Test
    public void testFindByTafId() {
        /* Arrange */
        testRailProjectService.save(testRailProject);
        testRailProjectService.save(testRailProject2);
        testRailProjectService.save(testRailProject3);
        Long tafId = testRailProject.getId();
        Long tafId2 = testRailProject2.getId();
        Long tafId3 = testRailProject3.getId();

        /* Act */
        TestRailProject getTestRailProject = testRailProjectService.findByTafId(tafId).get();
        TestRailProject getTestRailProject2 = testRailProjectService.findByTafId(tafId2).get();
        TestRailProject getTestRailProject3 = testRailProjectService.findByTafId(tafId3).get();

        /* Assert */
        assertNotNull(getTestRailProject);
        assertNotNull(getTestRailProject2);
        assertNotNull(getTestRailProject3);

        assertEquals(testRailProject, getTestRailProject);
        assertEquals(testRailProject2, getTestRailProject2);
        assertEquals(testRailProject3, getTestRailProject3);
    }

    @Test
    public void testFindByTRId() {
        /* Arrange */
        int trId = (new Random()).nextInt(Integer.MAX_VALUE);
        int trId2 = (new Random()).nextInt(Integer.MAX_VALUE);
        int trId3 = (new Random()).nextInt(Integer.MAX_VALUE);


        testRailProject.setTRId(trId);
        testRailProject2.setTRId(trId2);
        testRailProject3.setTRId(trId3);

        testRailProjectService.save(testRailProject);
        testRailProjectService.save(testRailProject2);
        testRailProjectService.save(testRailProject3);

        /* Act */
        TestRailProject getTestRailProject = testRailProjectService.findByTRId(trId).get();
        TestRailProject getTestRailProject2 = testRailProjectService.findByTRId(trId2).get();
        TestRailProject getTestRailProject3 = testRailProjectService.findByTRId(trId3).get();

        /* Assert */
        assertNotNull(getTestRailProject);
        assertNotNull(getTestRailProject2);
        assertNotNull(getTestRailProject3);

        assertEquals(trId, getTestRailProject.getTRId());
        assertEquals(trId2, getTestRailProject2.getTRId());
        assertEquals(trId3, getTestRailProject3.getTRId());

        assertEquals(testRailProject, getTestRailProject);
        assertEquals(testRailProject2, getTestRailProject2);
        assertEquals(testRailProject3, getTestRailProject3);
    }

    @Test
    public void testFindByName() {
        /* Arrange */
        testRailProjectService.save(testRailProject);
        testRailProjectService.save(testRailProject2);
        testRailProjectService.save(testRailProject3);

        /* Act */
        TestRailProject getTestRailProject = testRailProjectService.findByName(testRailProject.getName()).get();
        TestRailProject getTestRailProject2 = testRailProjectService.findByName(testRailProject2.getName()).get();
        TestRailProject getTestRailProject3 = testRailProjectService.findByName(testRailProject3.getName()).get();

        /* Assert */
        assertNotNull(getTestRailProject);
        assertNotNull(getTestRailProject2);
        assertNotNull(getTestRailProject3);

        assertEquals(testRailProject, getTestRailProject);
        assertEquals(testRailProject2, getTestRailProject2);
        assertEquals(testRailProject3, getTestRailProject3);
    }


    @Test
    public void testDeleteByTafId() {
        /* Arrange */
        testRailProjectService.save(testRailProject);
        testRailProjectService.save(testRailProject2);
        testRailProjectService.save(testRailProject3);

        Long tafId = testRailProject.getId();
        Long tafId2 = testRailProject2.getId();
        Long tafId3 = testRailProject3.getId();

        /* Act */
        testRailProjectService.deleteByTafId(tafId3);
        testRailProjectService.deleteByTafId(tafId);
        testRailProjectService.deleteByTafId(tafId2);

        /* Assert */
        assertTrue(testRailProjectService.findByTafId(tafId).isEmpty());
        assertTrue(testRailProjectService.findByTafId(tafId2).isEmpty());
        assertTrue(testRailProjectService.findByTafId(tafId3).isEmpty());
    }
}