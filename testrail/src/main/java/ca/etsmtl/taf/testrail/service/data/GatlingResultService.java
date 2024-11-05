package ca.etsmtl.taf.testrail.service.data;

import ca.etsmtl.taf.testrail.model.entity.GatlingResult;
import ca.etsmtl.taf.testrail.repository.GatlingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GatlingResultService {
    /*
    * This class is used to interact with the GatlingResult table in the database.
    * Assisted by IA (copilot & chatGPT & intellij)
    * Tests : TODO
    *          To be completed and tested
    * */

    @Autowired
    private GatlingResultRepository repository;

    // Save or update a GatlingResult entity
    public GatlingResult save(GatlingResult result) {
        return repository.save(result);
    }

    // Find a GatlingResult by its unique ID
    public Optional<GatlingResult> findById(Long id) {
        return repository.findById(id);
    }

    // Retrieve all GatlingResult entries
    public List<GatlingResult> findAll() {
        return repository.findAll();
    }

    // Delete a GatlingResult by its unique ID
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Retrieve GatlingResults filtered by statusId
    public List<GatlingResult> findByStatusId(Integer statusId) {
        return repository.findByStatusId(statusId);
    }
}
