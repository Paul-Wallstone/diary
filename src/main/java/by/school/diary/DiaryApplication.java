package by.school.diary;

import by.school.diary.domain.Role;
import by.school.diary.entity.UserEntity;
import by.school.diary.repository.UserRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiaryApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${app.description}") String appDescription,
                                 @Value("${app.version}") String appVersion) {

        return new OpenAPI().info(new Info().title("Diary API").version(appVersion)
                .description(appDescription).termsOfService("http://swagger.io/terms/")
                .license(new License().name("Open API 1.0").url("http://springdoc.org")));
    }

//    @Bean
//    public ApplicationRunner dataLoader(UserRepository repo, PasswordEncoder encoder) {
//        return args ->
//            repo.save(UserEntity
//                    .builder()
//                    .firstName("John")
//                    .lastName("Socket")
//                    .username("jsocket")
//                    .password(encoder.encode("123"))
//                    .email("jsocket@example.com")
//                    .verified(true)
//                    .roles(new HashSet<>(List.of(Role.ROLE_USER, Role.ROLE_ADMIN)))
//                    .build());
//
//    }

}
