package org.example.lecutreAdminSystem.interfaces.api.common.exception.handler;

import org.example.lecutreAdminSystem.interfaces.api.common.exception.CustomException;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class APICustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleException(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
