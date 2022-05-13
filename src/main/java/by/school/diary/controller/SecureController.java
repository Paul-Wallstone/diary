package by.school.diary.controller;

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

    @GetMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides status of logging application")
    public ResponseEntity<String> getString() {
                return new ResponseEntity<>("logged in", HttpStatus.OK);
    }


}
