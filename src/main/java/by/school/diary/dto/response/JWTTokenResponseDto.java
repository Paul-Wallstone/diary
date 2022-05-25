package by.school.diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenResponseDto {
    private boolean success;
    private String token;
}
