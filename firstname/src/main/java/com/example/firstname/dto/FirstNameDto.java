package com.example.firstname.dto;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("firstnames")
public class FirstNameDto {

    @Id
    private String id;
    private String firstName;
}