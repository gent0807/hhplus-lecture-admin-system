package org.example.lecutreAdminSystem.interfaces.api.common.exception.handler;

import org.example.lecutreAdminSystem.domain.common.exception.ApplyInvalidException;
import org.example.lecutreAdminSystem.domain.common.exception.LectureInvalidException;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class APICustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = LectureInvalidException.class)
    public ResponseEntity<ErrorResponse> handleLectureInvalidException(LectureInvalidException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = ApplyInvalidException.class)
    public ResponseEntity<ErrorResponse> hadleApplyInvalidException(ApplyInvalidException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
