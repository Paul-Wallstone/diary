package by.school.diary.utils;


import by.school.diary.controller.UserController;
import by.school.diary.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {
    @Override
    public EntityModel<UserDto> toModel(UserDto user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
    }

    @Override
    public CollectionModel<EntityModel<UserDto>> toCollectionModel(Iterable<? extends UserDto> users) {
        List<UserDto> userDtos = StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());
        List<EntityModel<UserDto>> usersModel = userDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usersModel, linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }
}
