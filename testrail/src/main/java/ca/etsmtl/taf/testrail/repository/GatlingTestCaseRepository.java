package ca.etsmtl.taf.testrail.repository;

import ca.etsmtl.taf.testrail.model.entity.GatlingTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatlingTestCaseRepository extends JpaRepository<GatlingTestCase, Long> {
    /*
    * This interface is used to interact with the GatlingTestCase table in the database.
    * TODO
    *       To be completed and tested
    * Assisted by IA (copilot & chatGPT & intellij)
    * */


    // Find GatlingTestCase entries by sectionId
    List<GatlingTestCase> findBySectionId(Integer sectionId);

    // Find GatlingTestCase entries by title
    List<GatlingTestCase> findByTitle(String title);
}
