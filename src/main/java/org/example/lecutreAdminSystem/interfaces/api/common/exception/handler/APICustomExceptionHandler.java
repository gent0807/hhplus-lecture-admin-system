package org.example.lecutreAdminSystem.interfaces.api.common.exception.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.example.lecutreAdminSystem.domain.common.exception.*;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorCode;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorResponse;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ResponseErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Iterator;
import java.util.List;

import static org.springframework.beans.PropertyAccessorUtils.getPropertyName;


@RestControllerAdvice
@Slf4j
public class APICustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    protected ResponseEntity<ResponseErrorDTO> handleException(ConstraintViolationException e) {
        String errorMessage = getResultMessage(e.getConstraintViolations().iterator());

        ResponseErrorDTO result = ResponseErrorDTO.builder()
                .code("ConstraintViolationException.class")
                .message(errorMessage)
                .build();

        log.error(errorMessage);
        return ResponseEntity.badRequest().body(result);
    }

    private String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (violationIterator.hasNext()) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            resultMessageBuilder
                    .append("['")
                    .append(getPropertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
                    .append("' is '")
                    .append(constraintViolation.getInvalidValue()) // 유효하지 않은 값
                    .append("' :: ")
                    .append(constraintViolation.getMessage()) // 유효성 검사 실패 시 메시지
                    .append("]");

            if (violationIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }

        return resultMessageBuilder.toString();
    }

    private String getPropertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    protected ResponseEntity<ResponseErrorDTO> handleException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = getMessage(allErrors.iterator());

        ResponseErrorDTO result = ResponseErrorDTO.builder()
                .code("MethodArgumentNotValidException.class")
                .message(message)
                .build();

        return ResponseEntity.badRequest().body(result);
    }

    private String getMessage(Iterator<ObjectError> errorIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (errorIterator.hasNext()) {
            ObjectError error = errorIterator.next();
            resultMessageBuilder
                    .append("['")
                    .append(((FieldError) error).getField()) // 유효성 검사가 실패한 속성
                    .append("' is '")
                    .append(((FieldError) error).getRejectedValue()) // 유효하지 않은 값
                    .append("' :: ")
                    .append(error.getDefaultMessage()) // 유효성 검사 실패 시 메시지
                    .append("]");

            if (errorIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }

        log.error(resultMessageBuilder.toString());
        return resultMessageBuilder.toString();
    }

    @ExceptionHandler(value = LectureInvalidException.class)
    public ResponseEntity<ErrorResponse> handleLectureInvalidException(LectureInvalidException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = LectureByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLectureByIdNotFoundException(LectureByIdNotFoundException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = LectureByDateAndTimeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLectureByDateAndTimeNotFoundException(LectureByDateAndTimeNotFoundException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = ApplyInvalidException.class)
    public ResponseEntity<ErrorResponse> handleApplyInvalidException(ApplyInvalidException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = ApplyByUserIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleApplyByUserIdNotFoundException(ApplyByUserIdNotFoundException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = ApplyByUserIdAndLectureIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleApplyByUserIdAndLectureIdNotFoundException(ApplyByUserIdAndLectureIdNotFoundException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }





}
