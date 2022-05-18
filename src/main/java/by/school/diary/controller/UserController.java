package by.school.diary.controller;

import by.school.diary.dto.UserDto;
import by.school.diary.service.UserService;
import by.school.diary.utils.UserModelAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json",path = "/users")
@Tag(name = "User Controller", description = "This REST controller provides user services in the Diary application")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserModelAssembler assembler;

    @GetMapping("{id}")
    public EntityModel<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return assembler.toModel(userDto);
    }

    @GetMapping
    public CollectionModel<EntityModel<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return assembler.toCollectionModel(allUsers);
    }
}
