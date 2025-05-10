package com.nefisa.feelingstracker.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FeelingRequest {

    @NotEmpty(message = "Title is mandatory")
    @Size(min = 3, max = 30, message = "Title must be at least 3 characters long")
    private String title;

    @NotEmpty(message = "Description is mandatory")
    @Size(min = 5, message = "Description must be at least 5 characters long")
    private String description;

    public FeelingRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
