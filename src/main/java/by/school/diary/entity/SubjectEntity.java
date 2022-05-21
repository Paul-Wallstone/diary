package by.school.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "subjects")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "subjects")
    private Set<EmployeeEntity> employees;
    @Column(nullable = false, length = 125)
    private String title;
}
