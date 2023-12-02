package com.example.middlename.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("students")
public class StudentDto {
    @Id
    private String id;
    private String firstname;
    private String middlename;
    private String lastname;
}