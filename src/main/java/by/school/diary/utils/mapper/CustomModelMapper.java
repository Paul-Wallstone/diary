package by.school.diary.utils.mapper;

import by.school.diary.dto.*;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomModelMapper {


    private ModelMapper mapper;

    @Autowired
    public CustomModelMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserEntity toEntity(SignUpRequestDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, UserEntity.class)
                : null;
    }

    public UserEntity toEntity(UserDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, UserEntity.class)
                : null;
    }

    public LessonEntity toEntity(LessonDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, LessonEntity.class)
                : null;
    }

    public UserDto toDto(UserEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, UserDto.class)
                : null;
    }

    public LessonDto toDto(LessonEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, LessonDto.class)
                : null;
    }

    public ContactEntity toEntity(ContactDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, ContactEntity.class)
                : null;
    }

    public ContactDto toDto(ContactEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, ContactDto.class)
                : null;
    }

    public InfoEntity toEntity(InfoDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, InfoEntity.class)
                : null;
    }

    public InfoDto toDto(InfoEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, InfoDto.class)
                : null;
    }

    public StudentLessonEntity toEntity(StudentLessonDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, StudentLessonEntity.class)
                : null;
    }

    public StudentLessonDto toDto(StudentLessonEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, StudentLessonDto.class)
                : null;
    }

    public StudentEntity toEntity(StudentDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, StudentEntity.class)
                : null;
    }

    public StudentDto toDto(StudentEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, StudentDto.class)
                : null;
    }

    public EmployeeEntity toEntity(EmployeeDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, EmployeeEntity.class)
                : null;
    }

    public EmployeeDto toDto(EmployeeEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, EmployeeDto.class)
                : null;
    }

    public GroupEntity toEntity(GroupDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, GroupEntity.class)
                : null;
    }

    public GroupDto toDto(GroupEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, GroupDto.class)
                : null;
    }

    public PositionEntity toEntity(PositionDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, PositionEntity.class)
                : null;
    }

    public PositionDto toDto(PositionEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, PositionDto.class)
                : null;
    }

    public InstitutionEntity toEntity(InstitutionDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, InstitutionEntity.class)
                : null;
    }

    public InstitutionDto toDto(InstitutionEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, InstitutionDto.class)
                : null;
    }


    public SubjectEntity toEntity(SubjectDto dto) {
        return Optional.ofNullable(dto).isPresent()
                ? mapper.map(dto, SubjectEntity.class)
                : null;
    }

    public SubjectDto toDto(SubjectEntity entity) {
        return Optional.ofNullable(entity).isPresent()
                ? mapper.map(entity, SubjectDto.class)
                : null;
    }
}
