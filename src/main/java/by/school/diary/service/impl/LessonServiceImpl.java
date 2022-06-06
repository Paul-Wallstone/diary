package by.school.diary.service.impl;

import by.school.diary.dto.request.LessonRequestDto;
import by.school.diary.dto.response.LessonResponseDto;
import by.school.diary.entity.ScheduleEntity;
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
        ScheduleEntity scheduleEntity = scheduleRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
        return modelMapper.toDto(scheduleEntity);
    }

    @Override
    public List<LessonResponseDto> getAll() {
        List<ScheduleEntity> lessonEntities = StreamSupport.stream(scheduleRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return lessonEntities.stream().map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList());
    }

    @Override
    public LessonResponseDto save(LessonRequestDto dto) {
        ScheduleEntity scheduleEntity = modelMapper.toEntity(dto);
        ScheduleEntity savedLesson = scheduleRepository.save(scheduleEntity);
        return modelMapper.toDto(savedLesson);
    }

    @Override
    public LessonResponseDto update(LessonRequestDto dto, Long id) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));

        ScheduleEntity updatedLesson = scheduleRepository.save(scheduleEntity);
        return modelMapper.toDto(updatedLesson);
    }

    @Override
    public LessonResponseDto update(LessonRequestDto dto) {
        if (!ObjectUtils.isEmpty(dto.getId())) {
            ScheduleEntity scheduleEntity = scheduleRepository.findById(dto.getId()).orElseThrow(() -> new LessonNotFoundException(dto.getId()));

            ScheduleEntity updatedLesson = scheduleRepository.save(scheduleEntity);
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
