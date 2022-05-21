package by.school.diary.controller;

import by.school.diary.dto.ResponseUserDto;
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
    public EntityModel<ResponseUserDto> getUserById(@PathVariable @NotNull Long id) {
        ResponseUserDto userDto = userService.getUserById(id);
        return assembler.toModel(userDto);
    }

    @GetMapping
    public CollectionModel<EntityModel<ResponseUserDto>> getAllUsers() {
        List<ResponseUserDto> allUsers = userService.getAllUsers();
        return assembler.toCollectionModel(allUsers);
    }

    @PostMapping
    public EntityModel<ResponseUserDto> saveUser(@Valid @RequestBody ResponseUserDto userDto) {

        return assembler.toModel(userDto);
    }

}
