package ca.etsmtl.taf.testrail.service.data;


import ca.etsmtl.taf.testrail.TestConfig;
import ca.etsmtl.taf.testrail.model.entity.TestRailSuite;
import ca.etsmtl.taf.testrail.model.factory.TestRailSuiteFactory;
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
public class TestUnitTestRailSuite {

    private TestRailSuite testRailSuite;
    private TestRailSuite testRailSuite2;
    private TestRailSuite testRailSuite3;
    private String defaultName;

    @Autowired
    private TestRailSuiteService testRailSuiteService;

    @BeforeEach
    public void setUp() {
        testRailSuiteService.deleteAll(); // Drop all data from the table
        defaultName = "defaultName_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);
        testRailSuite = TestRailSuiteFactory.create(defaultName);
        testRailSuite2 = TestRailSuiteFactory.create(defaultName + "_2");
        testRailSuite3 = TestRailSuiteFactory.create(defaultName + "_3");
    }

    @Test
    public void testSave() {
        // Arrange
        testRailSuite.setTRId(1);

        // Act
        TestRailSuite savedTestRailSuite = testRailSuiteService.save(testRailSuite);

        // Assert
        assertNotNull(savedTestRailSuite);
        assertEquals(testRailSuite, savedTestRailSuite);
        assertEquals(defaultName, savedTestRailSuite.getName());
    }

    public void beforeUpdate() {
        testRailSuite.setTRId(2);
        testRailSuiteService.save(testRailSuite);
    }

    @Test
    public void testUpdateId() {
        /* Arrange */
        beforeUpdate();
        testRailSuite.setTRId(3);

        /* Act */
        TestRailSuite savedTestRailSuite = testRailSuiteService.save(testRailSuite);

        /* Assert */
        assertNotNull(savedTestRailSuite);
        assertEquals(testRailSuite, savedTestRailSuite);
        assertEquals(defaultName, savedTestRailSuite.getName());
        assertEquals(3, savedTestRailSuite.getTRId());
    }

