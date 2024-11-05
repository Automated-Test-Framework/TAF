package ca.etsmtl.taf.testrail.service.data;

import ca.etsmtl.taf.testrail.model.entity.TestRailCase;
import ca.etsmtl.taf.testrail.repository.TestRailCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestRailCaseService {
    /*
     * This class is used to interact with the GatlingResult table in the database.
     * Assisted by IA (copilot & chatGPT & intellij)
     * Tests : TestUnitTestRailCase
     * */


    @Autowired
    private TestRailCaseRepository testRailCaseRepository;

    public TestRailCase save(TestRailCase testRailCase) {
        return testRailCaseRepository.save(testRailCase);
    }

    public Optional<TestRailCase> findById(Long tafId) {
        return testRailCaseRepository.findById(tafId);
    }

    public Optional<TestRailCase> findByTRId(Integer id) {
        return testRailCaseRepository.findByTRId(id);
    }

    public Optional<TestRailCase> findByTitle(String title) {
        return testRailCaseRepository.findByTitle(title);
    }

    public void deleteById(Long tafId) {
        testRailCaseRepository.deleteById(tafId);
    }

    public void deleteAll() {
        testRailCaseRepository.deleteAll();
    }
}
