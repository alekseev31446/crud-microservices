package com.example.middlename.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.middlename.dto.StudentDto;
import com.example.middlename.service.MiddleNameService;
import static com.example.middlename.transformer.StudentDtoTransformer.toStudentDto;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/middlename")
public class MiddleNameController {

    private final MiddleNameService middleNameService;

    @GetMapping("/find/{id}")
    public StudentDto getById(@PathVariable String id) {
        return middleNameService.getById(id);
    }
    
    @GetMapping("/find/all")
    public List<StudentDto> getAll() {
        return middleNameService.getAll();
    }
    
    @PostMapping("/add")
    public StudentDto create(@RequestBody StudentDto student) {
        return middleNameService.create(student);
    }
    
    @PutMapping("/update/{id}")
    public StudentDto update(@PathVariable String id, @RequestBody StudentDto student) {
        return middleNameService.update(toStudentDto(id, student));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        middleNameService.delete(id);
    }

}
