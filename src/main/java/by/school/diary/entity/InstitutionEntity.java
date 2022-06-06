package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "institutions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"contact", "groups"})
public class InstitutionEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 125)
    private String title;

    @OneToOne
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private ContactEntity contact;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "institution", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private Set<GroupEntity> groups = new HashSet<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "institution", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private Set<EmployeeEntity> employees = new HashSet<>();

    public void setGroup(GroupEntity group) {
        this.groups.add(group);
        group.setInstitution(this);
    }

    public void removeGroup(GroupEntity group) {
        this.groups.remove(group);
        group.setEmployee(null);
    }

    public void removeGroups() {
        for (GroupEntity group : this.groups) {
            group.setEmployee(null);
        }
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employees.add(employee);
        employee.setInstitution(this);
    }

    public void removeEmployee(EmployeeEntity employee) {
        this.employees.remove(employee);
        employee.setInstitution(null);
    }

    public void removeEmployees() {
        for (EmployeeEntity employee : this.employees) {
            employee.setInstitution(null);
        }
    }

}
