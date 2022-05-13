package by.school.diary.repository;

import by.school.diary.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
