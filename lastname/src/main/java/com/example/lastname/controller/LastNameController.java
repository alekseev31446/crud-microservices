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
import org.springframework.web.bind.annotation.RestController;
import com.example.lastname.dto.StudentDto;
import com.example.lastname.service.LastNameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static com.example.lastname.transformer.StudentDtoTransformer.toStudentDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/lastname")
@Slf4j
public class LastNameController {

    private final LastNameService lastNameService;

    @GetMapping("/find/{id}")
    public StudentDto getById(@PathVariable String id) {
        try {
            log.info("Executing getById method with id: {}", id);
            return lastNameService.getById(id);
        }catch (Exception e) {
            log.error("Error in getById method", e);
            return null;
        }
    }
    
    @GetMapping("/find/all")
    public List<StudentDto> getAll() {
        try {
            log.info("Executing getAll method");
            return lastNameService.getAll();
        }catch (Exception e) {
            log.error("Error in getAll method", e);
            return null;
        }
    }
    
    @PostMapping("/add")
    public StudentDto create(@RequestBody StudentDto student) {
        try {
            log.info("Executing create method");
            return lastNameService.create(student);
        }catch (Exception e) {
            log.error("Error in create method", e);
            return null;
        }
    }
    
    @PutMapping("/update/{id}")
    public StudentDto update(@PathVariable String id, @RequestBody StudentDto student) {
       try {
           log.info("Executing update method with id: {}", id);
           return lastNameService.update(toStudentDto(id, student));
       }catch (Exception e) {
           log.error("Error in update method", e);
           return null;
       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            log.info("Executing delete method with id: {}", id);
            lastNameService.delete(id);

            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        }catch (Exception e) {
            log.error("Error in delete method", e);
            return null;
        }
    }

}
