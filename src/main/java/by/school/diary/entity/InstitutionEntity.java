package by.school.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "institutions")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstitutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 125)
    private String title;
    @OneToOne
    private ContactEntity contact;
}
