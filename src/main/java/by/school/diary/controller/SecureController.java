package by.school.diary.controller;

import by.school.diary.domain.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(produces = "application/json")
@Tag(name = "Secure Controller", description = "This REST controller provides secure services in the Diary application")
public class SecureController {

    @GetMapping("/logged-in-status")
    @PreAuthorize("hasAnyRole('" + Role.Fields.ROLE_ADMIN + "', '" + Role.Fields.ROLE_DIRECTOR + "', '" + Role.Fields.ROLE_STUDENT + "', '" + Role.Fields.ROLE_TEACHER + "')")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides status of logging application")
    public ResponseEntity<String> getLoggedInStatus() {
        return new ResponseEntity<>("You logged in successfully", HttpStatus.OK);
    }

    @GetMapping("/logged-out-status")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides status of logging application")
    public ResponseEntity<String> getLoggedOutStatus() {
        return new ResponseEntity<>("You logged out successfully", HttpStatus.OK);
    }
}
