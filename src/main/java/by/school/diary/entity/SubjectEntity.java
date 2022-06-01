package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "subjects")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude="employees")
public class SubjectEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany(mappedBy = "subjects")
    @ToString.Exclude
    private final Set<EmployeeEntity> employees = new HashSet<>();

    @Column(nullable = false, length = 125)
    private String title;
}
