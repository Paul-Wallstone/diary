package by.school.diary.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ContactRequestDto {
	@NotBlank(message = "City is mandatory")
	@Size(min = 2, message = "City must be at least 2 characters long")
	private String city;

	@NotBlank(message = "Address is mandatory")
	@Size(min = 4, message = "Address must be at least 4 characters long")
	private String address;

	@NotBlank(message = "Postcode is mandatory")
	@Size(min = 4, message = "Postcode must be at least 4 characters long")
	private String postcode;

	@NotBlank(message = "Phone is mandatory")
	@Size(min = 5, message = "Phone must be at least 5 characters long")
	private String phone;
}
