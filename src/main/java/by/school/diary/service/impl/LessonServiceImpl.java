package by.school.diary.service.impl;

import by.school.diary.dto.request.LessonRequestDto;
import by.school.diary.dto.response.LessonResponseDto;
import by.school.diary.entity.StudentLessonEntity;
import by.school.diary.exception.IdIsNullException;
import by.school.diary.exception.LessonNotFoundException;
import by.school.diary.repository.ScheduleRepository;
import by.school.diary.service.LessonService;
import by.school.diary.utils.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CustomModelMapper modelMapper;


    @Override
    public LessonResponseDto getById(Long id) {
        StudentLessonEntity studentLessonEntity = scheduleRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
        return modelMapper.toDto(studentLessonEntity);
    }

    @Override
    public List<LessonResponseDto> getAll() {
        Stream<StudentLessonEntity> lessonEntities = StreamSupport.stream(scheduleRepository.findAll().spliterator(), false);
        return lessonEntities.map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList());
    }

    @Override
    public LessonResponseDto save(LessonRequestDto dto) {
        StudentLessonEntity studentLessonEntity = modelMapper.toEntity(dto);
        StudentLessonEntity savedLesson = scheduleRepository.save(studentLessonEntity);
        return modelMapper.toDto(savedLesson);
    }

    @Override
    public LessonResponseDto update(LessonRequestDto dto, Long id) {
        StudentLessonEntity studentLessonEntity = scheduleRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));

        StudentLessonEntity updatedLesson = scheduleRepository.save(studentLessonEntity);
        return modelMapper.toDto(updatedLesson);
    }

    @Override
    public LessonResponseDto update(LessonRequestDto dto) {
        if (!ObjectUtils.isEmpty(dto.getId())) {
            StudentLessonEntity studentLessonEntity = scheduleRepository.findById(dto.getId()).orElseThrow(() -> new LessonNotFoundException(dto.getId()));

            StudentLessonEntity updatedLesson = scheduleRepository.save(studentLessonEntity);
            return modelMapper.toDto(updatedLesson);
        } else {
            throw new IdIsNullException("Id is null! Please revise your request!");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        } else {
            throw new LessonNotFoundException(id);
        }
    }

    @Override
    public Page<LessonResponseDto> findAllByDateBetween(Date from, Date to, Pageable pageable) {
        return null;
    }

    @Override
    public Page<LessonResponseDto> findAllByDateBetweenAndEmployeeId(Date from, Date to, Long employeeId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<LessonResponseDto> findAllByDateBetweenAndSubjectId(Date from, Date to, Long subjectId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<LessonResponseDto> findAllByDateBetweenAndGroupId(Date from, Date to, Long groupId, Pageable pageable) {
        return null;
    }
}
