package com.bol.mancala.base.config.advice;

import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomErrorResult;
import com.bol.mancala.base.exception.CustomException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public List<CustomErrorResult> handleCustomException(CustomException exception, HttpServletResponse response) {
        response.setStatus(exception.getCode().getValue());
        if (exception.getCauses() != null) {
            return exception.getCauses();
        }
        return Collections.singletonList(new CustomErrorResult(exception));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<CustomErrorResult> handleValidationExceptions(MethodArgumentNotValidException exception, HttpServletResponse response) {
        List<CustomErrorResult> customErrorResultBucket = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            CustomErrorResult customErrorResult = new CustomErrorResult(CustomErrorCode.VALIDATION_FAILED, errorMessage, fieldName);
            customErrorResultBucket.add(customErrorResult);
        });
        response.setStatus(CustomErrorCode.VALIDATION_FAILED.getValue());
        return customErrorResultBucket;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public List<CustomErrorResult> handleMethodArgumentTypeMismatchExceptions(MethodArgumentTypeMismatchException exception, HttpServletResponse response) {
        List<CustomErrorResult> customErrorResultBucket = new ArrayList<>();
        response.setStatus(CustomErrorCode.VALIDATION_FAILED.getValue());
        customErrorResultBucket.add(new CustomErrorResult(CustomErrorCode.VALIDATION_FAILED, exception.getMessage(), exception.getName()));
        return customErrorResultBucket;
    }


    @ExceptionHandler(AccessDeniedException.class)
    public List<CustomErrorResult> handleAccessDeniedException(AccessDeniedException exception, HttpServletResponse response) {
        List<CustomErrorResult> customErrorResultBucket = new ArrayList<>();
        response.setStatus(CustomErrorCode.UNAUTHORIZED.getValue());
        customErrorResultBucket.add(new CustomErrorResult(CustomErrorCode.UNAUTHORIZED, exception.getMessage(), null));
        return customErrorResultBucket;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public List<CustomErrorResult> handleHttpRequestFormatException(HttpMessageNotReadableException exception, HttpServletResponse response) {
        List<CustomErrorResult> customErrorResultBucket = new ArrayList<>();
        response.setStatus(CustomErrorCode.FORMAT_EXCEPTION.getValue());
        customErrorResultBucket.add(new CustomErrorResult(CustomErrorCode.FORMAT_EXCEPTION, exception.getMessage(), null));
        return customErrorResultBucket;
    }

    @ExceptionHandler(Exception.class)
    public CustomErrorResult handleUnHandledException(Exception exception, HttpServletResponse response) {
        exception.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new CustomErrorResult(CustomErrorCode.INTERNAL_SERVER_ERROR, "Internal Server Error", exception.getMessage());
    }
}
