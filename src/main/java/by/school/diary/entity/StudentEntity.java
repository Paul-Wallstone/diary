package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "students")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"group", "parents", "user", "contact"})
public class StudentEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "info_id")
    @ToString.Exclude
    private InfoEntity info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private GroupEntity group;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "student", orphanRemoval = true)
    @ToString.Exclude
    private final Set<ParentEntity> parents = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    @ToString.Exclude
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
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
