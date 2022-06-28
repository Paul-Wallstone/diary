package by.school.diary.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import by.school.diary.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import by.school.diary.utils.mapper.CustomModelMapper;

@Service
public class StudentLessonServiceImpl implements StudentLessonService {
    @Autowired
    private StudentLessonRepository studentLessonRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CustomModelMapper modelMapper;

    @Override
    public StudentLessonDto getById(Long id) {
        return modelMapper.toDto(getEntityById(id));
    }

    @Override
    public List<StudentLessonDto> all() {
        Stream<StudentLessonEntity> lessonEntities = StreamSupport
                .stream(studentLessonRepository.findAll().spliterator(), false);
        return lessonEntities.map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList());
    }

    @Override
    public StudentLessonDto save(StudentLessonDto dto) {
        StudentLessonEntity studentLessonEntity = modelMapper.toEntity(dto);
        StudentLessonEntity savedLesson = studentLessonRepository.save(studentLessonEntity);
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
    public Page<StudentDto> studentsBy(StudentLessonDto dto, Pageable pageable) {
        if (!ObjectUtils.isEmpty(dto.getId())) {
            return studentsById(dto.getId(), pageable);
        }
        Optional<Long> groupId = Optional.ofNullable(getEntityById(dto.getId()).getLesson().getGroup().getId());
        Page<StudentEntity> studentEntities = studentRepository
                .findAllByGroupId(groupId
                        .orElseThrow(() -> new IdIsNullException("Id is null! Please revise object Id!")), pageable);
        return new PageImpl<>(
                studentEntities.getContent().stream().map(student -> modelMapper.toDto(student))
                        .collect(Collectors.toList()),
                pageable, studentEntities.getTotalElements());
    }

    @Override
    public Page<StudentDto> studentsById(Long id, Pageable pageable) {
        Optional<Long> groupId = Optional.ofNullable(getEntityById(id).getLesson().getGroup().getId());
        Page<StudentEntity> studentEntities = studentRepository.findAllByGroupId(groupId
                .orElseThrow(() -> new IdIsNullException("Id is null! Please revise object Id!")), pageable);
        return new PageImpl<>(
                studentEntities.getContent().stream().map(student -> modelMapper.toDto(student)).collect(Collectors.toList()),
                pageable, studentEntities.getTotalElements());
    }

    @Override
    public EmployeeDto employeeBy(StudentLessonDto dto) {
        return modelMapper.toDto(getEntityById(dto.getId()).getLesson().getEmployee());
    }

    @Override
    public EmployeeDto employeeById(Long id) {
        return modelMapper.toDto(getEntityById(id).getLesson().getEmployee());
    }

    private StudentLessonEntity getEntityById(Long id) {
        return studentLessonRepository.findById(id).orElseThrow(() -> new StudentLessonNotFoundException(id));
    }
}
