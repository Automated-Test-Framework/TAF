package ca.etsmtl.taf.testrail.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tr_gatling_test_case")
@Getter
@Setter
public class GatlingTestCase {

    /*
    * Assisted by IA (copilot & chatGPT & intellij)
    * Tests : TODO
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scenario_name", nullable = false)
    private String scenarioName;

    @Column(name = "base_url", nullable = false)
    private String baseUrl;

    @Column(name = "user_injection")
    private String userInjection;
}