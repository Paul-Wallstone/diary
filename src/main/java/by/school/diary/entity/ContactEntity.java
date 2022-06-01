package by.school.diary.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "contacts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContactEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "City is mandatory")
    @Size(min = 2, message = "City must be at least 2 characters long")
    @Column(nullable = false, length = 30)
    private String city;

    @NotBlank(message = "Address is mandatory")
    @Size(min = 4, message = "Address must be at least 4 characters long")
    @Column(nullable = false, length = 120)
    private String address;

    @NotBlank(message = "Postcode is mandatory")
    @Size(min = 4, message = "Postcode must be at least 4 characters long")
    @Column(nullable = false, length = 10)
    private String postcode;

    @NotBlank(message = "Phone is mandatory")
    @Size(min = 5, message = "Phone must be at least 5 characters long")
    @Column(nullable = false, length = 20, unique = true)
    private String phone;
}
