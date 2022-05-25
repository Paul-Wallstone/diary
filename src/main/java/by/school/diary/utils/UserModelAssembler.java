package by.school.diary.utils;


import by.school.diary.controller.UserController;
import by.school.diary.dto.response.UserResponseDto;
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
public class UserModelAssembler implements RepresentationModelAssembler<UserResponseDto, EntityModel<UserResponseDto>> {
    private final CustomModelMapper modelMapper;

    public UserModelAssembler(CustomModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @NonNull
    @Override
    public EntityModel<UserResponseDto> toModel(@NonNull UserResponseDto user) {

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getById(user.getId())).withRel("user"),
                linkTo(methodOn(UserController.class).getAll()).withRel("users"),
                linkTo(methodOn(UserController.class).deleteById(user.getId())).withRel("delete"),
                linkTo(methodOn(UserController.class).save(modelMapper.toDto(user))).withRel("save"),
                linkTo(methodOn(UserController.class).update(user.getId(), modelMapper.toDto(user))).withRel("update"));
    }

    @NonNull
    @Override
    public CollectionModel<EntityModel<UserResponseDto>> toCollectionModel(Iterable<? extends UserResponseDto> users) {
        List<UserResponseDto> userDtos = StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());
        List<EntityModel<UserResponseDto>> usersModel = userDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usersModel, linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }
}
