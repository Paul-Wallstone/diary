package by.school.diary.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import by.school.diary.dto.StudentLessonDto;
import by.school.diary.entity.StudentLessonEntity;
import by.school.diary.exception.IdIsNullException;
import by.school.diary.exception.StudentLessonNotFoundException;
import by.school.diary.repository.StudentLessonRepository;
import by.school.diary.service.StudentLessonService;
import by.school.diary.utils.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentLessonServiceImpl implements StudentLessonService {
	@Autowired
	private StudentLessonRepository studentLessonRepository;
	@Autowired
	private CustomModelMapper modelMapper;

	@Override
	public StudentLessonDto getById(Long id) {
		StudentLessonEntity StudentLessonEntity = studentLessonRepository.findById(id)
				.orElseThrow(() -> new StudentLessonNotFoundException(id));
		return modelMapper.toDto(StudentLessonEntity);
	}

	@Override
	public List<StudentLessonDto> getAll() {
		Stream<StudentLessonEntity> lessonEntities = StreamSupport
				.stream(studentLessonRepository.findAll().spliterator(), false);
		return lessonEntities.map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList());
	}

	@Override
	public StudentLessonDto save(StudentLessonDto dto) {
		StudentLessonEntity StudentLessonEntity = modelMapper.toEntity(dto);
		StudentLessonEntity savedLesson = studentLessonRepository.save(StudentLessonEntity);
		return modelMapper.toDto(savedLesson);
	}

	@Override
	public StudentLessonDto update(StudentLessonDto dto, Long id) {
		StudentLessonEntity StudentLessonEntity = studentLessonRepository.findById(id)
				.orElseThrow(() -> new StudentLessonNotFoundException(id));
		StudentLessonEntity updatedLesson = studentLessonRepository.save(StudentLessonEntity);
		return modelMapper.toDto(updatedLesson);
	}

	@Override
	public StudentLessonDto update(StudentLessonDto dto) {
		if (!ObjectUtils.isEmpty(dto.getId())) {
			StudentLessonEntity StudentLessonEntity = studentLessonRepository.findById(dto.getId())
					.orElseThrow(() -> new StudentLessonNotFoundException(dto.getId()));
			StudentLessonEntity updatedLesson = studentLessonRepository.save(StudentLessonEntity);
			return modelMapper.toDto(updatedLesson);
		} else {
			throw new IdIsNullException("Id is null! Please revise your request!");
		}
	}

	@Override
	public void deleteById(Long id) {
		if (studentLessonRepository.existsById(id)) {
			studentLessonRepository.deleteById(id);
		} else {
			throw new StudentLessonNotFoundException(id);
		}
	}

}
