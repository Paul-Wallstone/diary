package by.school.diary.exception;

import by.school.diary.dto.response.ErrorResponseDto;
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
                .title("User Already Exist")
                .message(userExistException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now().toString())
                .stacktrace(ExceptionUtils.getStackTrace(userExistException))
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponseDto handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        return ErrorResponseDto.builder()
                .title("Username Not Found")
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
                .title("Request Object Is Not Valid")
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
				.message(contactNotFoundException.getMessage())
				.status(NOT_FOUND)
				.timestamp(now().toString())
				.build();
	}

}
