package com.bol.mancala.base.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomException extends Exception {
    private final CustomErrorCode code;
    private final String message;
    private final String trace;
    private final List<CustomErrorResult> causes;


    public CustomException(CustomErrorCode code, String message) {
        this.code = code;
        this.message = message;
        this.trace = null;
        this.causes = null;
    }

    public CustomException(CustomErrorCode code, String message, String trace) {
        this.code = code;
        this.message = message;
        this.trace = trace;
        this.causes = null;
    }

    public CustomException(CustomErrorCode code, String message, String trace, List<CustomErrorResult> causes) {
        this.code = code;
        this.message = message;
        this.trace = trace;
        this.causes = causes;
    }
}
