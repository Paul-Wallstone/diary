package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "parents")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"student", "user"})
public class ParentEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private StudentEntity student;

}
