package by.school.diary.entity;

import by.school.diary.domain.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "students")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"group", "parents", "diary", "user", "contact"})
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Sex sex;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false)
    Date birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private GroupEntity group;

    @OneToMany(cascade = CascadeType.MERGE,
            mappedBy = "student", orphanRemoval = true)
    @ToString.Exclude
    private final Set<ParentEntity> parents = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    @ToString.Exclude
    private DiaryEntity diary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private ContactEntity contact;

    public void addParent(ParentEntity parent) {
        this.parents.add(parent);
        parent.setStudent(this);
    }

    public void removeParent(ParentEntity parent) {
        parent.setStudent(null);
        this.parents.remove(parent);
    }

    public void removeParent() {
        for (ParentEntity parent : this.parents) {
            parent.setStudent(null);
        }
    }
}
