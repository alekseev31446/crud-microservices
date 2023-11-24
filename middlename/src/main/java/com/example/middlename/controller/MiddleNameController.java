package com.example.middlename.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.middlename.dto.MiddleNameDto;
import com.example.middlename.service.MiddleNameService;
import com.example.middlename.transformer.MiddleNameDtoTransformer;

@RestController
@RequestMapping("/middlename")
public class MiddleNameController {

    @Autowired
    private MiddleNameService middleNameService;

    @PostMapping("/add/{id}")
    public void create(@PathVariable String id, @RequestParam String middlename) {
        middleNameService.create(MiddleNameDtoTransformer.toMiddleNameDto(id, middlename));
    }

    @GetMapping("/find/{id}")
    public String getById(@PathVariable String id) {
        return middleNameService.getById(id).getMiddleName();
    }
    
    @PutMapping("/update/{id}")
    public void update(@PathVariable String id, @RequestParam String middlename) {
        middleNameService.update(MiddleNameDtoTransformer.toMiddleNameDto(id, middlename));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        middleNameService.delete(id);
    }

}
