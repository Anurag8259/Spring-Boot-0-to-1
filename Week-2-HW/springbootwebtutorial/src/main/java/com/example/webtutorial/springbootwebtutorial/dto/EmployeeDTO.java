package com.example.webtutorial.springbootwebtutorial.dto;

import com.example.webtutorial.springbootwebtutorial.annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Getter //lombok
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {


    private Long id;


    @NotBlank(message = "Required field in employee : name") // @NotNull , @NotEmpty , @NotBlank
    @Size(min = 3 , max = 30,message = "Size requirement not satisfied")
    private String name;

    @NotBlank
    @Email
    private String email;

    @Max(value = 80,message = "Max age is 80")
    @Min(value = 16,message = "Min age is 16")
    @Positive
    private Integer age;

//    @Pattern(regexp = "^(ADMIN|USER$)" , message = "Unsupported Role") Custom annotation
    @EmployeeRoleValidation //Custom Annotation
    @NotNull
    private String role; //ADMIN or USER

    @PastOrPresent(message = "Date of joining should not be in the future")
    private LocalDate dateOfJoining;


    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

    @PositiveOrZero(message = "Salary should be positive")
    @Digits(integer = 8,fraction = 2,message = "Salary should be rounded off to 2 decimal places")
    @DecimalMin(value="100.99")
    @DecimalMax(value="10000000.99")
    private Integer salary;


}
