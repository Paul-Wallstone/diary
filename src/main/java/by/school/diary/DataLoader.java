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
    ScheduleRepository scheduleRepository;
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
                      ScheduleRepository scheduleRepository,
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
        this.scheduleRepository = scheduleRepository;
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
        log.info("Created: " + user.toString());
        GroupEntity group = GroupEntity.builder()
                .title("A1")
                .build();
        log.info("Created: " + group.toString());

        StudentEntity student = StudentEntity.SBuilder()
                .username("fsocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .group(group)
                .build();
        studentRepository.save(student);
        log.info("Created: " + student.toString());

        StudentEntity student2 = StudentEntity.SBuilder()
                .username("msocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .group(group)
                .build();
        studentRepository.save(student2);
        log.info("Created: " + student2.toString());

        EmployeeEntity employee = EmployeeEntity.EBuilder()
                .username("ssocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .build();

        employeeRepository.save(employee);
        log.info("Created: " + employee.toString());

        SubjectEntity subject = SubjectEntity.builder()
                .title("Math")
                .employee(employee)
                .build();
        subjectRepository.save(subject);
        log.info("Created: " + subject.toString());

        LessonEntity lesson = LessonEntity.builder()
                .date(LocalDate.of(2022, 6, 6))
                .timeFrom(LocalTime.of(12, 0))
                .timeTo(LocalTime.of(12, 45))
                .subject(subject)
                .group(group)
                .build();
        lesson.setEmployee(employee);
        lessonRepository.save(lesson);


        StudentLessonEntity studentLessonEntity = StudentLessonEntity.builder()
                .lesson(lesson)
                .build();
        studentLessonEntity.setStudent(student);

        StudentLessonEntity studentLessonEntity2 = StudentLessonEntity.builder()
                .lesson(lesson)
                .build();
        studentLessonEntity2.setStudent(student2);
        log.info("Created: " + studentLessonEntity.toString());
        log.info("Created: " + studentLessonEntity2.toString());

        ContactEntity contact = ContactEntity.builder()
                .address("some address")
                .city("Minsk")
                .phone("+37529-311-31-35")
                .postcode("2021")
                .build();
        log.info("Created: " + contact.toString());

        ContactEntity contact2 = contactRepository.save(ContactEntity.builder()
                .address("some address2")
                .city("Minsk")
                .phone("+37529-322-32-36")
                .postcode("2022")
                .build());
        log.info("Created: " + contact2.toString());

        ContactEntity contact3 = contactRepository.save(ContactEntity.builder()
                .address("some address3")
                .city("Minsk")
                .phone("+37529-322-32-34")
                .postcode("2023")
                .build());
        log.info("Created: " + contact3.toString());

        InfoEntity info = InfoEntity.builder()
                .birthday(LocalDate.of(2000, 12, 12))
                .sex(Sex.MALE)
                .bio("some bio")
                .build();
        log.info("Created: " + info.toString());

        InfoEntity info2 = InfoEntity.builder()
                .birthday(LocalDate.of(2000, 12, 12))
                .sex(Sex.MALE)
                .bio("some bio2")
                .build();
        log.info("Created: " + info2.toString());

        InfoEntity info3 = InfoEntity.builder()
                .birthday(LocalDate.of(2000, 12, 12))
                .sex(Sex.FEMALE)
                .bio("some bio3")
                .build();
        log.info("Created: " + info3.toString());

        ParentEntity parent = ParentEntity.PBuilder()
                .username("psocket")
                .password(encoder.encode("1234567"))
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .build();
        log.info("Created: " + parent.toString());

        student.setParent(parent);
        log.info("Created: " + student.toString());

        student.setInfo(info);
        student2.setInfo(info2);
        student.setContact(contact);
        student2.setContact(contact2);

        PositionEntity position = PositionEntity.builder()
                .title("Director")
                .build();
        employee.setPosition(position);

        studentLessonEntity2.setMark(Mark.EIGHT);

        InstitutionEntity institution = InstitutionEntity.builder()
                .title("School")
                .build();
        institution.setEmployee(employee);
        institution.setGroup(group);
        institutionRepository.save(institution);
    }
}
