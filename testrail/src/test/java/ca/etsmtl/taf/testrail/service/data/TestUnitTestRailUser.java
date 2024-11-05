package ca.etsmtl.taf.testrail.service.data;

import ca.etsmtl.taf.testrail.TestConfig;
import ca.etsmtl.taf.testrail.model.entity.TestRailUser;
import ca.etsmtl.taf.testrail.model.factory.TestRailUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = TestConfig.class)
@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
public class TestUnitTestRailUser {

    private TestRailUser testRailUser;
    private TestRailUser testRailUser2;
    private TestRailUser testRailUser3;
    private String defaultName;
    private String defaultPassword;
    private String defaultEmail;

    @Autowired
    private TestRailUserService testRailUserService;

    @BeforeEach
    public void setUp() {
        testRailUserService.deleteAll(); // Drop all data from the table
        defaultName = "defaultName_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);
        defaultPassword = "defaultPassword_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);
        defaultEmail = "defaultEmail_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);
        testRailUser = TestRailUserFactory.create(defaultName, defaultPassword, defaultEmail);
        testRailUser2 = TestRailUserFactory.create(defaultName + "_2", defaultPassword + "_2", defaultEmail + "_2");
        testRailUser3 = TestRailUserFactory.create(defaultName + "_3", defaultPassword + "_3", defaultEmail + "_3");
    }

    @Test
    public void testSave() {
        /* Arrange */
        testRailUser.setTRId(1);

        /* Act */
        TestRailUser savedTestRailUser = testRailUserService.save(testRailUser);

        /* Assert */
        assertNotNull(savedTestRailUser);
        assertEquals(testRailUser, savedTestRailUser);
        assertEquals(defaultName, savedTestRailUser.getUsername());
    }

    public void beforeUpdate() {
        testRailUser.setTRId(2);
        testRailUserService.save(testRailUser);
    }

