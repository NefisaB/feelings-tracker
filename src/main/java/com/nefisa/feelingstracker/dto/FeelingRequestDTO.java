package com.nefisa.feelingstracker.dto;

import jakarta.validation.constraints.Size;

public class FeelingRequestDTO {

    @Size(min = 3, max = 300, message = "Description has to contain between 3 and 300 characters.")
    private String description;

    public FeelingRequestDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
