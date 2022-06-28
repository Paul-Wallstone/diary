package by.school.diary.service.impl;

import by.school.diary.domain.Sex;
import by.school.diary.dto.*;
import by.school.diary.entity.*;
import by.school.diary.repository.EmployeeRepository;
import by.school.diary.repository.GroupRepository;
import by.school.diary.repository.LessonRepository;
import by.school.diary.repository.SubjectRepository;
import by.school.diary.utils.mapper.CustomModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private CustomModelMapper modelMapper;
    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private final List<EmployeeDto> dtos = new ArrayList<>();
    private final List<EmployeeEntity> entities = new ArrayList<>();

    @BeforeEach
    void setUp() {

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

        EmployeeDto employeeDto = EmployeeDto
                .builder()
                .id(1L)
                .contact(contact)
                .info(info)
                .position(position)
                .username("user1")
                .institution(institution)
                .build();

        dtos.add(employeeDto);

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

        EmployeeEntity employeeEntity = EmployeeEntity
                .EBuilder()
                .contact(contactEntity)
                .info(infoEntity)
                .position(positionEntity)
                .username("user1")
                .institution(institutionEntity)
                .build();
        employeeEntity.setId(1L);
        entities.add(employeeEntity);
    }

    @Test
    void delete() {
    }

    @Test
    void findBy() {
        GroupEntity groupEntity = GroupEntity.builder().title("A1").build();
        GroupDto expected = GroupDto.builder().title("A1").build();
        EmployeeEntity employeeEntity = entities.get(0);
        doReturn(employeeEntity).when(modelMapper).toEntity(eq(dtos.get(0)));
//        when(modelMapper.toEntity((EmployeeDto) Mockito.any())).thenReturn(employeeEntity);
        when(groupRepository.findByEmployee(any(EmployeeEntity.class))).thenReturn(Optional.ofNullable(groupEntity));
        GroupDto actual = employeeService.findBy(dtos.get(0));
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
    }

    @Test
    void subjectsBy() {
    }

    @Test
    void subjectsById() {
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