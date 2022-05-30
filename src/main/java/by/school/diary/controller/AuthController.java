package by.school.diary.controller;

import by.school.diary.dto.response.JWTTokenResponseDto;
import by.school.diary.dto.request.LoginRequestDto;
import by.school.diary.dto.request.SignUpRequestDto;
import by.school.diary.exception.ValidationCustomException;
import by.school.diary.security.JWTTokenProvider;
import by.school.diary.security.SecurityConstants;
import by.school.diary.service.UserService;
import by.school.diary.validations.ResponseErrorValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@RestController

@RequestMapping(path = "/v1/auth", produces = "application/json")
@PreAuthorize("permitAll()")
@Tag(name = "Authentication Controller", description = "This REST controller provides secure services in the Diary application")
public class AuthController {
    @Autowired
    private ResponseErrorValidator responseErrorValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @PostMapping("/sign-in")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides authentication of User application and JWT Token")
    public EntityModel<Object> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest, BindingResult bindingResult) {
        Map<String, String> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors))
            throw new ValidationCustomException(errors);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return EntityModel.of(new JWTTokenResponseDto(true, jwt),
                linkTo(methodOn(AuthController.class).authenticateUser(loginRequest,null)).withSelfRel(),
                linkTo(methodOn(AuthController.class).registerUser(null,null)).withRel("sign-up"));
    }


    @PostMapping("/sign-up")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Provides registration of user in application")
    public EntityModel<Map<String,Object>> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequest, BindingResult bindingResult) {
        Map<String, String>  errors = responseErrorValidator.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors))
            throw new ValidationCustomException(errors);
        userService.registerUser(signUpRequest);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("timestamp",now().toString());
        return EntityModel.of(result,
                linkTo(methodOn(AuthController.class).registerUser(null,null)).withSelfRel(),
                linkTo(methodOn(AuthController.class).authenticateUser(null,null)).withRel("sign-in"));
    }
}
