package by.school.diary.service.impl;

import by.school.diary.dto.LessonDto;
import by.school.diary.entity.LessonEntity;
import by.school.diary.exception.IdIsNullException;
import by.school.diary.exception.LessonNotFoundException;
import by.school.diary.repository.LessonRepository;
import by.school.diary.service.LessonService;
import by.school.diary.utils.CustomModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CustomModelMapper modelMapper;


    @Override
    public LessonDto getById(Long id) {
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
        return modelMapper.toDto(lessonEntity);
    }

    @Override
    public List<LessonDto> getAll() {
        Stream<LessonEntity> lessonEntities = StreamSupport.stream(lessonRepository.findAll().spliterator(), false);
        return lessonEntities.map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList());
    }

    @Override
    public LessonDto save(LessonDto dto) {
        LessonEntity lessonEntity = modelMapper.toEntity(dto);
        LessonEntity savedLesson = lessonRepository.save(lessonEntity);
        return modelMapper.toDto(savedLesson);
    }

    @Override
    public LessonDto update(LessonDto dto, Long id) {
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
        LessonEntity updatedLesson = lessonRepository.save(lessonEntity);
        return modelMapper.toDto(updatedLesson);
    }

    @Override
    public LessonDto update(LessonDto dto) {
        if (!ObjectUtils.isEmpty(dto.getId())) {
            LessonEntity lessonEntity = lessonRepository.findById(dto.getId()).orElseThrow(() -> new LessonNotFoundException(dto.getId()));
            LessonEntity updatedLesson = lessonRepository.save(lessonEntity);
            return modelMapper.toDto(updatedLesson);
        } else {
            throw new IdIsNullException("Id is null! Please revise your request!");
        }
    }

	@Override
	public void deleteById(Long id) {
		try {
			lessonRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new LessonNotFoundException(id);
		}
	}

	@Override
	public Page<LessonDto> allByDates(LocalDate from, LocalDate to, Pageable pageable) {
		Page<LessonEntity> lessons = lessonRepository.findAllByDateBetween(from, to, pageable);
		return new PageImpl<>(
				lessons.getContent().stream().map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList()),
				pageable, lessons.getTotalElements());
	}

    @Override
    public Page<LessonDto> allByDatesAndEmployeeId(LocalDate from, LocalDate to, Long employeeId, Pageable pageable) {
    	Page<LessonEntity> lessons = lessonRepository.findAllByDateBetweenAndEmployeeId(from, to, employeeId, pageable);
		return new PageImpl<>(
				lessons.getContent().stream().map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList()),
				pageable, lessons.getTotalElements());
    }

    @Override
    public Page<LessonDto> allByDatesAndSubjectId(LocalDate from, LocalDate to, Long subjectId, Pageable pageable) {
    	Page<LessonEntity> lessons = lessonRepository.findAllByDateBetweenAndSubjectId(from, to, subjectId, pageable);
		return new PageImpl<>(
				lessons.getContent().stream().map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList()),
				pageable, lessons.getTotalElements());
    }

    @Override
    public Page<LessonDto> allByDatesAndGroupId(LocalDate from, LocalDate to, Long groupId, Pageable pageable) {
    	Page<LessonEntity> lessons = lessonRepository.findAllByDateBetweenAndGroupId(from, to, groupId, pageable);
		return new PageImpl<>(
				lessons.getContent().stream().map(lesson -> modelMapper.toDto(lesson)).collect(Collectors.toList()),
				pageable, lessons.getTotalElements());
    }
}
