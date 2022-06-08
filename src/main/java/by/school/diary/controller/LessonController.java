package by.school.diary.controller;

import by.school.diary.domain.Role;
import by.school.diary.dto.request.LessonRequestDto;
import by.school.diary.dto.response.LessonResponseDto;
import by.school.diary.service.LessonService;
import by.school.diary.utils.LessonModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Validated
@RestController
@RequestMapping(produces = "application/json", path = "/v1/lessons")
@Tag(name = "Lesson Controller", description = "This REST controller provides lesson services in the Diary application")
public class LessonController {
    private final LessonService lessonService;

    private final LessonModelAssembler assembler;

    @Autowired
    public LessonController(LessonService lessonService, LessonModelAssembler assembler) {
        this.lessonService = lessonService;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_USER + "')")
    @Operation(summary = "Provides lesson details with id from the Diary application")
    public EntityModel<LessonResponseDto> getById(@PathVariable @NotNull Long id) {
        LessonResponseDto dto = lessonService.getById(id);
        return assembler.toModel(dto);
    }

    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all lessons available in the Diary application")
    public CollectionModel<EntityModel<LessonResponseDto>> getAll() {
        List<LessonResponseDto> allUsers = lessonService.getAll();
        return assembler.toCollectionModel(allUsers);
    }

    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates a new lesson in the Diary application")
    public EntityModel<LessonResponseDto> save(@Valid @RequestBody LessonRequestDto requestDto) {
        LessonResponseDto responseDto = lessonService.save(requestDto);
        return assembler.toModel(responseDto);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete lesson with id from the Diary application")
    public ResponseEntity<EntityModel<LessonResponseDto>> deleteById(@PathVariable @NotBlank Long id) {
        lessonService.deleteById(id);
        return ResponseEntity.ok(assembler.toModel(new LessonResponseDto()));
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Update lesson with id from the Diary application")
    public EntityModel<LessonResponseDto> update(@PathVariable() Long id, @Valid @RequestBody LessonRequestDto requestDto) {
        LessonResponseDto user = lessonService.update(requestDto, id);
        return assembler.toModel(user);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Update lesson with id from the Diary application")
    public EntityModel<LessonResponseDto> update(@Valid @RequestBody LessonRequestDto requestDto) {
        LessonResponseDto user = lessonService.update(requestDto);
        return assembler.toModel(user);
    }

}
