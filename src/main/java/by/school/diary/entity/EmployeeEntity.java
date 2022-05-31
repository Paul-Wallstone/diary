package by.school.diary.entity;

import by.school.diary.domain.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "employees")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude="subjects")
public class EmployeeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false)
    LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Sex sex;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @ToString.Exclude
    private final Set<SubjectEntity> subjects = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    @ToString.Exclude
    private PositionEntity position;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private ContactEntity contact;

    public void addSubject(SubjectEntity subject) {
        this.subjects.add(subject);
        subject.getEmployees().add(this);
    }

    public void removeSubject(SubjectEntity subject) {
        this.subjects.remove(subject);
        subject.getEmployees().remove(this);
    }

    public void removeBooks() {
        for (SubjectEntity subject : this.subjects) {
            subject.getEmployees().remove(this);
        }
    }
}
