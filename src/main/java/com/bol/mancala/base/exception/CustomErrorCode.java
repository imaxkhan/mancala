package com.bol.mancala.base.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public enum CustomErrorCode {

    ENTITY_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED),
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    VALIDATION_FAILED(HttpServletResponse.SC_BAD_REQUEST),
    ALREADY_EXITS(HttpServletResponse.SC_EXPECTATION_FAILED),
    FORMAT_EXCEPTION(HttpServletResponse.SC_BAD_REQUEST);

    private final int value;

    CustomErrorCode(int value) {
        this.value = value;
    }
}
