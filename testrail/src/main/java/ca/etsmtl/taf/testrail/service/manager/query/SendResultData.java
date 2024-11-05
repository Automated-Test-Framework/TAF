package ca.etsmtl.taf.testrail.service.manager.query;

import lombok.Getter;
import lombok.Setter;

import java.net.http.HttpResponse;


@Getter
@Setter
public class SendResultData {
    private Exception exception;
    private int statusCode;
    private HttpResponse<String> response;

    public SendResultData(Exception exception, int statusCode,  HttpResponse<String> response) {
        this.exception = exception;
        this.statusCode = statusCode;
        this.response = response;
    }
}
