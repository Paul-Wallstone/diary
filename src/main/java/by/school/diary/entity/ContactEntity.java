package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "contacts")
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class ContactEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 120)
    private String address;

    @Column(nullable = false, length = 10)
    private Integer postcode;

    @Column(length = 20)
    private String phone;
}
