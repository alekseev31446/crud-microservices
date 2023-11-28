package com.example.middlename.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.middlename.transformer.StudentDtoTransformer;
import com.example.middlename.dto.StudentDto;
import com.example.middlename.feign.LastNameFeignClient;
import com.example.middlename.repository.MiddleNameRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MiddleNameService {

    private final MiddleNameRepository middleNameRepository;
    
    @Autowired
    private LastNameFeignClient lastNameFeignClient;

    public StudentDto getById(String id) {
        StudentDto student = lastNameFeignClient.getById(id);
        student.setMiddlename(middleNameRepository.getById(id));
        return student;
    }
    
    public List<StudentDto> getAll() {
        List<StudentDto> studentList = middleNameRepository.getAll();
        studentList.forEach(student -> {
            student.setLastname(lastNameFeignClient.getById(student.getId()).getLastname());
        });
        
        return studentList;
    }
    
    public StudentDto create(StudentDto student) {
        StudentDto createdStudent = middleNameRepository.create(StudentDtoTransformer.toStudentDto(null, null, student.getMiddlename(), null));
        createdStudent.setLastname(lastNameFeignClient.update(createdStudent.getId(), student).getLastname());
        return createdStudent;
    }
    
    public StudentDto update(String id, StudentDto student) {
        lastNameFeignClient.update(id, student);
        
        return middleNameRepository.update(id, student);
    }
    
    public void delete(String id) {
        middleNameRepository.delete(id);
    }
}