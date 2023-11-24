package com.example.firstname.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstname.dto.FirstNameDto;
import com.example.firstname.dto.StudentDto;
import com.example.firstname.feign.LastNameFeignClient;
import com.example.firstname.feign.MiddleNameFeignClient;
import com.example.firstname.service.FirstNameService;
import com.example.firstname.transformer.FirstNameDtoTransformer;
import com.example.firstname.transformer.StudentDtoTransformer;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/firstname")
public class FirstNameController {

    @Autowired
    private FirstNameService firstNameService;
    
    @Autowired
    private MiddleNameFeignClient middleNameFeignClient;

    @Autowired
    private LastNameFeignClient lastNameFeignClient;

    @PostMapping("/add")
    public StudentDto create(@RequestBody StudentDto studentDto) {
        FirstNameDto createdFirstName = firstNameService.create(FirstNameDtoTransformer.toFirstNameDto(studentDto.getFirstname()));
        
        middleNameFeignClient.addMiddleName(createdFirstName.getId(), studentDto.getMiddlename());
        lastNameFeignClient.addLastName(createdFirstName.getId(), studentDto.getLastname());
        
        
        studentDto.setId(createdFirstName.getId());
        
        return studentDto;
    }
    
    @GetMapping("/show/all")
    public List<StudentDto> getAll() {
        List<FirstNameDto> allFirstNames = firstNameService.getAll();
        
        List<StudentDto> allStudents = allFirstNames.stream()
                .filter(Objects::nonNull)
                .map(this::buildStudentDto)
                .collect(Collectors.toList());

        return allStudents;
    }
    
    @GetMapping("/find/{id}")
    public StudentDto getById(@PathVariable String id) {
        FirstNameDto studentFirstName = firstNameService.getById(id);
        
        StudentDto student = buildStudentDto(studentFirstName);

        return student;
    }
    
    @PutMapping("/update/{id}")
    public StudentDto update(@PathVariable String id, @RequestBody StudentDto updatedStudentDto) {
        
        FirstNameDto updatedFirstName = firstNameService.update(FirstNameDtoTransformer.toFirstNameDto(id, updatedStudentDto.getFirstname()));

        middleNameFeignClient.updateMiddleName(id, updatedStudentDto.getMiddlename());

        lastNameFeignClient.updateLastName(id, updatedStudentDto.getLastname());

        StudentDto updatedStudent = buildStudentDto(updatedFirstName);
        return updatedStudent;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        firstNameService.delete(id);

        middleNameFeignClient.deleteMiddleName(id);
        lastNameFeignClient.deleteLastName(id);

        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

    
    private StudentDto buildStudentDto(FirstNameDto firstName) {
        if(firstName == null) {
            return null;
        }
        String lastName = lastNameFeignClient.getLastName(firstName.getId());
        String middleName = middleNameFeignClient.getMiddleName(firstName.getId());

        return StudentDtoTransformer.toStudentDto(firstName.getId(), firstName.getFirstName(), middleName, lastName);
    }
    
}
