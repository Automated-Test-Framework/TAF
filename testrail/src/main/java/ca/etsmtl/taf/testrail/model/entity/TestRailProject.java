package ca.etsmtl.taf.testrail.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tr_project")
@Getter
@Setter
public class TestRailProject extends TestRailData {

    /*
    * Class to save projects from TestRail in TAF database to be used in the application.
    * Class build assisted by IA (copilot & chatGPT & intellij)
    * Tests :
    *       - Unit tests : TestUnitTestRailProject
    *       - Integration tests : TODO
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tr_id", unique = true, nullable = true)
    private Integer TRId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "announcement")
    private String announcement;

    @Column(name = "show_announcement")
    private Boolean showAnnouncement;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "completed_on")
    private Timestamp completedOn;

    @Column(name = "suite_mode")
    private Integer suiteMode;

    @Column(name = "default_role_id")
    private Integer defaultRoleId;

    @Column(name = "case_statuses_enabled")
    private Boolean caseStatusesEnabled;

    @Column(name = "url")
    private String url;

    @Column(name="is_saved")
    private Boolean isSaved=false;

    @ManyToMany(mappedBy = "projects")
    private List<TestRailUser> users;
}
