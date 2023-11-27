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
import com.example.firstname.dto.StudentDto;
import com.example.firstname.feign.MiddleNameFeignClient;
import com.example.firstname.service.FirstNameService;
import com.example.firstname.transformer.StudentDtoTransformer;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/firstname")
public class FirstNameController {
    @Autowired
    private FirstNameService firstNameService;
    
    @Autowired
    private MiddleNameFeignClient middleNameFeignClient;

    @PostMapping("/add")
    public StudentDto create(@RequestBody StudentDto studentDto) {
        firstNameService.create(studentDto);
        
        return studentDto;
    }
    
    @GetMapping("/find/{id}")
    public StudentDto getById(@PathVariable String id) {

        return firstNameService.getById(id);
    }
    
    @GetMapping("/find/all")
    public List<StudentDto> getAll() {

        return firstNameService.getAll();
    }
    
    @PutMapping("/update/{id}")
    public StudentDto update(@PathVariable String id, @RequestBody StudentDto student) {
        return firstNameService.update(StudentDtoTransformer.toStudentDto(id, student));
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        firstNameService.delete(id);

        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }
}
