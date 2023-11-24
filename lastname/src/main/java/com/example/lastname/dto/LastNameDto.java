package com.example.lastname.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document("lastnames")
public class LastNameDto {

    @Id
    private String id;
    private String lastName;

}