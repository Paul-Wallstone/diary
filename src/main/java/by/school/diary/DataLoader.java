package by.school.diary;

import by.school.diary.domain.Mark;
import by.school.diary.domain.Role;
import by.school.diary.domain.Sex;
import by.school.diary.entity.*;
import by.school.diary.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parent;
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
                      EmployeeRepository employeeRepository) {
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
                .verified(true)
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
                .verified(true)
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
                .verified(true)
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
                .verified(true)
                .roles(new HashSet<>(List.of(Role.ROLE_USER)))
                .build());
        log.info("Created: " + user4.toString());

        ContactEntity contact = contactRepository.save(ContactEntity.builder()
                .address("some address")
                .city("Minsk")
                .phone("+37529-311-31-31")
                .postcode(2022)
                .build());
        log.info("Created: " + contact.toString());

        ContactEntity contact2 = contactRepository.save(ContactEntity.builder()
                .address("some address")
                .city("Minsk")
                .phone("+37529-322-32-32")
                .postcode(2022)
                .build());
        log.info("Created: " + contact2.toString());

        PositionEntity position = positionRepository.save(PositionEntity.builder()
                .title("Director")
                .build());
        log.info("Created: " + position.toString());

        SubjectEntity subject = SubjectEntity.builder()
                .employees(new HashSet<>())
                .title("Math")
                .build();
        log.info("Created: " + subject.toString());

        EmployeeEntity employee = EmployeeEntity.builder()
                .birthday(now())
                .contact(contact)
                .sex(Sex.MALE)
                .user(user)
                .position(position)
                .build();
        employee.addSubject(subject);
        employeeRepository.save(employee);
        log.info("Created: " + employee.toString());

        GroupEntity group = GroupEntity.builder()
                .employee(employee)
                .title("A1")
                .build();
        GroupEntity groupSaved = groupRepository.save(group);
        log.info("Created: " + group.toString());

        ParentEntity parent = parentRepository.save(ParentEntity.builder()
                .contact(contact2)
                .user(user2)
                .build());
        log.info("Created: " + parent.toString());

        StudentEntity student = StudentEntity.builder()
                .birthday(new Date())
                .sex(Sex.MALE)
                .user(user3)
                .contact(contact2)
                .build();
        student.addParent(parent);
        StudentEntity studentSaved = studentRepository.save(student);
        log.info("Created: " + student.toString());

        StudentEntity student2 = StudentEntity.builder()
                .birthday(new Date())
                .sex(Sex.FEMALE)
                .user(user4)
                .contact(contact2)
                .build();
        student.addParent(parent);
        StudentEntity studentSaved2 = studentRepository.save(student2);
        log.info("Created: " + studentSaved2.toString());

        groupSaved.addStudent(studentSaved);
        groupSaved.addStudent(studentSaved2);
        GroupEntity groupSaved2 = groupRepository.save(groupSaved);
        log.info("Created: " + groupSaved2.toString());

        student = studentRepository.findById(studentSaved.getId()).get();
        DiaryEntity diary = DiaryEntity.builder().build();
        DiaryEntity diarySaved = diaryRepository.save(diary);
        student.setDiary(diarySaved);
        studentRepository.save(student);
        log.info("Created: " + student.toString());

        LessonEntity lesson = LessonEntity.builder()
                .date(new Date())
                .message("some message")
                .subject(subject)
                .employee(employee)
                .group(group)
                .build();
        LessonEntity lessonSaved = lessonRepository.save(lesson);
        log.info("Created: " + lessonSaved.toString());

        diary = diaryRepository.findById(diarySaved.getId()).get();
        diary.addLesson(lesson);
        diary = diaryRepository.save(diary);
        log.info("Created: " + diary.toString());
    }
}
