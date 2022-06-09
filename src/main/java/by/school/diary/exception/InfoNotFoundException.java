package by.school.diary.exception;

public class InfoNotFoundException extends RuntimeException {
	public InfoNotFoundException(Long id) {
		super("Info with id: " + id + " not found! Please revise Info id!");
	}
}
