package by.school.diary.exception;

import by.school.diary.domain.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException validationException) {
        return ErrorResponse.builder()
                .message(validationException.getMessage())
                .status(NOT_FOUND)
                .timestamp(now())
                .build();
    }
}
