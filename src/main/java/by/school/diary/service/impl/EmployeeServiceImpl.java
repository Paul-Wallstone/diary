package by.school.diary.service.impl;

import by.school.diary.dto.*;
import by.school.diary.entity.EmployeeEntity;
import by.school.diary.entity.LessonEntity;
import by.school.diary.entity.StudentLessonEntity;
import by.school.diary.entity.SubjectEntity;
import by.school.diary.exception.EmployeeNotFoundException;
import by.school.diary.exception.GroupNotFoundException;
import by.school.diary.exception.IdIsNullException;
import by.school.diary.exception.SubjectNotFoundException;
import by.school.diary.repository.*;
import by.school.diary.service.EmployeeService;
import by.school.diary.utils.mapper.CustomModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final GroupRepository groupRepository;

    private final CustomModelMapper modelMapper;

    private final LessonRepository lessonRepository;

    private final SubjectRepository subjectRepository;

    @Override
    public void delete(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.toEntity(employeeDto);
        if (Optional.ofNullable(employeeEntity).isEmpty())
            throw new IdIsNullException("Employee is null, please revise request!");
        try {
            employeeRepository.delete(employeeEntity);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(employeeEntity.getId());
        }
    }

    @Override
    public GroupDto findBy(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.toEntity(employeeDto);
        return modelMapper.toDto(groupRepository.findByEmployee(employeeEntity).orElseThrow(() -> new GroupNotFoundException(GROUP_NOT_FOUND_PLEASE_REVISE_EMPLOYEE_PARAMETER)));
    }

    @Override
    public GroupDto findById(Long employeeId) {
        return modelMapper.toDto(groupRepository.findByEmployeeId(employeeId).orElseThrow(() -> new GroupNotFoundException(GROUP_NOT_FOUND_PLEASE_REVISE_EMPLOYEE_PARAMETER)));
    }

    @Override
    public List<SubjectDto> subjectsBy(EmployeeDto employeeDto) {
        if (!ObjectUtils.isEmpty(employeeDto.getId()))
            return subjectsById(employeeDto.getId());
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);
    }

    @Override
    public List<SubjectDto> subjectsById(Long employeeId) {
        List<SubjectEntity> subjectEntities = subjectRepository.findByEmployeeId(employeeId).orElseThrow(() -> new SubjectNotFoundException(NOT_FOUND_BY_EMPLOYEE_ID));
        return subjectEntities.stream().map(modelMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<LessonDto> lessonsBy(EmployeeDto employeeDto, Pageable pageable) {
        if (!ObjectUtils.isEmpty(employeeDto.getId()))
            return lessonsById(employeeDto.getId(), pageable);
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);
    }

    @Override
    public List<LessonDto> lessonsBy(EmployeeDto employeeDto) {
        if (!ObjectUtils.isEmpty(employeeDto.getId())) {
            List<LessonEntity> entities = lessonRepository.findAllByEmployeeId(employeeDto.getId());
            return entities.stream().map(modelMapper::toDto).collect(Collectors.toList());
        }
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);
    }

    @Override
    public Page<LessonDto> lessonsById(Long employeeId, Pageable pageable) {
        Page<LessonEntity> lessons = lessonRepository.findAllByEmployeeId(employeeId, pageable);
        return new PageImpl<>(
                lessons.getContent().stream().map(modelMapper::toDto).collect(Collectors.toList()),
                pageable, lessons.getTotalElements());
    }

    @Override
    public Page<LessonDto> lessonsBy(EmployeeDto employeeDto, LocalDate from, LocalDate to, Pageable pageable) {
        if (!ObjectUtils.isEmpty(employeeDto.getId()))
            return lessonsBy(employeeDto.getId(), from, to, pageable);
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);

    }

    @Override
    public Page<LessonDto> lessonsBy(Long employeeId, LocalDate from, LocalDate to, Pageable pageable) {
        Page<LessonEntity> lessons = lessonRepository.findAllByDateBetweenAndEmployeeId(from, to, employeeId, pageable);
        return new PageImpl<>(
                lessons.getContent().stream().map(modelMapper::toDto).collect(Collectors.toList()),
                pageable, lessons.getTotalElements());
    }

    @Override
    public Page<LessonDto> lessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable) {
        Page<LessonEntity> lessons = lessonRepository.findAllByDateBetweenAndEmployeeIdAndGroupId(from, to, employeeDto.getId(), groupDto.getId(), pageable);
        return new PageImpl<>(
                lessons.getContent().stream().map(modelMapper::toDto).collect(Collectors.toList()),
                pageable, lessons.getTotalElements());
    }

    @Override
    public Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, Pageable pageable) {
        if (!ObjectUtils.isEmpty(employeeDto.getId())) {
            List<LessonEntity> lessons = lessonRepository.findAllByEmployeeId(employeeDto.getId());
            return getStudentLessonDtos(pageable, lessons);
        }
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);
    }

    @Override
    public Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, LocalDate from, LocalDate to, Pageable pageable) {
        if (!ObjectUtils.isEmpty(employeeDto.getId())) {
            List<LessonEntity> lessons = lessonRepository.findAllByDateBetweenAndEmployeeId(from, to, employeeDto.getId());
            return getStudentLessonDtos(pageable, lessons);
        }
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);
    }

    @Override
    public Page<StudentLessonDto> studentLessonsBy(EmployeeDto employeeDto, GroupDto groupDto, LocalDate from, LocalDate to, Pageable pageable) {
        if (!ObjectUtils.isEmpty(employeeDto.getId())) {
            if (!ObjectUtils.isEmpty(employeeDto.getId())) {
                List<LessonEntity> lessons = lessonRepository.findAllByDateBetweenAndEmployeeIdAndGroupId(from, to, employeeDto.getId(), groupDto.getId());
                return getStudentLessonDtos(pageable, lessons);
            }
            throw new IdIsNullException(ID_IS_NULL_REVISE_GROUP_PARAMETER);
        }
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);
    }

    @Override
    public EmployeeDto getById(Long id) {
        return modelMapper.toDto(getEntityById(id));
    }

    @Override
    public List<EmployeeDto> all() {
        Stream<EmployeeEntity> entities = StreamSupport.stream(employeeRepository.findAll().spliterator(), false);
        return entities.map(modelMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        EmployeeEntity employeeEntity = modelMapper.toEntity(dto);
        employeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.toDto(employeeEntity);
    }

    @Override
    public EmployeeDto update(EmployeeDto dto, Long id) {
        if (!ObjectUtils.isEmpty(id)) {
            EmployeeEntity employeeEntity = getEntityById(id);
            employeeEntity.setInfo(modelMapper.toEntity(dto.getInfo()));
            employeeEntity.setPosition(modelMapper.toEntity(dto.getPosition()));
            employeeEntity.setContact(modelMapper.toEntity(dto.getContact()));
            employeeEntity.setInstitution(modelMapper.toEntity(dto.getInstitution()));
            employeeEntity.setUsername(dto.getUsername());
            employeeEntity = employeeRepository.save(employeeEntity);
            return modelMapper.toDto(employeeEntity);
        }
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);
    }

    @Override
    public EmployeeDto update(EmployeeDto dto) {
        if (!ObjectUtils.isEmpty(dto.getId()))
            return update(dto, dto.getId());
        throw new IdIsNullException(ID_IS_NULL_REVISE_EMPLOYEE_PARAMETER);
    }

    @Override
    public void deleteById(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(id);
        }
    }

    private EmployeeEntity getEntityById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private Page<StudentLessonDto> getStudentLessonDtos(Pageable pageable, List<LessonEntity> lessons) {
        List<StudentLessonEntity> studentLessons = lessons.stream().flatMap(lesson -> lesson.getStudentLessons().stream()).collect(Collectors.toList());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), studentLessons.size());
        return new PageImpl<>(
                studentLessons.stream().map(modelMapper::toDto).collect(Collectors.toList()).subList(start, end),
                pageable, lessons.size());
    }
}
