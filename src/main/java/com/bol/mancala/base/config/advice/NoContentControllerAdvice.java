package com.bol.mancala.base.config.advice;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Global advice for handling void controller
 * automatically returns 204 no content
 */
@ControllerAdvice
public class NoContentControllerAdvice implements ResponseBodyAdvice<Void> {
    @Override
    public boolean supports(MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().isAssignableFrom(void.class);
    }

    @Override
    public Void beforeBodyWrite(Void body, MethodParameter returnType, @NotNull MediaType mediaType,
                                @NotNull Class<? extends HttpMessageConverter<?>> converterType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        if (returnType.getParameterType().isAssignableFrom(void.class)) {
            response.setStatusCode(HttpStatus.NO_CONTENT);
        }

        return body;
    }
}
