package com.nefisa.feelingstracker.controller;

import com.nefisa.feelingstracker.response.UserResponse;
import com.nefisa.feelingstracker.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin REST API Endpoints", description = "Operations related to admin")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Get all users", description = "Retrieve all users from database")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserResponse> getAllUsers(){
        return adminService.getAllUsers();
    }

    @Operation(summary = "Promote user to admin", description = "Promote user to admin role")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public UserResponse promoteToAdmin(@PathVariable @Min(1) long id){
        return adminService.promoteToAdmin(id);
    }


    @Operation(summary = "Delete an user", description = "Delete non admin user from database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteNonAdminUser(@PathVariable @Min(1) long id){
        adminService.deleteNonAdminUser(id);
    }
}
