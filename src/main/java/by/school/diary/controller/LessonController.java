package by.school.diary.controller;

import by.school.diary.domain.Role;
import by.school.diary.dto.LessonDto;
import by.school.diary.service.LessonService;
import by.school.diary.utils.assembler.LessonModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping(produces = "application/json", path = "/v1/lessons")
@Tag(name = "Lesson Controller", description = "This REST controller provides lesson services in the Diary application")
public class LessonController {
    private final LessonService lessonService;

    private final LessonModelAssembler assembler;

    private PagedResourcesAssembler<LessonDto> pagedResourcesAssembler;

    @Autowired
    public LessonController(LessonService lessonService, LessonModelAssembler assembler, PagedResourcesAssembler<LessonDto> pagedResourcesAssembler) {
        this.lessonService = lessonService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

//Todo стоит подумать какой должен быть LessonDto, какова его вложенность ... может и упростить

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_USER + "')")
    @Operation(summary = "Provides lesson details with id from the Diary application")
    public EntityModel<LessonDto> getById(@PathVariable @NotNull Long id) {
        LessonDto dto = lessonService.getById(id);
        return assembler.toModel(dto);
    }

    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all lessons available in the Diary application")
    public CollectionModel<EntityModel<LessonDto>> getAll() {
        List<LessonDto> lessonDtos = lessonService.getAll();
        return assembler.toCollectionModel(lessonDtos);
    }

    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates a new lesson in the Diary application")
    public EntityModel<LessonDto> save(@Valid @RequestBody LessonDto requestDto) {
        LessonDto responseDto = lessonService.save(requestDto);
        return assembler.toModel(responseDto);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete lesson with id from the Diary application")
    public ResponseEntity<EntityModel<LessonDto>> deleteById(@PathVariable @NotBlank Long id) {
        lessonService.deleteById(id);
        return ResponseEntity.ok(assembler.toModel(new LessonDto()));
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Update lesson with id from the Diary application")
    public EntityModel<LessonDto> update(@PathVariable() Long id, @Valid @RequestBody LessonDto requestDto) {
        LessonDto user = lessonService.update(requestDto, id);
        return assembler.toModel(user);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Update lesson with id from the Diary application")
    public EntityModel<LessonDto> update(@Valid @RequestBody LessonDto requestDto) {
        LessonDto user = lessonService.update(requestDto);
        return assembler.toModel(user);
    }

    @GetMapping("/page")
    @ResponseStatus(code = HttpStatus.OK)
    public PagedModel<EntityModel<LessonDto>> allByDates(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                         @RequestParam LocalDate from,
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                         @RequestParam LocalDate to,
                                                         @PageableDefault(value = 10, page = 0, sort = "date") Pageable pageable) {
        Page<LessonDto> lessonDto = lessonService.allByDates(from, to, pageable);
        return pagedResourcesAssembler.toModel(lessonDto, assembler);
    }

    @GetMapping("/page/employee-id")
    @ResponseStatus(code = HttpStatus.OK)
    public PagedModel<EntityModel<LessonDto>> allByDatesAndEmployeeId(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      @RequestParam LocalDate from,
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      @RequestParam LocalDate to,
                                                                      @RequestParam Long id,
                                                                      @PageableDefault(value = 10, page = 0, sort = "date") Pageable pageable) {
        Page<LessonDto> lessonDto = lessonService.allByDatesAndEmployeeId(from, to, id, pageable);
        return pagedResourcesAssembler.toModel(lessonDto, assembler);
    }

    @GetMapping("/page/subject-id")
    @ResponseStatus(code = HttpStatus.OK)
    public PagedModel<EntityModel<LessonDto>> allByDatesAAndSubjectId(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      @RequestParam LocalDate from,
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      @RequestParam LocalDate to,
                                                                      @RequestParam Long id,
                                                                      @PageableDefault(value = 10, page = 0, sort = "date") Pageable pageable) {
        Page<LessonDto> lessonDto = lessonService.allByDatesAndSubjectId(from, to, id, pageable);
        return pagedResourcesAssembler.toModel(lessonDto, assembler);
    }

    @GetMapping("/page/group-id")
    @ResponseStatus(code = HttpStatus.OK)
    public PagedModel<EntityModel<LessonDto>> allByDatesAndGroupId(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                   @RequestParam LocalDate from,
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                   @RequestParam LocalDate to,
                                                                   @RequestParam Long id,
                                                                   @PageableDefault(value = 10, page = 0, sort = "date") Pageable pageable) {
        Page<LessonDto> lessonDto = lessonService.allByDatesAndGroupId(from, to, id, pageable);
        return pagedResourcesAssembler.toModel(lessonDto, assembler);
    }


}
