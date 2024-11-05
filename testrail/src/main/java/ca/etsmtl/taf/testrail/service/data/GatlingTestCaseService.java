package ca.etsmtl.taf.testrail.service.data;

import ca.etsmtl.taf.testrail.model.entity.GatlingTestCase;
import ca.etsmtl.taf.testrail.repository.GatlingTestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GatlingTestCaseService {
    /*
     * This class is used to interact with the GatlingResult table in the database.
     * Assisted by IA (copilot & chatGPT & intellij)
     * Tests : TODO
     *          To be completed and tested
     * */


    @Autowired
    private GatlingTestCaseRepository repository;

    // Save or update a GatlingTestCase entity
    public GatlingTestCase save(GatlingTestCase testCase) {
        return repository.save(testCase);
    }

    // Find a GatlingTestCase by its unique ID
    public Optional<GatlingTestCase> findById(Long id) {
        return repository.findById(id);
    }

    // Retrieve all GatlingTestCase entries
    public List<GatlingTestCase> findAll() {
        return repository.findAll();
    }

    // Delete a GatlingTestCase by its unique ID
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Retrieve GatlingTestCase entries filtered by sectionId
    public List<GatlingTestCase> findBySectionId(Integer sectionId) {
        return repository.findBySectionId(sectionId);
    }

    // Retrieve GatlingTestCase entries filtered by title
    public List<GatlingTestCase> findByTitle(String title) {
        return repository.findByTitle(title);
    }
}
