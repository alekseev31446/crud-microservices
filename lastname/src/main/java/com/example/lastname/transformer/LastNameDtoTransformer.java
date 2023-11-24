package com.example.lastname.transformer;

import com.example.lastname.dto.LastNameDto;

public class LastNameDtoTransformer {
    
    public static LastNameDto toLastNameDto(String id, String lastName) {
        return LastNameDto.builder()
                .id(id)
                .lastName(lastName)
                .build();
    }
}