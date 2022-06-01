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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;


@Slf4j
@Component
public class DataLoader implements ApplicationRunner {

    UserRepository userRepository;
    ContactRepository contactRepository;
    DiaryRepository diaryRepository;
    GroupRepository groupRepository;
    LessonRepository lessonRepository;
    StudentRepository studentRepository;
    ParentRepository parentRepository;
    PositionRepository positionRepository;
    SubjectRepository subjectRepository;
    PasswordEncoder encoder;
    EmployeeRepository employeeRepository;
    InfoRepository infoRepository;

    @Autowired
    public DataLoader(UserRepository userRepository,
                      ContactRepository contactRepository,
                      DiaryRepository diaryRepository,
                      GroupRepository groupRepository,
                      LessonRepository lessonRepository,
                      StudentRepository studentRepository,
                      ParentRepository parentRepository,
                      PositionRepository positionRepository,
                      SubjectRepository subjectRepository,
                      PasswordEncoder encoder,
                      EmployeeRepository employeeRepository,
                      InfoRepository infoRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.diaryRepository = diaryRepository;
        this.groupRepository = groupRepository;
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.positionRepository = positionRepository;
        this.subjectRepository = subjectRepository;
        this.encoder = encoder;
        this.employeeRepository = employeeRepository;
        this.infoRepository = infoRepository;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity user = userRepository.save(UserEntity
                .builder()
                .firstName("John")
                .lastName("Socket")
                .username("jsocket")
                .password(encoder.encode("1234567"))
                .email("jsocket@example.com")
                .roles(new HashSet<>(List.of(Role.ROLE_USER, Role.ROLE_ADMIN)))
                .build());
        log.info("Created: " + user.toString());

        UserEntity user2 = userRepository.save(UserEntity
                .builder()
                .firstName("Mark")
                .lastName("Socket")
                .username("msocket")
                .password(encoder.encode("1234567"))
                .email("msocket@example.com")
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .build());
        log.info("Created: " + user2.toString());

        UserEntity user3 = userRepository.save(UserEntity
                .builder()
                .firstName("Sue")
                .lastName("Socket")
                .username("ssocket")
                .password(encoder.encode("1234567"))
                .email("ssocket@example.com")
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .build());
        log.info("Created: " + user3.toString());

        UserEntity user4 = userRepository.save(UserEntity
                .builder()
                .firstName("Kate")
                .lastName("Socket")
                .username("ksocket")
                .password(encoder.encode("1234567"))
                .email("ksocket@example.com")
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .build());
        log.info("Created: " + user4.toString());

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

        ContactEntity contact4 = contactRepository.save(ContactEntity.builder()
                .address("some address4")
                .city("Minsk")
                .phone("+37529-321-32-33")
                .postcode("2024")
                .build());
        log.info("Created: " + contact4.toString());

        GroupEntity group = GroupEntity.builder()
                .title("A1")
                .build();
        groupRepository.save(group);
        log.info("Created: " + group.toString());

        StudentEntity student = StudentEntity.builder()
                .user(user)
                .group(group)
                .contact(contact)
                .build();
        studentRepository.save(student);
        log.info("Created: " + student.toString());

        ParentEntity parent = parentRepository.save(ParentEntity.builder()
                .build());
        log.info("Created: " + parent.toString());

        student.addParent(parent);
        studentRepository.save(student);
        log.info("Created: " + student.toString());

        parent.setContact(contact2);
        parent.setUser(user2);
        parentRepository.save(parent);
        log.info("Created: " + parent.toString());

        InfoEntity info = InfoEntity.builder()
                .birthday(new Date())
                .sex(Sex.MALE)
                .bio("some bio")
                .build();

        InfoEntity info2 = InfoEntity.builder()
                .birthday(new Date())
                .sex(Sex.MALE)
                .bio("some bio2")
                .build();
        infoRepository.save(info2);
        log.info("Created: " + info2.toString());

        InfoEntity info3 = InfoEntity.builder()
                .birthday(new Date())
                .sex(Sex.FEMALE)
                .bio("some bio3")
                .build();
        infoRepository.save(info3);
        log.info("Created: " + info3.toString());

        InfoEntity info4 = InfoEntity.builder()
                .birthday(new Date())
                .sex(Sex.FEMALE)
                .bio("some bio4")
                .build();
        infoRepository.save(info4);
        log.info("Created: " + info4.toString());

        student.setInfo(info);
        studentRepository.save(student);
        log.info("Created: " + student.toString());

        parent.setInfo(info2);
        parentRepository.save(parent);
        log.info("Created: " + parent.toString());

        EmployeeEntity employee = EmployeeEntity.builder()
                .user(user3)
                .build();
        employeeRepository.save(employee);
        log.info("Created: " + employee.toString());

        PositionEntity position = PositionEntity.builder()
                .title("Director")
                .build();
        positionRepository.save(position);
        log.info("Created: " + position.toString());

        employee.setContact(contact3);
        employee.setInfo(info3);
        employee.setPosition(position);
        employeeRepository.save(employee);
        log.info("Created: " + employee.toString());


        SubjectEntity subject = SubjectEntity.builder()
                .title("Math")
                .build();
        subjectRepository.save(subject);
        log.info("Created: " + subject.toString());

        SubjectEntity subject2 = SubjectEntity.builder()
                .title("English")
                .build();
        subjectRepository.save(subject2);
        log.info("Created: " + subject2.toString());

        employee.getSubjects().add(subject);
        employeeRepository.save(employee);
        log.info("Created: " + employee.toString());

        group.setEmployee(employee);
        groupRepository.save(group);
        log.info("Created: " + group.toString());

        DiaryEntity diary = DiaryEntity.builder()
                .student(student)
                .build();
        diaryRepository.save(diary);
        log.info("Created: " + diary.toString());

        StudentEntity student2 = StudentEntity.builder()
                .user(user4)
                .group(group)
                .contact(contact4)
                .info(info4)
                .build();
        studentRepository.save(student2);
        log.info("Created: " + student2.toString());

        LessonEntity lesson = LessonEntity.builder()
                .date(new Date())
                .subject(subject)
                .group(group)
                .build();
        log.info("Created: " + lesson.toString());

        LessonEntity lesson2 = LessonEntity.builder()
                .date(new Date())
                .subject(subject2)
                .group(group)
                .build();
        log.info("Created: " + lesson2.toString());

        diary.addLesson(lesson);
        diary.addLesson(lesson2);
        diaryRepository.save(diary);
        log.info("Created: " + diary.toString());

        DiaryEntity diary2 = DiaryEntity.builder()
                .student(student2)
                .build();
        diary2.addLesson(lesson);
        diary2.addLesson(lesson2);
        diaryRepository.save(diary2);
        log.info("Created: " + diary2.toString());

        diary.setMark(Mark.EIGHT, 1L);
        diary2.setMark(Mark.FIVE, 3L);
        diaryRepository.save(diary);
        diaryRepository.save(diary2);

    }
}
