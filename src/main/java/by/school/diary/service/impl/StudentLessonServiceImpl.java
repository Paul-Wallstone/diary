package by.school.diary.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import by.school.diary.dto.EmployeeDto;
import by.school.diary.dto.StudentDto;
import by.school.diary.dto.StudentLessonDto;
import by.school.diary.entity.StudentEntity;
import by.school.diary.entity.StudentLessonEntity;
import by.school.diary.exception.IdIsNullException;
import by.school.diary.exception.StudentLessonNotFoundException;
import by.school.diary.repository.StudentLessonRepository;
import by.school.diary.service.StudentLessonService;
import by.school.diary.utils.CustomModelMapper;

@Service
public class StudentLessonServiceImpl implements StudentLessonService {
	@Autowired
	private StudentLessonRepository studentLessonRepository;
	@Autowired
	private CustomModelMapper modelMapper;

	@Override
	public StudentLessonDto getById(Long id) {
		return modelMapper.toDto(getEntityById(id));
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
		StudentLessonEntity updatedLesson = studentLessonRepository.save(getEntityById(id));
		return modelMapper.toDto(updatedLesson);
	}

	@Override
	public StudentLessonDto update(StudentLessonDto dto) {
		if (!ObjectUtils.isEmpty(dto.getId())) {
			StudentLessonEntity updatedLesson = studentLessonRepository.save(getEntityById(dto.getId()));
			return modelMapper.toDto(updatedLesson);
		} else {
			throw new IdIsNullException("Id is null! Please revise your request!");
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			studentLessonRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new StudentLessonNotFoundException(id);
		}
	}

	@Override
	public List<StudentDto> studentsBy(StudentLessonDto studentLessonDto) {
		Set<StudentEntity> students = getEntityById(studentLessonDto.getId()).getLesson().getGroup().getStudents();
		return students.stream().map(student -> modelMapper.toDto(student)).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentsById(Long id) {
		Set<StudentEntity> students = getEntityById(id).getLesson().getGroup().getStudents();
		return students.stream().map(student -> modelMapper.toDto(student)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto employeeBy(StudentLessonDto studentLessonDto) {
		return modelMapper.toDto(getEntityById(studentLessonDto.getId()).getLesson().getEmployee());
	}

	@Override
	public EmployeeDto employeeById(Long id) {
		return modelMapper.toDto(getEntityById(id).getLesson().getEmployee());
	}

	public StudentLessonEntity getEntityById(Long id) {
		return studentLessonRepository.findById(id).orElseThrow(() -> new StudentLessonNotFoundException(id));
	}
}
