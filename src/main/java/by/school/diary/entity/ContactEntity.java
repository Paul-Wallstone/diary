package by.school.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "contacts")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
