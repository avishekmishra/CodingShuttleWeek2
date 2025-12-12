package com.techviews.Week2Practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techviews.Week2Practice.annotations.PrimeInputValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Title of a department should not be empty")
    private String title;

    @AssertTrue(message = "Only active department can be accepted")
    @JsonProperty("isActive")
    private Boolean isActive;

    @PrimeInputValidation(message = "Number of employee in department should be a prime number")
    private Integer headCountInDepartment;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createdAt;

}
