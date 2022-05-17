package by.school.diary.entity;

import by.school.diary.domain.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
    private Sex sex;
    @ManyToOne
    private Group group;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId")
    private Set<Parent> parents;
    @OneToOne(cascade = CascadeType.ALL)
    private Diary diary;
    @OneToOne(cascade = CascadeType.ALL)
    private AppUser user;
}
