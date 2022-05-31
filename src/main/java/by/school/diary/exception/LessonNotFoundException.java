package by.school.diary.exception;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long id) {
        super("Lesson with id: " + id + " not found! Please revise Lesson id!");
    }
}
