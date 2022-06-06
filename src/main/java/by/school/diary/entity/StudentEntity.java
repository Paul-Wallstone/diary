package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"group", "parents", "user", "contact"})
public class StudentEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    @ToString.Exclude
    private InfoEntity info;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private ContactEntity contact;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private GroupEntity group;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "student", orphanRemoval = true)
    @ToString.Exclude
    private final Set<ParentEntity> parents = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @ToString.Exclude
    private UserEntity user;


    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "student")
    @ToString.Exclude
    @Builder.Default
    private Set<ScheduleEntity> lessons = new HashSet<>();

    public void setParent(ParentEntity parent) {
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
