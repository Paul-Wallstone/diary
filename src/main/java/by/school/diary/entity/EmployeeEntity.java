package by.school.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "employees")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SubjectEntity> subjects;
    @OneToOne
    private PositionEntity position;
    @OneToOne
    private UserEntity user;
    @OneToOne
    private ContactEntity contact;
}
