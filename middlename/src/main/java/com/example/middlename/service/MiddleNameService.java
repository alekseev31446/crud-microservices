package com.example.middlename.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.middlename.dto.StudentDto;
import com.example.middlename.feign.LastNameFeignClient;
import com.example.middlename.repository.MiddleNameRepository;
import lombok.RequiredArgsConstructor;
import static com.example.middlename.transformer.StudentDtoTransformer.toStudentDto;


@RequiredArgsConstructor
@Service
public class MiddleNameService {

    private final MiddleNameRepository middleNameRepository;
    
    private final LastNameFeignClient lastNameFeignClient;

    public StudentDto getById(String id) {
        StudentDto student = lastNameFeignClient.getById(id);
        student.setMiddlename(middleNameRepository.getById(id).getMiddlename());
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
        StudentDto createdStudent = middleNameRepository.create(toStudentDto(null, student.getMiddlename()));
        createdStudent.setLastname(lastNameFeignClient.update(createdStudent.getId(), student).getLastname());
        return createdStudent;
    }
    
    public StudentDto update(StudentDto student) {
        lastNameFeignClient.update(student.getId(), student);
        
        return middleNameRepository.update(student);
    }
    
    public void delete(String id) {
        middleNameRepository.delete(id);
    }
}