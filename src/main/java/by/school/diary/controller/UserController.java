package by.school.diary.controller;

import by.school.diary.domain.Role;
import by.school.diary.dto.UserDto;
import by.school.diary.service.UserService;
import by.school.diary.utils.assembler.UserModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Validated
@RestController
@RequestMapping(produces = "application/json", path = "/v1/users")
@Tag(name = "User Controller", description = "This REST controller provides user services in the Diary application")
public class UserController {

    private final UserService userService;

    private final UserModelAssembler assembler;

    public UserController(UserService userService, UserModelAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_ADMIN + "', " +
            "'" + Role.Fields.ROLE_TEACHER + "','" + Role.Fields.ROLE_DIRECTOR + "')")
    @Operation(summary = "Provides user details with id from the Diary application")
    public EntityModel<UserDto> getById(@PathVariable @NotNull Long id) {
        UserDto userDto = userService.getById(id);
        return assembler.toModel(userDto);
    }

    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_ADMIN + "', " +
            "'" + Role.Fields.ROLE_TEACHER + "','" + Role.Fields.ROLE_DIRECTOR + "')")
    @Operation(summary = "Provides all users available in the Diary application")
    public CollectionModel<EntityModel<UserDto>> getAll() {
        List<UserDto> allUsers = userService.getAll();
        return assembler.toCollectionModel(allUsers);
    }

    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_ADMIN + "')")
    @Operation(summary = "Creates a new user in the Diary application")
    public EntityModel<UserDto> save(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.save(userDto);
        return assembler.toModel(user);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_ADMIN + "')")
    @Operation(summary = "Delete user with id from the Diary application")
    public ResponseEntity<EntityModel<UserDto>> deleteById(@PathVariable @NotBlank Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(assembler.toModel(new UserDto()));
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_ADMIN + "')")
    @Operation(summary = "Update user with id from the Diary application")
    public EntityModel<UserDto> update(@PathVariable() Long id, @Valid @RequestBody UserDto userDto) {
        UserDto user = userService.update(userDto, id);
        return assembler.toModel(user);
    }

    @GetMapping("/current")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_USER + "')")
    @Operation(summary = "Get current user with id from the Diary application")
    public EntityModel<UserDto> getCurrentUser(Principal principal) {
        UserDto user = userService.getCurrentUser(principal);
        return assembler.toModel(user);
    }

    @PutMapping("/current/update")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_USER + "')")
    @Operation(summary = "Update current user from the Diary application")
    public EntityModel<UserDto> updateCurrentByPrincipal(@Valid @RequestBody UserDto userDto, Principal principal) {
        UserDto user = userService.updateCurrentByPrincipal(userDto, principal);
        return assembler.toModel(user);
    }
}
