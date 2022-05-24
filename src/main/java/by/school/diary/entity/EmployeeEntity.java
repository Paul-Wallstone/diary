package by.school.diary.entity;

import by.school.diary.domain.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = false)
    LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Sex sex;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<SubjectEntity> subjects;

    @OneToOne
    private PositionEntity position;

    @OneToOne
    private UserEntity user;
    
    @OneToOne
    private ContactEntity contact;
}
