package by.school.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
