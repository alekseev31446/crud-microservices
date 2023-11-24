package com.example.firstname.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {
    private String id;
    private String firstname;
    private String middlename;
    private String lastname;
}