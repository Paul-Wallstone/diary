package by.school.diary.service.impl;

import by.school.diary.dto.request.LessonRequestDto;
import by.school.diary.dto.response.LessonResponseDto;
import by.school.diary.entity.LessonEntity;
import by.school.diary.exception.IdIsNullException;
import by.school.diary.exception.LessonNotFoundException;
import by.school.diary.repository.LessonRepository;
import by.school.diary.service.CRUDService;
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
    private LessonRepository lessonRepository;
    @Autowired
    private CustomModelMapper modelMapper;


    @Override
    public LessonResponseDto getById(Long id) {
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
        return modelMapper.toDto(lessonEntity);
    }

    @Override
    public List<LessonResponseDto> getAll() {
        List<LessonEntity> lessonEntities = StreamSupport.stream(lessonRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return lessonEntities.stream().map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList());
    }

    @Override
    public LessonResponseDto save(LessonRequestDto dto) {
        LessonEntity lessonEntity = modelMapper.toEntity(dto);
        LessonEntity savedLesson = lessonRepository.save(lessonEntity);
        return modelMapper.toDto(savedLesson);
    }

    @Override
    public LessonResponseDto update(LessonRequestDto dto, Long id) {
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
        lessonEntity.setDate(dto.getDate());
        lessonEntity.setMark(dto.getMark());
        lessonEntity.setEmployee(dto.getEmployee());
        lessonEntity.setMessage(dto.getMessage());
        lessonEntity.setSubject(dto.getSubject());
//        lessonEntity.setDiaries(dto.getDiaries());
        lessonEntity.setGroup(dto.getGroup());
        LessonEntity updatedLesson = lessonRepository.save(lessonEntity);
        return modelMapper.toDto(updatedLesson);
    }

    @Override
    public LessonResponseDto update(LessonRequestDto dto) {
        if (!ObjectUtils.isEmpty(dto.getId())) {
            LessonEntity lessonEntity = lessonRepository.findById(dto.getId()).orElseThrow(() -> new LessonNotFoundException(dto.getId()));
            lessonEntity.setDate(dto.getDate());
            lessonEntity.setMark(dto.getMark());
            lessonEntity.setEmployee(dto.getEmployee());
            lessonEntity.setMessage(dto.getMessage());
            lessonEntity.setSubject(dto.getSubject());
//            lessonEntity.setDiaries(dto.getDiaries());
            lessonEntity.setGroup(dto.getGroup());
            LessonEntity updatedLesson = lessonRepository.save(lessonEntity);
            return modelMapper.toDto(updatedLesson);
        } else {
            throw new IdIsNullException("Id is null! Please revise your request!");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
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
