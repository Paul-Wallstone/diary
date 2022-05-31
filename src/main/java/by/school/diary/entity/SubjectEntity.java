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
@EqualsAndHashCode(exclude="employees")
public class SubjectEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "subjects")
    @ToString.Exclude
    private Set<EmployeeEntity> employees = new HashSet<>();

    @Column(nullable = false, length = 125)
    private String title;
}
