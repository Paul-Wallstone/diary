package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "lessons",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "time_from", "time_to", "employee_id"})})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"subject", "group", "employee"})
public class LessonEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "time_from", nullable = false)
    @NotNull
    private LocalTime timeFrom;

    @Column(name = "time_to", nullable = false)
    @NotNull
    private LocalTime timeTo;

    @OneToOne
    @JoinColumn(name = "subject_id")
    @NotNull
    @ToString.Exclude
    private SubjectEntity subject;

    @OneToOne
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    @ToString.Exclude
    private EmployeeEntity employee;

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
        this.employee.getLessons().add(this);
    }

}
