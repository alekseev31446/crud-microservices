package com.example.middlename.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        List<StudentDto> studentList = lastNameFeignClient.getAll();
        studentList.forEach(student -> {
            student.setMiddlename(middleNameRepository.getById(student.getId()));
        });
        
        return studentList;
    }
    
    public void update(StudentDto student) {
        middleNameRepository.update(student);
        lastNameFeignClient.update(student);
    }
    
    public void delete(String id) {
        lastNameFeignClient.delete(id);
    }
}