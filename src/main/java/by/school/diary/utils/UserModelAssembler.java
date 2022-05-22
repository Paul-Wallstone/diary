package by.school.diary.utils;


import by.school.diary.controller.UserController;
import by.school.diary.dto.ResponseUserDto;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserModelAssembler implements RepresentationModelAssembler<ResponseUserDto, EntityModel<ResponseUserDto>> {
    @Autowired
    CustomModelMapper modelMapper;

    @Override
    public EntityModel<ResponseUserDto> toModel(ResponseUserDto user) {

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getById(user.getId())).withRel("user"),
                linkTo(methodOn(UserController.class).getAll()).withRel("users"),
                linkTo(methodOn(UserController.class).deleteById(user.getId())).withRel("delete"),
                linkTo(methodOn(UserController.class).save(modelMapper.toDto(user))).withRel("save"),
                linkTo(methodOn(UserController.class).update(user.getId(), modelMapper.toDto(user))).withRel("update"));
    }

    @Override
    public CollectionModel<EntityModel<ResponseUserDto>> toCollectionModel(Iterable<? extends ResponseUserDto> users) {
        List<ResponseUserDto> userDtos = StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());
        List<EntityModel<ResponseUserDto>> usersModel = userDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usersModel, linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }
}
