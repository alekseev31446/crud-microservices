package com.example.firstname.transformer;

import com.example.firstname.dto.StudentDto;

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