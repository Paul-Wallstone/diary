package by.school.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "contacts")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String city;
    @Column(nullable = false, length = 120)
    private String address;
    @Column(nullable = false, length = 10)
    private Integer postcode;
    @Column(length = 20)
    private String phone;
}
