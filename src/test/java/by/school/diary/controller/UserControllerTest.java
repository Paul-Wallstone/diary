package by.school.diary.controller;


import by.school.diary.dto.ResponseUserDto;
import by.school.diary.service.UserService;
import by.school.diary.utils.UserModelAssembler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;
    @Mock
    UserModelAssembler assembler;
    @Mock
    UserService userService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getUserByIdFunctionalTest() {
        ResponseUserDto dto = ResponseUserDto
                .builder()
                .id(1L)
                .firstName("John")
                .lastName("Socket")
                .userName("jsocket")
                .build();
        EntityModel<ResponseUserDto> model = EntityModel.of(dto,
                linkTo(methodOn(UserController.class).getById(dto.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("users"));
        when(userService.getById(1l)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(model);
        EntityModel<ResponseUserDto> actual = userController.getById(1L);
        assertEquals(actual, model);
    }

    @Test
    void getUserByIdIntegrationTest() throws Exception {
        Long id = 1l;
        mockMvc.perform(get("/users/{id}", id))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Socket"))
                .andExpect(jsonPath("$.userName").value("jsocket"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserByIdIntegrationInvalidTest() throws Exception {
        mockMvc.perform(get("/users/{id}", 100))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void saveUser() {
    }
}