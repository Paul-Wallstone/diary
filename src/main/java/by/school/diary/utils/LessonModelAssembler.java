package by.school.diary.utils;

import by.school.diary.controller.LessonController;
import by.school.diary.dto.LessonDto;
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
public class LessonModelAssembler implements RepresentationModelAssembler<LessonDto, EntityModel<LessonDto>> {

    @NonNull
    @Override
    public EntityModel<LessonDto> toModel(LessonDto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(LessonController.class).getById(entity.getId())).withRel("lesson"),
                linkTo(methodOn(LessonController.class).getAll()).withRel("lessons"),
                linkTo(methodOn(LessonController.class).deleteById(entity.getId())).withRel("delete"),
                linkTo(methodOn(LessonController.class).save(entity)).withRel("save"),
                linkTo(methodOn(LessonController.class).update(entity.getId(), entity)).withRel("update"));
    }

    @NonNull
    @Override
    public CollectionModel<EntityModel<LessonDto>> toCollectionModel(Iterable<? extends LessonDto> entities) {
        List<LessonDto> responseDtos = StreamSupport.stream(entities.spliterator(), false)
                .collect(Collectors.toList());
        List<EntityModel<LessonDto>> lessoModels = responseDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(lessoModels, linkTo(methodOn(LessonController.class).getAll()).withSelfRel());
    }
}
