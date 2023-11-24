package com.example.middlename.transformer;

import com.example.middlename.dto.MiddleNameDto;

public class MiddleNameDtoTransformer {
    
    public static MiddleNameDto toMiddleNameDto(String id, String middleName) {
        return MiddleNameDto.builder()
                .id(id)
                .middleName(middleName)
                .build();
    }
}