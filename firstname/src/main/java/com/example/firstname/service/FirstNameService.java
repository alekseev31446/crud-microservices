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
        List<StudentDto> studentList = middleNameFeignClient.getAll();
        studentList.forEach(student -> {
            student.setFirstname(firstNameRepository.getById(student.getId()));
        });
        return studentList;
    }
    
    public void create(StudentDto student) {
        StudentDto createdStudent = firstNameRepository.create(StudentDtoTransformer.toStudentDto(null, student.getFirstname(), null, null));
        student.setId(createdStudent.getId());
        middleNameFeignClient.update(student);
    }
    
    public StudentDto update(StudentDto student) {
        firstNameRepository.update(student);
        middleNameFeignClient.update(student);
        return student;
    }
    
    public void delete(String id) {
        middleNameFeignClient.delete(id);
    }
}