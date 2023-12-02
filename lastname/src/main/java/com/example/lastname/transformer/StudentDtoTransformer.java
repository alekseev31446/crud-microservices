package com.example.lastname.transformer;

import com.example.lastname.dto.StudentDto;

public class StudentDtoTransformer {
    
    public static StudentDto toStudentDto(String id, String lastname) {
        return StudentDto.builder()
                .id(id)
                .firstname(null)
                .middlename(null)
                .lastname(lastname)
                .build();
    }
    
    public static StudentDto toStudentDto(String id, StudentDto student) {
        return toStudentDto(id, student.getLastname());
    }

}