package by.school.diary.exception;

import by.school.diary.domain.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.validation.ConstraintViolationException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvisor {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ErrorResponse.builder()
                .title("Resource Not Found")
                .message(userNotFoundException.getMessage())
                .status(NOT_FOUND)
                .timestamp(now())
                .stacktrace(ExceptionUtils.getStackTrace(userNotFoundException))
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        return ErrorResponse.builder()
                .message(methodArgumentNotValidException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        return ErrorResponse.builder()
                .message(constraintViolationException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now())
                .build();
    }

}
