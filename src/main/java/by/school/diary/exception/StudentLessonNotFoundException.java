package by.school.diary.exception;

public class StudentLessonNotFoundException extends RuntimeException {
    public StudentLessonNotFoundException(Long id) {
        super("Student's lesson with id: " + id + " not found! Please revise Student's lesson id!");
    }

}
