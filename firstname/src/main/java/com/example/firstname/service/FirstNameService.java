package com.example.firstname.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstname.dto.FirstNameDto;
import com.example.firstname.repository.FirstNameRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FirstNameService {

    @Autowired
    private final FirstNameRepository firstNameRepository;

    public List<FirstNameDto> getAll() {
        return firstNameRepository.getAll();
    }
    
    public FirstNameDto getById(String id) {
        return firstNameRepository.getById(id);
    }
    
    public FirstNameDto create(FirstNameDto student) {
        return firstNameRepository.create(student);
    }
    
    public FirstNameDto update(FirstNameDto student) {
        return firstNameRepository.update(student);
    }
    
    public void delete(String id) {
        firstNameRepository.delete(id);
    }
}