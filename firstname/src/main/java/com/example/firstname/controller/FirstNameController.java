package com.example.firstname.controller;

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
import com.example.firstname.service.FirstNameService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import static com.example.firstname.transformer.StudentDtoTransformer.toStudentDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/firstname")
public class FirstNameController {
    
    private final FirstNameService firstNameService;
    
    @PostMapping("/add")
    public StudentDto create(@RequestBody StudentDto studentDto) {
        
        return firstNameService.create(studentDto);
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
        return firstNameService.update(toStudentDto(id, student));
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        firstNameService.delete(id);

        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }
}