    @Test
    public void testUpdateUsername() {
        /* Arrange */
        beforeUpdate();
        String updatedUsername = "newName_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailUser.setUsername(updatedUsername);
        TestRailUser updatedTestRailUser = testRailUserService.save(testRailUser);
        TestRailUser getTestRailUser = testRailUserService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailUser);
        assertEquals(2, updatedTestRailUser.getTRId());
        assertEquals(updatedUsername, updatedTestRailUser.getUsername());
        assertEquals(testRailUser, updatedTestRailUser);
        assertEquals(updatedTestRailUser, getTestRailUser);
    }

    @Test
    public void testUpdatePassword() {
        /* Arrange */
        beforeUpdate();
        String updatedPassword = "newPassword_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailUser.setPassword(updatedPassword);
        TestRailUser updatedTestRailUser = testRailUserService.save(testRailUser);
        TestRailUser getTestRailUser = testRailUserService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailUser);
        assertEquals(2, updatedTestRailUser.getTRId());
        assertEquals(updatedPassword, updatedTestRailUser.getPassword());
        assertEquals(testRailUser, updatedTestRailUser);
        assertEquals(updatedTestRailUser, getTestRailUser);
    }

    @Test
    public void testUpdateEmail() {
        /* Arrange */
        beforeUpdate();
        String updatedEmail = "newEmail_" + LocalDate.now() + LocalTime.now() + TestConfig.getRandomString(16);

        /* Act */
        testRailUser.setEmail(updatedEmail);
        TestRailUser updatedTestRailUser = testRailUserService.save(testRailUser);
        TestRailUser getTestRailUser = testRailUserService.findByTRId(2).get();

        /* Assert */
        assertNotNull(updatedTestRailUser);
        assertEquals(2, updatedTestRailUser.getTRId());
        assertEquals(updatedEmail, updatedTestRailUser.getEmail());
        assertEquals(testRailUser, updatedTestRailUser);
        assertEquals(updatedTestRailUser, getTestRailUser);
    }

    @Test
    public void testFindByTafId() {

        /* Arrange */
        testRailUserService.save(testRailUser);
        testRailUserService.save(testRailUser2);
        testRailUserService.save(testRailUser3);
        Long tafId = testRailUser.getId();
        Long tafId2 = testRailUser2.getId();
        Long tafId3 = testRailUser3.getId();

        /* Act */
        TestRailUser getTestRailUser = testRailUserService.findByTafId(tafId).get();
        TestRailUser getTestRailUser2 = testRailUserService.findByTafId(tafId2).get();
        TestRailUser getTestRailUser3 = testRailUserService.findByTafId(tafId3).get();

        /* Assert */
        assertNotNull(getTestRailUser);
        assertNotNull(getTestRailUser2);
        assertNotNull(getTestRailUser3);
        assertEquals(testRailUser, getTestRailUser);
        assertEquals(testRailUser2, getTestRailUser2);
        assertEquals(testRailUser3, getTestRailUser3);
    }

    @Test
    public void testFindByTRId() {

        /* Arrange */
        int trId = (new Random()).nextInt(Integer.MAX_VALUE);
        int trId2 = (new Random()).nextInt(Integer.MAX_VALUE);
        int trId3 = (new Random()).nextInt(Integer.MAX_VALUE);
        testRailUser.setTRId(trId);
        testRailUser2.setTRId(trId2);
        testRailUser3.setTRId(trId3);
        testRailUserService.save(testRailUser);
        testRailUserService.save(testRailUser2);
        testRailUserService.save(testRailUser3);

        /* Act */
        TestRailUser getTestRailUser = testRailUserService.findByTRId(trId).get();
        TestRailUser getTestRailUser2 = testRailUserService.findByTRId(trId2).get();
        TestRailUser getTestRailUser3 = testRailUserService.findByTRId(trId3).get();

        /* Assert */
        assertNotNull(getTestRailUser);
        assertNotNull(getTestRailUser2);
        assertNotNull(getTestRailUser3);

        assertEquals(trId, getTestRailUser.getTRId());
        assertEquals(trId2, getTestRailUser2.getTRId());
        assertEquals(trId3, getTestRailUser3.getTRId());

        assertEquals(testRailUser, getTestRailUser);
        assertEquals(testRailUser2, getTestRailUser2);
        assertEquals(testRailUser3, getTestRailUser3);
    }

    @Test
    public void testFindByUsername() {

        /* Arrange */
        testRailUserService.save(testRailUser);
        testRailUserService.save(testRailUser2);
        testRailUserService.save(testRailUser3);

        /* Act */
        TestRailUser getTestRailUser = testRailUserService.findByUsername(testRailUser.getUsername()).get();
        TestRailUser getTestRailUser2 = testRailUserService.findByUsername(testRailUser2.getUsername()).get();
        TestRailUser getTestRailUser3 = testRailUserService.findByUsername(testRailUser3.getUsername()).get();

        /* Assert */
        assertNotNull(getTestRailUser);
        assertNotNull(getTestRailUser2);
        assertNotNull(getTestRailUser3);

        assertEquals(testRailUser, getTestRailUser);
        assertEquals(testRailUser2, getTestRailUser2);
        assertEquals(testRailUser3, getTestRailUser3);
    }

    @Test
    public void testDeleteByTafId(){
        /* Arrange */
        testRailUserService.save(testRailUser);
        testRailUserService.save(testRailUser2);
        testRailUserService.save(testRailUser3);

        Long tafId = testRailUser.getId();
        Long tafId2 = testRailUser2.getId();
        Long tafId3 = testRailUser3.getId();

        /* Act */
        testRailUserService.deleteByTafId(tafId);
        testRailUserService.deleteByTafId(tafId2);
        testRailUserService.deleteByTafId(tafId3);

        /* Assert */
        assertTrue(testRailUserService.findByTafId(tafId).isEmpty());
        assertTrue(testRailUserService.findByTafId(tafId2).isEmpty());
        assertTrue(testRailUserService.findByTafId(tafId3).isEmpty());
    }
}
