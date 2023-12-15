package com.bol.mancala.base.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomException extends Exception {
    private final CustomErrorCode code;
    private final String message;
    private final List<CustomErrorResult> causes;


    public CustomException(CustomErrorCode code, String message) {
        this.code = code;
        this.message = message;
        this.causes = null;
    }

    public CustomException(CustomErrorCode code, String message, List<CustomErrorResult> causes) {
        this.code = code;
        this.message = message;
        this.causes = causes;
    }
}
