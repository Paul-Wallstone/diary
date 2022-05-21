package by.school.diary.controller;

import by.school.diary.dto.UserDto;
import by.school.diary.service.UserService;
import by.school.diary.utils.UserModelAssembler;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping(produces = "application/json", path = "/users")
@Tag(name = "User Controller", description = "This REST controller provides user services in the Diary application")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserModelAssembler assembler;

    @GetMapping("{id}")
    public EntityModel<UserDto> getUserById(@PathVariable @NotNull Long id) {
        UserDto userDto = userService.getUserById(id);
        return assembler.toModel(userDto);
    }

    @GetMapping
    public CollectionModel<EntityModel<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return assembler.toCollectionModel(allUsers);
    }

    @PostMapping
    public EntityModel<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {

        return assembler.toModel(userDto);
    }

}
