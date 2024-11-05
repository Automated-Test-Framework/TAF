package ca.etsmtl.taf.testrail.service.data;

import ca.etsmtl.taf.testrail.model.entity.TestRailUser;
import ca.etsmtl.taf.testrail.repository.TestRailUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestRailUserService {
    /*
     * This class is used to interact with the GatlingResult table in the database.
     * Assisted by IA (copilot & chatGPT & intellij)
     * Tests : TestUnitTestRailUser
     * */


    @Autowired
    private TestRailUserRepository repository;

    // Save or update a TestRailUser entity
    public TestRailUser save(TestRailUser user) {
        return repository.save(user);
    }

    // Find a TestRailUser by their unique ID (tafId)
    public Optional<TestRailUser> findByTafId(Long tafId) {
        return repository.findById(tafId);
    }

    // Find a TestRailUser by their unique ID (TR id)
    public Optional<TestRailUser> findByTRId(Integer id) {
        return repository.findByTRId(id);
    }

    // Find a TestRailUser by their unique username
    public Optional<TestRailUser> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    // Find a TestRailUser by their unique email
    public Optional<TestRailUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Retrieve all TestRailUser entries
    public List<TestRailUser> findAll() {
        return repository.findAll();
    }

    // Delete a TestRailUser by their unique ID (tafId)
    public void deleteById(Long tafId) {
        repository.deleteById(tafId);
    }

    public void deleteByTafId(Long tafId) {
        repository.deleteById(tafId);
    }

    // Delete all TestRailUser entries
    public void deleteAll() {
        repository.deleteAll();
    }


}
