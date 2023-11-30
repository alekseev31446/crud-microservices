package com.example.lastname.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.lastname.dto.StudentDto;
import com.example.lastname.service.LastNameService;
import lombok.RequiredArgsConstructor;
import static com.example.lastname.transformer.StudentDtoTransformer.toStudentDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/lastname")
public class LastNameController {

    private final LastNameService lastNameService;

    @GetMapping("/find/{id}")
    public StudentDto getById(@PathVariable String id) {
        return lastNameService.getById(id);
    }
    
    @GetMapping("/find/all")
    public List<StudentDto> getAll() {
        return lastNameService.getAll();
    }
    
    @PostMapping("/add")
    public StudentDto create(@RequestBody StudentDto student) {
        return lastNameService.create(student);
    }
    
    @PutMapping("/update/{id}")
    public StudentDto update(@PathVariable String id, @RequestBody StudentDto student) {
       return lastNameService.update(toStudentDto(id, student));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        lastNameService.delete(id);

        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
    }

}
