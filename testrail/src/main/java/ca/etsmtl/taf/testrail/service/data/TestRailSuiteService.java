package ca.etsmtl.taf.testrail.service.data;

import ca.etsmtl.taf.testrail.model.entity.TestRailSuite;
import ca.etsmtl.taf.testrail.repository.TestRailSuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestRailSuiteService {
    /*
     * This class is used to interact with the GatlingResult table in the database.
     * Assisted by IA (copilot & chatGPT & intellij)
     * Tests : TestUnitTestRailProject
     * */

    @Autowired
    private TestRailSuiteRepository testRAilSuiteRepository;

    public TestRailSuite save(TestRailSuite suite) {
        return testRAilSuiteRepository.save(suite);
    }

    public Optional<TestRailSuite> findByTafId(Long tafId) {
        return testRAilSuiteRepository.findById(tafId);
    }

    public Optional<TestRailSuite> findByTRId(Integer id) {
        return testRAilSuiteRepository.findByTRId(id);
    }

    public Optional<TestRailSuite> findByName(String name) {
        return testRAilSuiteRepository.findByName(name);
    }

    public void deleteByTafId(Long tafId) {
        testRAilSuiteRepository.deleteById(tafId);
    }

    public void deleteAll() {
        testRAilSuiteRepository.deleteAll();
    }


}
