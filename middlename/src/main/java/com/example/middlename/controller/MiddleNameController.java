package com.example.middlename.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.middlename.dto.StudentDto;
import com.example.middlename.service.MiddleNameService;

@RestController
@RequestMapping("/middlename")
public class MiddleNameController {

    @Autowired
    private MiddleNameService middleNameService;

    @GetMapping("/find/{id}")
    public StudentDto getById(@PathVariable String id) {
        return middleNameService.getById(id);
    }
    
    @GetMapping("/find/all")
    public List<StudentDto> getAll() {
        return middleNameService.getAll();
    }
    
    @PutMapping("/update/")
    public void update(@RequestBody StudentDto student) {
        middleNameService.update(student);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        middleNameService.delete(id);
    }

}
