package ca.etsmtl.taf.testrail.repository;

import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRailProjectRepository extends JpaRepository<TestRailProject, Long> {

    /*
    * This interface is used to interact with the TestRailProject table in the database.
    * Tests : TestUnitTestRailProject
    * Assisted by IA (copilot & chatGPT & intellij)
    * */

    Optional<TestRailProject> findByTRId(Integer id);

    Optional<TestRailProject> findByName(String name);

}
