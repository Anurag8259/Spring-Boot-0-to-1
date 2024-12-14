package com.example.webtutorial.springbootwebtutorial.dto;

import com.example.webtutorial.springbootwebtutorial.annotation.PasswordValidation;
import com.example.webtutorial.springbootwebtutorial.annotation.PrimeNumberValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {


    private Long id;

    @NotBlank
    private String title;

    @AssertTrue
    private Boolean isActive;

    @PastOrPresent
    private LocalDate createdAt;

    @PrimeNumberValidation
    private Integer no;


    @PasswordValidation
    private String pw;
}
