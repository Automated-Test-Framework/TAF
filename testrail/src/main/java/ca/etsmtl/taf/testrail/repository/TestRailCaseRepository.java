package ca.etsmtl.taf.testrail.repository;

import ca.etsmtl.taf.testrail.model.entity.TestRailCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRailCaseRepository extends JpaRepository<TestRailCase, Long> {
    /*
     * This interface is used to interact with the TestRailProject table in the database.
     * Tests : TestUnitTestRailCase
     * Assisted by IA (copilot & chatGPT & intellij)
     * */

    Optional<TestRailCase> findByTRId(Integer id);

    Optional<TestRailCase> findByTitle(String title);



}
