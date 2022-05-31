package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "groups")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"employee", "students"})
public class GroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @ToString.Exclude
    private EmployeeEntity employee;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "group", orphanRemoval = true)
    @ToString.Exclude
    private final Set<StudentEntity> students = new HashSet<>();

    public void addStudent(StudentEntity student) {
        this.students.add(student);
        student.setGroup(this);
    }
    public void removeStudent(StudentEntity student) {
        student.setGroup(null);
        this.students.remove(student);
    }
    public void removeStudents() {
        for (StudentEntity student : this.students) {
            student.setGroup(null);
        }
    }
}
