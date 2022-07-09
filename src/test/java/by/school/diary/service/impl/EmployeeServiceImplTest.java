package by.school.diary.service.impl;

import by.school.diary.domain.Sex;
import by.school.diary.dto.*;
import by.school.diary.entity.*;
import by.school.diary.exception.EmployeeNotFoundException;
import by.school.diary.exception.GroupNotFoundException;
import by.school.diary.exception.IdIsNullException;
import by.school.diary.exception.SubjectNotFoundException;
import by.school.diary.repository.EmployeeRepository;
import by.school.diary.repository.GroupRepository;
import by.school.diary.repository.LessonRepository;
import by.school.diary.repository.SubjectRepository;
import by.school.diary.utils.mapper.CustomModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private GroupRepository groupRepository;
    @Spy
    private CustomModelMapper modelMapper = new CustomModelMapper(new ModelMapper());
    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private final List<EmployeeDto> employeeDtos = new ArrayList<>();
    private final List<EmployeeEntity> employeeEntities = new ArrayList<>();
    private final List<SubjectEntity> subjectEntities = new ArrayList<>();
    private final List<SubjectDto> subjectDtos = new ArrayList<>();
    public static final GroupEntity GROUP_ENTITY = GroupEntity.builder().title("A1").build();
    public static final GroupDto GROUP_DTO = GroupDto.builder().title("A1").build();
    public static final SubjectEntity SUBJECT_ENTITY = SubjectEntity.builder().title("Math").build();
    public static final SubjectEntity SUBJECT_ENTITY2 = SubjectEntity.builder().title("English").build();
    public static final SubjectDto SUBJECT_DTO = SubjectDto.builder().title("Math").build();
    public static final SubjectDto SUBJECT_DTO2 = SubjectDto.builder().title("English").build();
    public static EmployeeEntity EMPLOYEE_ENTITY;
    public static EmployeeDto EMPLOYEE_DTO;

    @BeforeEach
    void setUp() {
        employeeDtos.clear();
        employeeEntities.clear();
        ContactDto contact = ContactDto.builder()
                .address("some address")
                .city("Minsk")
                .phone("+37529-311-31-35")
                .postcode("2021")
                .build();

        InfoDto info = InfoDto.builder()
                .firstName("name1")
                .lastName("lastName1")
                .email("name1@gmail.com")
                .birthday(LocalDate.of(2000, 12, 12))
                .sex(Sex.MALE)
                .bio("some bio")
                .build();

        PositionDto position = PositionDto.builder()
                .title("Director")
                .build();

        InstitutionDto institution = InstitutionDto.builder()
                .title("School")
                .build();

        EMPLOYEE_DTO = EmployeeDto
                .builder()
                .id(1L)
                .contact(contact)
                .info(info)
                .position(position)
                .username("user1")
                .institution(institution)
                .build();

        employeeDtos.add(EMPLOYEE_DTO);

        ContactEntity contactEntity = ContactEntity.builder()
                .address("some address")
                .city("Minsk")
                .phone("+37529-311-31-35")
                .postcode("2021")
                .build();

        InfoEntity infoEntity = InfoEntity.builder()
                .firstName("name1")
                .lastName("lastName1")
                .email("name1@gmail.com")
                .birthday(LocalDate.of(2000, 12, 12))
                .sex(Sex.MALE)
                .bio("some bio")
                .build();

        PositionEntity positionEntity = PositionEntity.builder()
                .title("Director")
                .build();

        InstitutionEntity institutionEntity = InstitutionEntity.builder()
                .title("School")
                .build();

        EMPLOYEE_ENTITY = EmployeeEntity
                .EBuilder()
                .contact(contactEntity)
                .info(infoEntity)
                .position(positionEntity)
                .username("user1")
                .institution(institutionEntity)
                .locked(true)
                .verified(true)
                .credentialsExpired(true)
                .accountExpired(true)
                .enabled(true)
                .roles(new HashSet<>())
                .build();
        EMPLOYEE_ENTITY.setId(1L);
        employeeEntities.add(EMPLOYEE_ENTITY);
        subjectEntities.add(SUBJECT_ENTITY);
        subjectEntities.add(SUBJECT_ENTITY2);
        subjectDtos.add(SUBJECT_DTO);
        subjectDtos.add(SUBJECT_DTO2);
    }

    @Test
    void testDeleteSuccess() {
        when(modelMapper.toEntity(any(EmployeeDto.class))).thenReturn(EMPLOYEE_ENTITY);
        doNothing().when(employeeRepository).delete(any(EmployeeEntity.class));
        employeeService.delete(EMPLOYEE_DTO);
        verify(employeeRepository, times(1)).delete(EMPLOYEE_ENTITY);
        verify(employeeRepository, never()).delete(isNull());
    }

    @Test
    void testDeleteWithMapperSuccess() {
        doNothing().when(employeeRepository).delete(any(EmployeeEntity.class));
        employeeService.delete(EMPLOYEE_DTO);
        verify(employeeRepository, times(1)).delete(EMPLOYEE_ENTITY);
        verify(employeeRepository, never()).delete(isNull());
    }

    @Test
    void testDeleteNullFailed() {
        when(modelMapper.toEntity(any(EmployeeDto.class))).thenReturn(EMPLOYEE_ENTITY);
        doThrow(EmployeeNotFoundException.class).when(employeeRepository).delete(null);
        assertThrows(IdIsNullException.class, () -> employeeService.delete(null));
        verify(employeeRepository, never()).delete(any());
    }

    @Test
    void testDeleteNullWithMapperFailed() {
        doThrow(EmployeeNotFoundException.class).when(employeeRepository).delete(null);
        assertThrows(IdIsNullException.class, () -> employeeService.delete(null));
        verify(employeeRepository, never()).delete(any());
    }

    @Test
    void testDeleteFailed() {
        when(modelMapper.toEntity(any(EmployeeDto.class))).thenReturn(EMPLOYEE_ENTITY);
        doThrow(EmployeeNotFoundException.class).when(employeeRepository).delete(EMPLOYEE_ENTITY);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.delete(EMPLOYEE_DTO));
        verify(employeeRepository, times(1)).delete(any());
    }

    @Test
    void testDeleteWithMapperFailed() {
        doThrow(EmployeeNotFoundException.class).when(employeeRepository).delete(EMPLOYEE_ENTITY);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.delete(EMPLOYEE_DTO));
        verify(employeeRepository, times(1)).delete(any());
    }

    @Test
    void testByEmployeeGetGroupDtoSuccess() {
        when(modelMapper.toEntity(any(EmployeeDto.class))).thenReturn(EMPLOYEE_ENTITY);
        when(modelMapper.toDto(any(GroupEntity.class))).thenReturn(GROUP_DTO);
        when(groupRepository.findByEmployee(any(EmployeeEntity.class))).thenReturn(Optional.ofNullable(GROUP_ENTITY));
        GroupDto actualGroupDto = employeeService.findBy(EMPLOYEE_DTO);
        ArgumentCaptor<EmployeeDto> captor = ArgumentCaptor.forClass(EmployeeDto.class);
        verify(modelMapper, atLeastOnce()).toEntity(captor.capture());
        assertEquals(EMPLOYEE_DTO, captor.getValue());
        assertEquals(GROUP_DTO, actualGroupDto);
        verify(groupRepository).findByEmployee(EMPLOYEE_ENTITY);
        verify(groupRepository, times(1)).findByEmployee(EMPLOYEE_ENTITY);
        verify(groupRepository, never()).findByEmployee(isNull());
    }

    @Test
    void testByEmployeeGetGroupDtoWithMapperSuccess() {
        when(groupRepository.findByEmployee(any(EmployeeEntity.class))).thenReturn(Optional.ofNullable(GROUP_ENTITY));
        GroupDto groupDto = employeeService.findBy(EMPLOYEE_DTO);
        ArgumentCaptor<EmployeeDto> captor = ArgumentCaptor.forClass(EmployeeDto.class);
        verify(modelMapper).toEntity(captor.capture());
        assertEquals(EMPLOYEE_DTO, captor.getValue());
        assertEquals(GROUP_DTO, groupDto);
        verify(groupRepository).findByEmployee(EMPLOYEE_ENTITY);
        verify(groupRepository, times(1)).findByEmployee(EMPLOYEE_ENTITY);
        verify(groupRepository, never()).findByEmployee(isNull());
    }

    @Test
    void testFindByEmployeeSetNullEmployeeDtoFailed() {
        when(modelMapper.toEntity(any(EmployeeDto.class))).thenReturn(null);
        when(modelMapper.toDto(any(GroupEntity.class))).thenReturn(GROUP_DTO);
        when(groupRepository.findByEmployee(any(EmployeeEntity.class))).thenReturn(Optional.ofNullable(null));
        assertThrows(GroupNotFoundException.class, () -> employeeService.findBy(null));
        verify(groupRepository).findByEmployee(null);
        verify(groupRepository, times(1)).findByEmployee(isNull());
        verify(groupRepository, never()).findByEmployee(any(EmployeeEntity.class));
    }

    @Test
    void testFindByEmployeeGetNullGroupEntityFailed() {
        when(modelMapper.toEntity(any(EmployeeDto.class))).thenReturn(EMPLOYEE_ENTITY);
        when(modelMapper.toDto(any(GroupEntity.class))).thenReturn(GROUP_DTO);
        when(groupRepository.findByEmployee(any(EmployeeEntity.class))).thenReturn(Optional.ofNullable(null));
        assertThrows(GroupNotFoundException.class, () -> employeeService.findBy(EMPLOYEE_DTO));
        verify(groupRepository).findByEmployee(EMPLOYEE_ENTITY);
    }

    @Test
    void testByEmployeeIdGetGroupDtoSuccess() {
        when(modelMapper.toDto(any(GroupEntity.class))).thenReturn(GROUP_DTO);
        when(groupRepository.findByEmployeeId(anyLong())).thenReturn(Optional.ofNullable(GROUP_ENTITY));
        GroupDto actualGroupDto = employeeService.findById(1L);
        assertEquals(GROUP_DTO, actualGroupDto);
        verify(groupRepository).findByEmployeeId(1L);
        verify(groupRepository, times(1)).findByEmployeeId(1L);
        verify(groupRepository, never()).findByEmployeeId(isNull());
    }

    @Test
    void testByEmployeeIdGetGroupDtoWithMapperSuccess() {
        when(groupRepository.findByEmployeeId(anyLong())).thenReturn(Optional.ofNullable(GROUP_ENTITY));
        GroupDto actualGroupDto = employeeService.findById(1L);
        assertEquals(GROUP_DTO, actualGroupDto);
        verify(groupRepository).findByEmployeeId(1L);
        verify(groupRepository, times(1)).findByEmployeeId(1L);
        verify(groupRepository, never()).findByEmployeeId(isNull());
    }

    @Test
    void testFindByEmployeeIdSetNullEmployeeDtoFailed() {
        when(modelMapper.toDto(any(GroupEntity.class))).thenReturn(GROUP_DTO);
        when(groupRepository.findByEmployeeId(null)).thenReturn(Optional.ofNullable(null));
        assertThrows(GroupNotFoundException.class, () -> employeeService.findById(null));
        verify(groupRepository).findByEmployeeId(null);
        verify(groupRepository, times(1)).findByEmployeeId(isNull());
        verify(groupRepository, never()).findByEmployeeId(anyLong());
    }

    @Test
    void testFindByEmployeeIdGetNullGroupEntityFailed() {
        when(modelMapper.toDto(any(GroupEntity.class))).thenReturn(GROUP_DTO);
        when(groupRepository.findByEmployeeId(anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(GroupNotFoundException.class, () -> employeeService.findById(1L));
        verify(groupRepository).findByEmployeeId(anyLong());
        verify(groupRepository, times(1)).findByEmployeeId(anyLong());
    }

    @Test
    void testSubjectsByIdSuccess() {
        when(modelMapper.toDto(any(SubjectEntity.class))).thenReturn(SUBJECT_DTO).thenReturn(SUBJECT_DTO2);
        when(subjectRepository.findByEmployeeId(anyLong())).thenReturn(Optional.ofNullable(subjectEntities));
        List<SubjectDto> subjectDtos = employeeService.subjectsById(anyLong());
        assertEquals(subjectDtos.get(0), this.subjectDtos.get(0));
        assertEquals(subjectDtos.get(1), this.subjectDtos.get(1));
        assertArrayEquals(subjectDtos.toArray(), this.subjectDtos.toArray());
        verify(subjectRepository).findByEmployeeId(anyLong());
        verify(subjectRepository, times(1)).findByEmployeeId(anyLong());
    }

    @Test
    void testSubjectsByIdWithMapperSuccess() {
        when(subjectRepository.findByEmployeeId(anyLong())).thenReturn(Optional.ofNullable(subjectEntities));
        List<SubjectDto> subjectDtos = employeeService.subjectsById(anyLong());
        assertEquals(subjectDtos.get(0), this.subjectDtos.get(0));
        assertEquals(subjectDtos.get(1), this.subjectDtos.get(1));
        assertArrayEquals(subjectDtos.toArray(), this.subjectDtos.toArray());
        verify(subjectRepository).findByEmployeeId(anyLong());
        verify(subjectRepository, times(1)).findByEmployeeId(anyLong());
    }

    @Test
    void testSubjectsByIdSetNullWithMapperFailed() {
        when(subjectRepository.findByEmployeeId(null)).thenReturn(Optional.ofNullable(null));
        assertThrows(SubjectNotFoundException.class, () -> employeeService.subjectsById(null));
        verify(subjectRepository).findByEmployeeId(null);
        verify(subjectRepository, times(1)).findByEmployeeId(null);
        verify(subjectRepository, never()).findByEmployeeId(anyLong());
    }

    @Test
    void testSubjectsByIdGetNullWithMapperFailed() {
        when(subjectRepository.findByEmployeeId(1L)).thenReturn(Optional.ofNullable(null));
        assertThrows(SubjectNotFoundException.class, () -> employeeService.subjectsById(1L));
        verify(subjectRepository).findByEmployeeId(anyLong());
        verify(subjectRepository, times(1)).findByEmployeeId(anyLong());
        verify(subjectRepository, never()).findByEmployeeId(null);
    }


    @Test
    void lessonsBy() {
    }

    @Test
    void testLessonsBy() {
    }

    @Test
    void lessonsById() {
    }

    @Test
    void testLessonsBy1() {
    }

    @Test
    void testLessonsBy2() {
    }

    @Test
    void testLessonsBy3() {
    }

    @Test
    void studentLessonsBy() {
    }

    @Test
    void testStudentLessonsBy() {
    }

    @Test
    void testStudentLessonsBy1() {
    }

    @Test
    void getById() {
    }

    @Test
    void all() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void deleteById() {
    }
}