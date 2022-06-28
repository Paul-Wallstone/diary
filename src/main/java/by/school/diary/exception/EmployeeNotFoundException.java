package by.school.diary.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee with id: " + id + " not found! Please revise Employee id!");
    }
}
