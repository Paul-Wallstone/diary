package by.school.diary.exception.handler;

import by.school.diary.dto.response.ErrorResponseDto;
import by.school.diary.exception.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvisor {
    public static final String OBJECT_NOT_FOUND = "Resource Not Found";
    public static final String USER_ALREADY_EXIST = "User Already Exist";
    public static final String NOT_CURRENT_USER = "Not Current User";
    public static final String USERNAME_NOT_FOUND = "Username Not Found";
    public static final String REQUEST_OBJECT_IS_NOT_VALID = "Request Object Is Not Valid";

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDto handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ErrorResponseDto.builder()
                .title(OBJECT_NOT_FOUND)
                .message(userNotFoundException.getMessage())
                .status(NOT_FOUND)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(userNotFoundException))
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(IdIsNullException.class)
    public ErrorResponseDto handleIdIsNullException(IdIsNullException idIsNullException) {
        return ErrorResponseDto.builder()
                .title(OBJECT_NOT_FOUND)
                .message(idIsNullException.getMessage())
                .status(NOT_FOUND)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(idIsNullException))
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(LessonNotFoundException.class)
    public ErrorResponseDto handleLessonNotFoundException(LessonNotFoundException lessonNotFoundException) {
        return ErrorResponseDto.builder()
                .title(OBJECT_NOT_FOUND)
                .message(lessonNotFoundException.getMessage())
                .status(NOT_FOUND)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(lessonNotFoundException))
                .build();
    }


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UserExistException.class)
    public ErrorResponseDto handleUserExistException(UserExistException userExistException) {
        return ErrorResponseDto.builder()
                .title(USER_ALREADY_EXIST)
                .message(userExistException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(userExistException))
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NotCurrentUserException.class)
    public ErrorResponseDto handleNotCurrentUserException(NotCurrentUserException notCurrentUserException) {
        return ErrorResponseDto.builder()
                .title(NOT_CURRENT_USER)
                .message(notCurrentUserException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(notCurrentUserException))
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponseDto handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        return ErrorResponseDto.builder()
                .title(USERNAME_NOT_FOUND)
                .message(usernameNotFoundException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(usernameNotFoundException))
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationCustomException.class)
    public ErrorResponseDto handleValidationCustomException(ValidationCustomException validationCustomException) {
        return ErrorResponseDto.builder()
                .title(REQUEST_OBJECT_IS_NOT_VALID)
                .message(validationCustomException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(validationCustomException))
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto handleValidationExceptions(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        return ErrorResponseDto.builder()
                .message(methodArgumentNotValidException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now().toString())
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseDto handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        return ErrorResponseDto.builder()
                .message(constraintViolationException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now().toString())
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ContactNotFoundException.class)
    public ErrorResponseDto handleContactNotFoundException(ContactNotFoundException contactNotFoundException) {
        return ErrorResponseDto.builder()
                .title(OBJECT_NOT_FOUND)
                .message(contactNotFoundException.getMessage())
                .status(NOT_FOUND)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(contactNotFoundException))
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(InfoNotFoundException.class)
    public ErrorResponseDto handInfoNotFoundException(InfoNotFoundException infoNotFoundException) {
        return ErrorResponseDto.builder()
                .title(OBJECT_NOT_FOUND)
                .message(infoNotFoundException.getMessage())
                .status(NOT_FOUND)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(infoNotFoundException))
                .build();
    }
}