    @Test
    public void testUpdateName() {
        /* Arrange */
        beforeUpdate();
        String updatedName = "updatedName_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailSuite.setName(updatedName);
        TestRailSuite updatedTestRailSuite = testRailSuiteService.save(testRailSuite);
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailSuite);
        assertEquals(2, updatedTestRailSuite.getTRId());
        assertEquals(updatedName, updatedTestRailSuite.getName());
        assertEquals(testRailSuite, updatedTestRailSuite);
        assertEquals(updatedTestRailSuite, getTestRailSuite);
    }

    @Test
    public void testUpdateDescription() {
        /* Arrange */
        beforeUpdate();
        String updatedDescription = "updatedDescription_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailSuite.setDescription(updatedDescription);
        TestRailSuite updatedTestRailSuite = testRailSuiteService.save(testRailSuite);
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailSuite);
        assertEquals(2, updatedTestRailSuite.getTRId());
        assertEquals(updatedDescription, updatedTestRailSuite.getDescription());
        assertEquals(testRailSuite, updatedTestRailSuite);
        assertEquals(updatedTestRailSuite, getTestRailSuite);
    }

    @Test
    public void testUpdateIsMaster() {
        /* Arrange */
        beforeUpdate();
        boolean updatedIsMaster = true;

        /* Act */
        testRailSuite.setIsMaster(updatedIsMaster);
        TestRailSuite updatedTestRailSuite = testRailSuiteService.save(testRailSuite);
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailSuite);
        assertEquals(2, updatedTestRailSuite.getTRId());
        assertEquals(updatedIsMaster, updatedTestRailSuite.getIsMaster());
        assertEquals(testRailSuite, updatedTestRailSuite);
        assertEquals(updatedTestRailSuite, getTestRailSuite);
    }

    @Test
    public void testUpdateIsBaseline() {
        /* Arrange */
        beforeUpdate();
        boolean updatedIsBaseline = true;

        /* Act */
        testRailSuite.setIsBaseline(updatedIsBaseline);
        TestRailSuite updatedTestRailSuite = testRailSuiteService.save(testRailSuite);
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailSuite);
        assertEquals(2, updatedTestRailSuite.getTRId());
        assertEquals(updatedIsBaseline, updatedTestRailSuite.getIsBaseline());
        assertEquals(testRailSuite, updatedTestRailSuite);
        assertEquals(updatedTestRailSuite, getTestRailSuite);
    }

    @Test
    public void testUpdateIsCompleted() {
        /* Arrange */
        beforeUpdate();
        boolean updatedIsCompleted = true;

        /* Act */
        testRailSuite.setIsCompleted(updatedIsCompleted);
        TestRailSuite updatedTestRailSuite = testRailSuiteService.save(testRailSuite);
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailSuite);
        assertEquals(2, updatedTestRailSuite.getTRId());
        assertEquals(updatedIsCompleted, updatedTestRailSuite.getIsCompleted());
        assertEquals(testRailSuite, updatedTestRailSuite);
        assertEquals(updatedTestRailSuite, getTestRailSuite);
    }

    @Test
    public void testUpdateCompletedOn() {
        /* Arrange */
        beforeUpdate();
        Timestamp updatedCompletedOn = new Timestamp(System.currentTimeMillis());

        /* Act */
        testRailSuite.setCompletedOn(updatedCompletedOn);
        TestRailSuite updatedTestRailSuite = testRailSuiteService.save(testRailSuite);
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailSuite);
        assertEquals(2, updatedTestRailSuite.getTRId());
        assertEquals(updatedCompletedOn, updatedTestRailSuite.getCompletedOn());
        assertEquals(testRailSuite, updatedTestRailSuite);
        assertEquals(updatedTestRailSuite, getTestRailSuite);
    }

    @Test
    public void testUpdateUrl() {
        /* Arrange */
        beforeUpdate();
        String updatedUrl = "updatedUrl_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailSuite.setUrl(updatedUrl);
        TestRailSuite updatedTestRailSuite = testRailSuiteService.save(testRailSuite);
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailSuite);
        assertEquals(2, updatedTestRailSuite.getTRId());
        assertEquals(updatedUrl, updatedTestRailSuite.getUrl());
        assertEquals(testRailSuite, updatedTestRailSuite);
        assertEquals(updatedTestRailSuite, getTestRailSuite);
    }

    @Test
    public void testFindByTafId() {
        /* Arrange */
        testRailSuiteService.save(testRailSuite);
        testRailSuiteService.save(testRailSuite2);
        testRailSuiteService.save(testRailSuite3);
        Long tafId = testRailSuite.getId();
        Long tafId2 = testRailSuite2.getId();
        Long tafId3 = testRailSuite3.getId();

        /* Act */
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTafId(tafId).get();
        TestRailSuite getTestRailSuite2 = testRailSuiteService.findByTafId(tafId2).get();
        TestRailSuite getTestRailSuite3 = testRailSuiteService.findByTafId(tafId3).get();

        /* Assert */
        assertNotNull(getTestRailSuite);
        assertNotNull(getTestRailSuite2);
        assertNotNull(getTestRailSuite3);

        assertEquals(testRailSuite, getTestRailSuite);
        assertEquals(testRailSuite2, getTestRailSuite2);
        assertEquals(testRailSuite3, getTestRailSuite3);
    }

    @Test
    public void testFindByTRId() {
        /* Arrange */
        int trId = (new Random()).nextInt(Integer.MAX_VALUE);
        int trId2 = (new Random()).nextInt(Integer.MAX_VALUE);
        int trId3 = (new Random()).nextInt(Integer.MAX_VALUE);

        testRailSuite.setTRId(trId);
        testRailSuite2.setTRId(trId2);
        testRailSuite3.setTRId(trId3);

        testRailSuiteService.save(testRailSuite);
        testRailSuiteService.save(testRailSuite2);
        testRailSuiteService.save(testRailSuite3);

        /* Act */
        TestRailSuite getTestRailSuite = testRailSuiteService.findByTRId(trId).get();
        TestRailSuite getTestRailSuite2 = testRailSuiteService.findByTRId(trId2).get();
        TestRailSuite getTestRailSuite3 = testRailSuiteService.findByTRId(trId3).get();

        /* Assert */
        assertNotNull(getTestRailSuite);
        assertNotNull(getTestRailSuite2);
        assertNotNull(getTestRailSuite3);

        assertEquals(trId, getTestRailSuite.getTRId());
        assertEquals(trId2, getTestRailSuite2.getTRId());
        assertEquals(trId3, getTestRailSuite3.getTRId());

        assertEquals(testRailSuite, getTestRailSuite);
        assertEquals(testRailSuite2, getTestRailSuite2);
        assertEquals(testRailSuite3, getTestRailSuite3);
    }

    @Test
    public void testFindByName() {
        /* Arrange */
        testRailSuiteService.save(testRailSuite);
        testRailSuiteService.save(testRailSuite2);
        testRailSuiteService.save(testRailSuite3);

        /* Act */
        TestRailSuite getTestRailSuite = testRailSuiteService.findByName(testRailSuite.getName()).get();
        TestRailSuite getTestRailSuite2 = testRailSuiteService.findByName(testRailSuite2.getName()).get();
        TestRailSuite getTestRailSuite3 = testRailSuiteService.findByName(testRailSuite3.getName()).get();

        /* Assert */
        assertNotNull(getTestRailSuite);
        assertNotNull(getTestRailSuite2);
        assertNotNull(getTestRailSuite3);

        assertEquals(testRailSuite, getTestRailSuite);
        assertEquals(testRailSuite2, getTestRailSuite2);
        assertEquals(testRailSuite3, getTestRailSuite3);
    }

    @Test
    public void testDeleteByTafId() {
        /* Arrange */
        testRailSuiteService.save(testRailSuite);
        testRailSuiteService.save(testRailSuite2);
        testRailSuiteService.save(testRailSuite3);
        Long tafId = testRailSuite.getId();
        Long tafId2 = testRailSuite2.getId();
        Long tafId3 = testRailSuite3.getId();

        /* Act */
        testRailSuiteService.deleteByTafId(tafId);
        testRailSuiteService.deleteByTafId(tafId2);
        testRailSuiteService.deleteByTafId(tafId3);

        /* Assert */
        assertTrue(testRailSuiteService.findByTafId(tafId).isEmpty());
        assertTrue(testRailSuiteService.findByTafId(tafId2).isEmpty());
        assertTrue(testRailSuiteService.findByTafId(tafId3).isEmpty());
    }
}
