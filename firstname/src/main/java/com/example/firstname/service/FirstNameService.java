package com.example.firstname.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.firstname.dto.StudentDto;
import com.example.firstname.feign.MiddleNameFeignClient;
import com.example.firstname.repository.FirstNameRepository;
import static com.example.firstname.transformer.StudentDtoTransformer.toStudentDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FirstNameService {

    private final FirstNameRepository firstNameRepository;
    
    private final MiddleNameFeignClient middleNameFeignClient;

    public StudentDto getById(String id) {
        StudentDto student = middleNameFeignClient.getById(id);
        student.setFirstname(firstNameRepository.getById(id).getFirstname());
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
    
    public StudentDto create(StudentDto student) {
        StudentDto createdStudent = firstNameRepository.create(toStudentDto(null, student.getFirstname()));
        student.setId(createdStudent.getId());
        middleNameFeignClient.update(createdStudent.getId(), student);
        return student;
    }
    
    public StudentDto update(StudentDto student) {
        firstNameRepository.update(student);
        middleNameFeignClient.update(student.getId(), student);
        return student;
    }
    
    public void delete(String id) {
        firstNameRepository.delete(id);
    }
}