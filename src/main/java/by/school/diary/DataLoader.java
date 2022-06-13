package by.school.diary;

import by.school.diary.domain.Mark;
import by.school.diary.domain.Role;
import by.school.diary.domain.Sex;
import by.school.diary.entity.*;
import by.school.diary.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;


@Slf4j
@Component
public class DataLoader implements ApplicationRunner {

    UserRepository userRepository;
    ContactRepository contactRepository;
    GroupRepository groupRepository;
    StudentLessonRepository studentLessonRepository;
    StudentRepository studentRepository;
    ParentRepository parentRepository;
    PositionRepository positionRepository;
    SubjectRepository subjectRepository;
    PasswordEncoder encoder;
    EmployeeRepository employeeRepository;
    InfoRepository infoRepository;
    InstitutionRepository institutionRepository;
    LessonRepository lessonRepository;


    @Autowired
    public DataLoader(UserRepository userRepository,
                      InstitutionRepository institutionRepository,
                      ContactRepository contactRepository,
                      GroupRepository groupRepository,
                      StudentLessonRepository studentLessonRepository,
                      StudentRepository studentRepository,
                      ParentRepository parentRepository,
                      PositionRepository positionRepository,
                      SubjectRepository subjectRepository,
                      PasswordEncoder encoder,
                      EmployeeRepository employeeRepository,
                      LessonRepository lessonRepository,
                      InfoRepository infoRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.groupRepository = groupRepository;
        this.studentLessonRepository = studentLessonRepository;
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.positionRepository = positionRepository;
        this.subjectRepository = subjectRepository;
        this.encoder = encoder;
        this.employeeRepository = employeeRepository;
        this.infoRepository = infoRepository;
        this.institutionRepository = institutionRepository;
        this.lessonRepository = lessonRepository;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity user = userRepository.save(UserEntity
                .builder()
                .username("jsocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER, Role.ROLE_ADMIN)))
                .build());
        userRepository.save(user);

        GroupEntity group = GroupEntity.builder()
                .title("A1")
                .build();

        StudentEntity student = StudentEntity.SBuilder()
                .username("fsocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .group(group)
                .build();
        studentRepository.save(student);

        StudentEntity student2 = StudentEntity.SBuilder()
                .username("msocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .group(group)
                .build();
        studentRepository.save(student2);

        EmployeeEntity employee = EmployeeEntity.EBuilder()
                .username("ssocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .build();
        employeeRepository.save(employee);

        SubjectEntity subject = SubjectEntity.builder()
                .title("Math")
                .employee(employee)
                .build();
        subjectRepository.save(subject);

        LessonEntity lesson = LessonEntity.builder()
                .date(LocalDate.of(2022, 6, 6))
                .timeFrom(LocalTime.of(12, 0))
                .timeTo(LocalTime.of(12, 45))
                .subject(subject)
                .group(group)
                .build();
        lesson.setEmployee(employee);
        lessonRepository.save(lesson);

        LessonEntity lesson2 = LessonEntity.builder()
                .date(LocalDate.of(2022, 6, 5))
                .timeFrom(LocalTime.of(12, 0))
                .timeTo(LocalTime.of(12, 45))
                .subject(subject)
                .group(group)
                .build();
        lesson2.setEmployee(employee);
        lessonRepository.save(lesson2);

        StudentLessonEntity studentLesson = StudentLessonEntity.builder()
                .lesson(lesson)
                .student(student)
                .build();
        studentLessonRepository.save(studentLesson);

        StudentLessonEntity studentLesson3 = StudentLessonEntity.builder()
                .lesson(lesson)
                .student(student2)
                .build();
        studentLessonRepository.save(studentLesson3);

        StudentLessonEntity studentLesson2 = StudentLessonEntity.builder()
                .lesson(lesson2)
                .student(student)
                .build();
        studentLessonRepository.save(studentLesson2);

        StudentLessonEntity studentLesson4 = StudentLessonEntity.builder()
                .lesson(lesson2)
                .student(student2)
                .build();
        studentLessonRepository.save(studentLesson4);


        ContactEntity contact = ContactEntity.builder()
                .address("some address")
                .city("Minsk")
                .phone("+37529-311-31-35")
                .postcode("2021")
                .build();

        ContactEntity contact2 = contactRepository.save(ContactEntity.builder()
                .address("some address2")
                .city("Minsk")
                .phone("+37529-322-32-36")
                .postcode("2022")
                .build());

        ContactEntity contact3 = contactRepository.save(ContactEntity.builder()
                .address("some address3")
                .city("Minsk")
                .phone("+37529-322-32-34")
                .postcode("2023")
                .build());

        InfoEntity info = InfoEntity.builder()
                .firstName("Bname1")
                .lastName("lastName1")
                .email("name1@gmail.com")
                .birthday(LocalDate.of(2000, 12, 12))
                .sex(Sex.MALE)
                .bio("some bio")
                .build();

        InfoEntity info2 = InfoEntity.builder()
                .firstName("Dname2")
                .lastName("lastName2")
                .email("name2@gmail.com")
                .birthday(LocalDate.of(2000, 12, 12))
                .sex(Sex.MALE)
                .bio("some bio2")
                .build();

        InfoEntity info3 = InfoEntity.builder()
                .firstName("Fname3")
                .lastName("lastName3")
                .email("name3@gmail.com")
                .birthday(LocalDate.of(2000, 12, 12))
                .sex(Sex.FEMALE)
                .bio("some bio3")
                .build();

        ParentEntity parent = ParentEntity.PBuilder()
                .username("psocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .build();

        student.setParent(parent);

        student.setInfo(info);
        student2.setInfo(info2);
        employee.setInfo(info3);
        student.setContact(contact);
        student2.setContact(contact2);
        employee.setContact(contact3);

        PositionEntity position = PositionEntity.builder()
                .title("Director")
                .build();
        employee.setPosition(position);

        studentLesson2.setMark(Mark.EIGHT);

        InstitutionEntity institution = InstitutionEntity.builder()
                .title("School")
                .build();
        institution.setEmployee(employee);
        institution.setGroup(group);
        institutionRepository.save(institution);
    }
}
