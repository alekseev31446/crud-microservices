package com.example.lastname.transformer;

import com.example.lastname.dto.StudentDto;

public class StudentDtoTransformer {
    
    public static StudentDto toStudentDto(String id, String firstname, String middlename, String lastname) {
        return StudentDto.builder()
                .id(id)
                .firstname(firstname)
                .middlename(middlename)
                .lastname(lastname)
                .build();
    }

}