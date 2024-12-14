package com.example.webtutorial.springbootwebtutorial.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
//    @Pattern(regexp = "hh-mm-ss dd-mm-yyyy")
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private T data;
    private ApiError error;



    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }


    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }


    public ApiResponse(T data) {
        this();
        this.data = data;
    }
}
