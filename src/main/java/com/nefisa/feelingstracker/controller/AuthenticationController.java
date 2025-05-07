package com.nefisa.feelingstracker.controller;

import com.nefisa.feelingstracker.request.AuthenticationRequest;
import com.nefisa.feelingstracker.request.RegisterRequest;
import com.nefisa.feelingstracker.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication REST API Endpoints", description = "Operations related to register and login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register user", description = "Create new user in database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
    }

    @Operation(summary = "Login user", description = "Submit email and password to authenticate user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public void login(@Valid @RequestBody AuthenticationRequest request) throws Exception {
        authenticationService.login(request);
    }


}
