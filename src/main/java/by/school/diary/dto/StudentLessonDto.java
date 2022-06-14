package by.school.diary.dto;

import by.school.diary.domain.Mark;
import by.school.diary.entity.LessonEntity;
import by.school.diary.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentLessonDto {
	protected Long id;
	
	private Mark mark;

    private String notes;

    private String task;

    private StudentEntity student;

    private LessonEntity lesson;
}
