package by.school.diary.controller;

import by.school.diary.dto.RequestUserDto;
import by.school.diary.dto.ResponseUserDto;
import by.school.diary.service.UserService;
import by.school.diary.utils.UserModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping(produces = "application/json", path = "/v1")
@Tag(name = "User Controller", description = "This REST controller provides user services in the Diary application")
public class UserController {

    private final UserService userService;

    private final UserModelAssembler assembler;

    public UserController(UserService userService, UserModelAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides user details with id from the Diary application")
    public EntityModel<ResponseUserDto> getById(@PathVariable @NotNull Long id) {
        ResponseUserDto userDto = userService.getById(id);
        return assembler.toModel(userDto);
    }

    @GetMapping("/users")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all users available in the Diary application")
    public CollectionModel<EntityModel<ResponseUserDto>> getAll() {
        List<ResponseUserDto> allUsers = userService.getAll();
        return assembler.toCollectionModel(allUsers);
    }

    @PostMapping("/users")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates a new user in the Diary application")
    public EntityModel<ResponseUserDto> save(@Valid @RequestBody RequestUserDto userDto) {
        ResponseUserDto user = userService.save(userDto);
        return assembler.toModel(user);
    }

    @DeleteMapping("/users/{id}/delete")
    @Operation(summary = "Delete user with id from the Diary application")
    public ResponseEntity<EntityModel<ResponseUserDto>> deleteById(@PathVariable @NotBlank Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(assembler.toModel(new ResponseUserDto()));
    }

    @PutMapping("/users/{id}/update")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Update user with id from the Diary application")
    public EntityModel<ResponseUserDto> update(@PathVariable() Long id, @Valid @RequestBody RequestUserDto userDto) {
        ResponseUserDto user = userService.update(userDto, id);
        return assembler.toModel(user);
    }
}
