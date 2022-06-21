package by.school.diary.dto;

import by.school.diary.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto {

    private Long id;

    private String title;

    private EmployeeDto employee;

}
