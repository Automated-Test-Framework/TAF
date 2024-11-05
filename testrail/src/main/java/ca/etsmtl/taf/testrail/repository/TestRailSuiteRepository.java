package ca.etsmtl.taf.testrail.repository;

import ca.etsmtl.taf.testrail.model.entity.TestRailSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRailSuiteRepository extends JpaRepository<TestRailSuite, Long> {
    /*
     * This interface is used to interact with the TestRailProject table in the database.
     * Tests : TestUnitTestRailProject
     * Assisted by IA (copilot & chatGPT & intellij)
     * */

    Optional<TestRailSuite> findByTRId(Integer id);

    Optional<TestRailSuite> findByName(String name);

}
