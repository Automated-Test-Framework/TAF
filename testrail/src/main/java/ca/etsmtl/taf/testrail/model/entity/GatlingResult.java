package ca.etsmtl.taf.testrail.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tr_gatling_result")
@Getter
@Setter
public class GatlingResult {
    /*
    * Assisted by IA (copilot & chatGPT & intellij)
    * Tests : TODO
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_id", nullable = false)
    private Integer statusId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "version")
    private String version;

    @Column(name = "elapsed")
    private String elapsed;

    @Column(name = "defects")
    private String defects;

    @Column(name = "assignedto_id")
    private Integer assignedToId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

}