package ca.etsmtl.taf.testrail.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "tr_suite")
@Getter
@Setter
public class TestRailSuite extends TestRailData{

        /*
        * Class to save suites from TestRail in TAF database to be used in the application.
        * Class build assisted by IA (copilot & chatGPT & intellij)
        * Tests :
        *       - Unit tests : TestUnitTestRailSuite
        *       - Integration tests : TODO
        * */

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "tr_id", unique = true)
        private Integer TRId;

        @ManyToOne
        @JoinColumn(name = "project_id", referencedColumnName = "id")
        private TestRailProject project;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "description")
        private String description;

        @Column(name = "is_master")
        private Boolean isMaster;

        @Column(name = "is_baseline")
        private Boolean isBaseline;

        @Column(name = "is_completed")
        private Boolean isCompleted;

        @Column(name = "completed_on")
        private Timestamp completedOn;

        @Column(name = "url")
        private String url;

}
