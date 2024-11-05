package ca.etsmtl.taf.testrail.service.manager.saving;

import ca.etsmtl.taf.testrail.model.entity.TestRailProject;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DataResponseFromTR {
    private int statusCode;
    private TestRailProject data;

    public DataResponseFromTR(int statusCode, TestRailProject data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
