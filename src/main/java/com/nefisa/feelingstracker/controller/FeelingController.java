package com.nefisa.feelingstracker.controller;

import com.nefisa.feelingstracker.request.FeelingRequest;
import com.nefisa.feelingstracker.response.FeelingResponse;
import com.nefisa.feelingstracker.service.FeelingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/feelings")
@Tag(name = "Feeling REST API Endpoints", description = "Operations for managing user feelings")
public class FeelingController {

    private final FeelingServiceImpl feelingService;

    @Autowired
    public FeelingController(FeelingServiceImpl feelingService) {
        this.feelingService = feelingService;
    }

    @Operation(summary = "Create a feeling for user", description = "Add new feeling for registered user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FeelingResponse createFeeling(@Valid @RequestBody FeelingRequest request) throws AccessDeniedException {
        return feelingService.createFeeling(request);
    }

    @Operation(summary = "Get all feelings", description = "Retrieve all feelings for registered user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<FeelingResponse> getFeelings() throws AccessDeniedException {
        return feelingService.getAllFeelings();
    }



}
