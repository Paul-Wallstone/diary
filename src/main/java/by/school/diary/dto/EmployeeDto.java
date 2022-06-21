package by.school.diary.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {

    private Long id;

    private String username;

    private InfoDto info;

    private ContactDto contact;

    private PositionDto position;

    private InstitutionDto institution;
}
