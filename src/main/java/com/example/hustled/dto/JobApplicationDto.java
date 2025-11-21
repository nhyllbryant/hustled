package com.example.hustled.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class JobApplicationDto {
    @NotBlank
    @Size(min = 50, max = 2000)
    private String coverLetter;
}
