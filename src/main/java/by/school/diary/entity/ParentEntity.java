package by.school.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name = "parents")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @ManyToOne
    private StudentEntity student;

    @OneToOne
    private ContactEntity contact;
}
