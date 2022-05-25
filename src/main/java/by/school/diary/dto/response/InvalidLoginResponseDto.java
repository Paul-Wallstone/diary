package by.school.diary.dto.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponseDto {

    private String username;
    private String password;

    public InvalidLoginResponseDto() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }
}
