package com.example.middlename.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document("middlenames")
public class MiddleNameDto {

    @Id
    private String id;
    private String middleName;
    
}