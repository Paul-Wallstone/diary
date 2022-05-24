package by.school.diary.entity;

import by.school.diary.domain.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "students")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactEntity contact;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Sex sex;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false)
    LocalDateTime birthday;

    @ManyToOne
    private GroupEntity group;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", orphanRemoval = true)
    private Set<ParentEntity> parents;

    @OneToOne(cascade = CascadeType.ALL)
    private DiaryEntity diary;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
}
