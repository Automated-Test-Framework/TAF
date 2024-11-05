package ca.etsmtl.taf.testrail.service.data;

import ca.etsmtl.taf.testrail.TestConfig;
import ca.etsmtl.taf.testrail.model.entity.TestRailCase;
import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import ca.etsmtl.taf.testrail.model.entity.TestRailSuite;
import ca.etsmtl.taf.testrail.model.factory.TestRailCaseFactory;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = TestConfig.class)
@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
public class TestUnitTestRailCase {

    private TestRailCase testRailCase;
    private TestRailCase testRailCase2;
    private TestRailCase testRailCase3;
    private String defaultTitle;

    @Autowired
    private TestRailCaseService testRailCaseService;

    @BeforeEach
    public void setUp() {
        testRailCaseService.deleteAll(); // Drop all data from the table
        defaultTitle = "defaultName_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);
        testRailCase = TestRailCaseFactory.create(defaultTitle);
        testRailCase2 = TestRailCaseFactory.create(defaultTitle + "_2");
        testRailCase3 = TestRailCaseFactory.create(defaultTitle + "_3");
    }

    @Test
    public void testSave() {
        // Arrange
        testRailCase.setTRId(1);

        // Act
        TestRailCase savedTestRailCase = testRailCaseService.save(testRailCase);

        // Assert
        assertNotNull(savedTestRailCase);
        assertEquals(testRailCase, savedTestRailCase);
        assertEquals(defaultTitle, savedTestRailCase.getTitle());
    }

    public void beforeUpdate() {
        testRailCase.setTRId(2);
        testRailCaseService.save(testRailCase);
    }

    @Test
    public void testUpdateId() {
        // Arrange
        beforeUpdate();
        testRailCase.setTRId(3);

        // Act
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(defaultTitle, updatedTestRailCase.getTitle());
        assertEquals(3, updatedTestRailCase.getTRId());
    }

    @Test
    public void testUpdateTitle() {
        // Arrange
        beforeUpdate();
        String newTitle = "defaultTitle_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        // Act
        testRailCase.setTitle(newTitle);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(2, updatedTestRailCase.getTRId());
        assertEquals(newTitle, updatedTestRailCase.getTitle());
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
    }

    @Test
    public void testUpdateSectionId(){
        // Arrange
        beforeUpdate();
        int newSectionId = 4;

        // Act
        testRailCase.setSectionId(newSectionId);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
        assertEquals(4, updatedTestRailCase.getSectionId());
        assertEquals(newSectionId, getTestRailCase.getSectionId());
    }

    @Test
    public void testUpdateTypeId(){
        // Arrange
        beforeUpdate();
        int newTypeId = 4;

        // Act
        testRailCase.setTypeId(newTypeId);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
        assertEquals(4, updatedTestRailCase.getTypeId());
        assertEquals(newTypeId, getTestRailCase.getTypeId());
    }

    @Test
    public void testUpdatePriorityId(){
        // Arrange
        beforeUpdate();
        int newPriorityId = 4;

        // Act
        testRailCase.setPriorityId(newPriorityId);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
        assertEquals(4, updatedTestRailCase.getPriorityId());
        assertEquals(newPriorityId, getTestRailCase.getPriorityId());
    }

    @Test
    public void testUpdateMilestoneId(){
        // Arrange
        beforeUpdate();
        int newMilestoneId = 4;

        // Act
        testRailCase.setMilestoneId(newMilestoneId);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
        assertEquals(4, updatedTestRailCase.getMilestoneId());
        assertEquals(newMilestoneId, getTestRailCase.getMilestoneId());
    }

    @Test
    public void testUpdateRefs() {
        // Arrange
        beforeUpdate();
        String newRefs = "defaultRefs_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        // Act
        testRailCase.setTitle(newRefs);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(2, updatedTestRailCase.getTRId());
        assertEquals(newRefs, updatedTestRailCase.getTitle());
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
    }

