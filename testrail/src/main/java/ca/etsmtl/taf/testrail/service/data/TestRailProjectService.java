package ca.etsmtl.taf.testrail.service.data;


import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import ca.etsmtl.taf.testrail.repository.TestRailProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestRailProjectService {
    /*
     * This class is used to interact with the GatlingResult table in the database.
     * Assisted by IA (copilot & chatGPT & intellij)
     * Tests : TestUnitTestRailProject
     * */


    @Autowired
    private TestRailProjectRepository testRailProjectRepository;

    public TestRailProject save(TestRailProject project) {
         return testRailProjectRepository.save(project);
    }

    public Optional<TestRailProject> findByTafId(Long tafId) {
        return testRailProjectRepository.findById(tafId);
    }

    public Optional<TestRailProject> findByTRId(Integer id) {
        return testRailProjectRepository.findByTRId(id);
    }

    public Optional<TestRailProject> findByName(String name) {
        return testRailProjectRepository.findByName(name);
    }

    public void deleteByTafId(Long tafId) {
        testRailProjectRepository.deleteById(tafId);
    }

    public void deleteAll() {
        testRailProjectRepository.deleteAll();
    }
}
