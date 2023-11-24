package com.example.lastname.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lastname.dto.LastNameDto;
import com.example.lastname.service.LastNameService;
import com.example.lastname.transformer.LastNameDtoTransformer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lastname")
public class LastNameController {

    @Autowired
    private LastNameService lastNameService;

    @PostMapping("/add/{id}")
    public void create(@PathVariable String id, @RequestParam String lastname) {
        lastNameService.create(LastNameDtoTransformer.toLastNameDto(id, lastname));
    }

    @GetMapping("/find/{id}")
    public String getById(@PathVariable String id) {
        return lastNameService.getById(id).getLastName();
    }
    
    @PutMapping("/update/{id}")
    public void update(@PathVariable String id, @RequestParam String lastname) {
        lastNameService.update(LastNameDtoTransformer.toLastNameDto(id, lastname));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        lastNameService.delete(id);
    }

}
