package by.school.diary.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactDto {
    Long id;

    private String city;

    private String address;

    private String postcode;

    private String phone;
}