    @Test
    public void testUpdateCreatedBy(){
        // Arrange
        beforeUpdate();
        int newCreatedBy = 4;

        // Act
        testRailCase.setCreatedBy(newCreatedBy);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
        assertEquals(4, updatedTestRailCase.getCreatedBy());
        assertEquals(newCreatedBy, getTestRailCase.getCreatedBy());
    }

    @Test
    public void testUpdateCreatedOn() {
        /* Arrange */
        beforeUpdate();
        Timestamp updatedCompletedOn = new Timestamp(System.currentTimeMillis());

        /* Act */
        testRailCase.setCreatedOn(updatedCompletedOn);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase= testRailCaseService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailCase);
        assertEquals(2, updatedTestRailCase.getTRId());
        assertEquals(updatedCompletedOn, updatedTestRailCase.getCreatedOn());
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
    }

    @Test
    public void testUpdateUpdatedBy(){
        // Arrange
        beforeUpdate();
        int newUpdatedBy = 4;

        // Act
        testRailCase.setUpdatedBy(newUpdatedBy);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
        assertEquals(4, updatedTestRailCase.getUpdatedBy());
        assertEquals(newUpdatedBy, getTestRailCase.getUpdatedBy());
    }

    @Test
    public void testUpdateUpdatedOn() {
        /* Arrange */
        beforeUpdate();
        Timestamp updatedUpdatedOn = new Timestamp(System.currentTimeMillis());

        /* Act */
        testRailCase.setUpdatedOn(updatedUpdatedOn);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase= testRailCaseService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailCase);
        assertEquals(2, updatedTestRailCase.getTRId());
        assertEquals(updatedUpdatedOn, updatedTestRailCase.getUpdatedOn());
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
    }

    @Test
    public void testUpdateEstimate() {
        /* Arrange */
        beforeUpdate();
        Timestamp updatedEstimate = new Timestamp(System.currentTimeMillis());

        /* Act */
        testRailCase.setEstimate(updatedEstimate);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase= testRailCaseService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailCase);
        assertEquals(2, updatedTestRailCase.getTRId());
        assertEquals(updatedEstimate, updatedTestRailCase.getEstimate());
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
    }

    @Test
    public void testUpdateEstimateForecast() {
        /* Arrange */
        beforeUpdate();
        Timestamp updatedEstimateForecast = new Timestamp(System.currentTimeMillis());

        /* Act */
        testRailCase.setEstimateForecast(updatedEstimateForecast);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase= testRailCaseService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailCase);
        assertEquals(2, updatedTestRailCase.getTRId());
        assertEquals(updatedEstimateForecast, updatedTestRailCase.getEstimateForecast());
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
    }

    @Test
    public void testUpdateSuiteId(){
        // Arrange
        beforeUpdate();
        int newSuiteId = 4;

        // Act
        testRailCase.setSuiteId(newSuiteId);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
        assertEquals(4, updatedTestRailCase.getSuiteId());
        assertEquals(newSuiteId, getTestRailCase.getSuiteId());
    }

    @Test
    public void testUpdateTemplateId(){
        // Arrange
        beforeUpdate();
        int newTemplateId = 4;

        // Act
        testRailCase.setTemplateId(newTemplateId);
        TestRailCase updatedTestRailCase = testRailCaseService.save(testRailCase);
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(2).get();

        // Assert
        assertNotNull(updatedTestRailCase);
        assertEquals(testRailCase, updatedTestRailCase);
        assertEquals(updatedTestRailCase, getTestRailCase);
        assertEquals(4, updatedTestRailCase.getTemplateId());
        assertEquals(newTemplateId, getTestRailCase.getTemplateId());
    }

    @Test
    public void testFindByTafId() {
        /* Arrange */
        testRailCaseService.save(testRailCase);
        testRailCaseService.save(testRailCase2);
        testRailCaseService.save(testRailCase3);
        Long tafId = testRailCase.getId();
        Long tafId2 = testRailCase2.getId();
        Long tafId3 = testRailCase3.getId();

        /* Act */
        TestRailCase getTestRailCase = testRailCaseService.findById(tafId).get();
        TestRailCase getTestRailCase2 = testRailCaseService.findById(tafId2).get();
        TestRailCase getTestRailCase3 = testRailCaseService.findById(tafId3).get();

        /* Assert */
        assertNotNull(getTestRailCase);
        assertNotNull(getTestRailCase2);
        assertNotNull(getTestRailCase3);

        assertEquals(testRailCase, getTestRailCase);
        assertEquals(testRailCase2, getTestRailCase2);
        assertEquals(testRailCase3, getTestRailCase3);
    }

    @Test
    public void testFindByTRId() {
        /* Arrange */
        int trId = (new Random()).nextInt(Integer.MAX_VALUE);
        int trId2 = (new Random()).nextInt(Integer.MAX_VALUE);
        int trId3 = (new Random()).nextInt(Integer.MAX_VALUE);


        testRailCase.setTRId(trId);
        testRailCase2.setTRId(trId2);
        testRailCase3.setTRId(trId3);

        testRailCaseService.save(testRailCase);
        testRailCaseService.save(testRailCase2);
        testRailCaseService.save(testRailCase3);

        /* Act */
        TestRailCase getTestRailCase = testRailCaseService.findByTRId(trId).get();
        TestRailCase getTestRailCase2 = testRailCaseService.findByTRId(trId2).get();
        TestRailCase getTestRailCase3 = testRailCaseService.findByTRId(trId3).get();

        /* Assert */
        assertNotNull(getTestRailCase);
        assertNotNull(getTestRailCase2);
        assertNotNull(getTestRailCase3);

        assertEquals(trId, getTestRailCase.getTRId());
        assertEquals(trId2, getTestRailCase2.getTRId());
        assertEquals(trId3, getTestRailCase3.getTRId());

        assertEquals(testRailCase, getTestRailCase);
        assertEquals(testRailCase2, getTestRailCase2);
        assertEquals(testRailCase3, getTestRailCase3);
    }

    @Test
    public void testFindByTitle() {
        /* Arrange */
        testRailCaseService.save(testRailCase);
        testRailCaseService.save(testRailCase2);
        testRailCaseService.save(testRailCase3);

        /* Act */
        TestRailCase getTestRailCase = testRailCaseService.findByTitle(testRailCase.getTitle()).get();
        TestRailCase getTestRailCase2 = testRailCaseService.findByTitle(testRailCase2.getTitle()).get();
        TestRailCase getTestRailCase3 = testRailCaseService.findByTitle(testRailCase3.getTitle()).get();

        /* Assert */
        assertNotNull(getTestRailCase);
        assertNotNull(getTestRailCase2);
        assertNotNull(getTestRailCase3);

        assertEquals(testRailCase, getTestRailCase);
        assertEquals(testRailCase2, getTestRailCase2);
        assertEquals(testRailCase3, getTestRailCase3);
    }

    @Test
    public void testDeleteByTafId() {
        /* Arrange */
        testRailCaseService.save(testRailCase);
        testRailCaseService.save(testRailCase2);
        testRailCaseService.save(testRailCase3);

        Long tafId = testRailCase.getId();
        Long tafId2 = testRailCase2.getId();
        Long tafId3 = testRailCase3.getId();

        /* Act */
        testRailCaseService.deleteById(tafId3);
        testRailCaseService.deleteById(tafId);
        testRailCaseService.deleteById(tafId2);

        /* Assert */
        assertTrue(testRailCaseService.findById(tafId).isEmpty());
        assertTrue(testRailCaseService.findById(tafId2).isEmpty());
        assertTrue(testRailCaseService.findById(tafId3).isEmpty());
    }
}
