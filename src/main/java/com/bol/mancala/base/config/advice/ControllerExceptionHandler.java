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

/**
 * Global Exception Handler that handle custom handled and unhandled exceptions
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     *
     * @param exception Custom exception being thrown will be caught here
     * @param response Manipulating servlet response status code here
     * @return Always return exception as array even with single object
     */
    @ExceptionHandler(CustomException.class)
    public List<CustomErrorResult> handleCustomException(CustomException exception, HttpServletResponse response) {
        response.setStatus(exception.getCode().getValue());
        if (exception.getCauses() != null) {
            return exception.getCauses();
        }
        return Collections.singletonList(new CustomErrorResult(exception));
    }

    /**
     *
     * @param exception MethodArgumentNotValidException exception being thrown will be caught here
     *                  specially for validation errors
     * @param response Manipulating servlet response status code here
     * @return Always return exception as array even with single object
     */
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

    /**
     *
     * @param exception MethodArgumentTypeMismatch exception being thrown will be caught here
     *                  specially for validation errors
     * @param response Manipulating servlet response status code here
     * @return Always return exception as array even with single object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public List<CustomErrorResult> handleMethodArgumentTypeMismatchExceptions(MethodArgumentTypeMismatchException exception, HttpServletResponse response) {
        List<CustomErrorResult> customErrorResultBucket = new ArrayList<>();
        response.setStatus(CustomErrorCode.VALIDATION_FAILED.getValue());
        customErrorResultBucket.add(new CustomErrorResult(CustomErrorCode.VALIDATION_FAILED, exception.getMessage(), exception.getName()));
        return customErrorResultBucket;
    }

    /**
     *
     * @param exception HttpMessageNotReadable exception being thrown will be caught here
     *                  specially for validation errors
     * @param response Manipulating servlet response status code here
     * @return Always return exception as array even with single object
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public List<CustomErrorResult> handleHttpRequestFormatException(HttpMessageNotReadableException exception, HttpServletResponse response) {
        List<CustomErrorResult> customErrorResultBucket = new ArrayList<>();
        response.setStatus(CustomErrorCode.FORMAT_EXCEPTION.getValue());
        customErrorResultBucket.add(new CustomErrorResult(CustomErrorCode.FORMAT_EXCEPTION, exception.getMessage(), null));
        return customErrorResultBucket;
    }

    /**
     *
     * @param exception Exception that are not handled ,will be handled here with custom exception
     * @param response Manipulating servlet response status code here
     * @return simple exception object
     */
    // TODO: 12/16/23 do we need change this to array?
    @ExceptionHandler(Exception.class)
    public CustomErrorResult handleUnHandledException(Exception exception, HttpServletResponse response) {
        exception.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new CustomErrorResult(CustomErrorCode.INTERNAL_SERVER_ERROR, "Internal Server Error", exception.getMessage());
    }
}
