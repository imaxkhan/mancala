package com.bol.mancala.base.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResult {
    private final CustomErrorCode code;
    private final String message;
    private final String field;
    private String trace;


    public CustomErrorResult(CustomErrorCode code, String message, String field, String trace) {
        this.code = code;
        this.message = message;
        this.field = field;
        this.trace = trace;
    }

    public CustomErrorResult(CustomException exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
        this.field = null;
    }

    public CustomErrorResult(CustomException exception, HttpServletResponse response) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
        this.field = null;
        response.setStatus(this.code.getValue());
    }
}
