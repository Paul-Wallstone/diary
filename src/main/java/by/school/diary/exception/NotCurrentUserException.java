package by.school.diary.exception;

public class NotCurrentUserException extends RuntimeException {
    public NotCurrentUserException(String message) {
        super(message);
    }
}
