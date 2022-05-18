package by.school.diary.exception;

public class UserNotLoggedInException extends RuntimeException {
    public UserNotLoggedInException(String user) {
        super("User: " + user + " not logged in! Please revise your username or password!" );
    }
}
