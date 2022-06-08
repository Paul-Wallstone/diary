package by.school.diary.utils;

import by.school.diary.controller.LessonController;
import by.school.diary.dto.response.LessonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LessonModelAssembler implements RepresentationModelAssembler<LessonResponseDto, EntityModel<LessonResponseDto>> {
    private final CustomModelMapper modelMapper;

    @Autowired
    public LessonModelAssembler(CustomModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @NonNull
    @Override
    public EntityModel<LessonResponseDto> toModel(LessonResponseDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(LessonController.class).getById(entity.getId())).withRel("lesson"),
                linkTo(methodOn(LessonController.class).getAll()).withRel("lessons"),
                linkTo(methodOn(LessonController.class).deleteById(entity.getId())).withRel("delete"),
                linkTo(methodOn(LessonController.class).save(modelMapper.toDto(entity))).withRel("save"),
                linkTo(methodOn(LessonController.class).update(entity.getId(), modelMapper.toDto(entity))).withRel("update"));
    }

    @NonNull
    @Override
    public CollectionModel<EntityModel<LessonResponseDto>> toCollectionModel(Iterable<? extends LessonResponseDto> entities) {
        List<LessonResponseDto> responseDtos = StreamSupport.stream(entities.spliterator(), false)
                .collect(Collectors.toList());
        List<EntityModel<LessonResponseDto>> lessoModels = responseDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(lessoModels, linkTo(methodOn(LessonController.class).getAll()).withSelfRel());
    }
}
