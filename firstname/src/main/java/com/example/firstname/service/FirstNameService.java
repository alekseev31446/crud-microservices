package com.example.firstname.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.firstname.dto.StudentDto;
import com.example.firstname.feign.MiddleNameFeignClient;
import com.example.firstname.repository.FirstNameRepository;
import com.example.firstname.transformer.StudentDtoTransformer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FirstNameService {

    @Autowired
    private final FirstNameRepository firstNameRepository;
    
    @Autowired
    private MiddleNameFeignClient middleNameFeignClient;

    public StudentDto getById(String id) {
        StudentDto student = middleNameFeignClient.getById(id);
        student.setFirstname(firstNameRepository.getById(id));
        return student;
    }
    
    public List<StudentDto> getAll() {
        List<StudentDto> studentList = firstNameRepository.getAll();
        studentList.forEach(student -> {
            StudentDto middlenameStudent = middleNameFeignClient.getById(student.getId());
            student.setMiddlename(middlenameStudent.getMiddlename());
            student.setLastname(middlenameStudent.getLastname());
        });
        return studentList;
    }
    
    public void create(StudentDto student) {
        StudentDto createdStudent = firstNameRepository.create(StudentDtoTransformer.toStudentDto(null, student.getFirstname(), null, null));
        student.setId(createdStudent.getId());
        middleNameFeignClient.update(createdStudent.getId(), student);
    }
    
    public StudentDto update(String id, StudentDto student) {
        firstNameRepository.update(id, student);
        middleNameFeignClient.update(id, student);
        return student;
    }
    
    public void delete(String id) {
        firstNameRepository.delete(id);
    }
}