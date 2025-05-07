package com.nefisa.feelingstracker.controller;

import com.nefisa.feelingstracker.response.UserResponse;
import com.nefisa.feelingstracker.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User REST API Endpoints", description = "Operations related to current user info")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public UserResponse getCurrentUser() throws Exception {
        return userService.getUserInfo();
    }
}
