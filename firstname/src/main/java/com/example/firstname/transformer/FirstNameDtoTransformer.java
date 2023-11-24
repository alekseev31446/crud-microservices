package com.example.firstname.transformer;

import com.example.firstname.dto.FirstNameDto;

public class FirstNameDtoTransformer {
    
    public static FirstNameDto toFirstNameDto(String id, String firstName) {
        return FirstNameDto.builder()
                .id(id)
                .firstName(firstName)
                .build();
    }
    
    public static FirstNameDto toFirstNameDto(String firstName) {
        return FirstNameDto.builder()
                .firstName(firstName)
                .build();
    }
}