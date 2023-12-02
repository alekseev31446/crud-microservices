package com.example.middlename.transformer;

import com.example.middlename.dto.StudentDto;

public class StudentDtoTransformer {
    
    public static StudentDto toStudentDto(String id, String middlename) {
        return StudentDto.builder()
                .id(id)
                .firstname(null)
                .middlename(middlename)
                .lastname(null)
                .build();
    }
    
    public static StudentDto toStudentDto(String id, String middlename, String lastname) {
        return StudentDto.builder()
                .id(id)
                .firstname(null)
                .middlename(middlename)
                .lastname(lastname)
                .build();
    }

    public static StudentDto toStudentDto(String id, StudentDto student) {
        return toStudentDto(id, student.getMiddlename(), student.getLastname());
    }
}