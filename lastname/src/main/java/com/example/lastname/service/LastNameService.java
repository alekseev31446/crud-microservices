package com.example.lastname.service;

import org.springframework.stereotype.Service;

import com.example.lastname.dto.LastNameDto;
import com.example.lastname.repository.LastNameRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LastNameService {

    private final LastNameRepository lastNameRepository;

    public LastNameDto getById(String id) {
        return lastNameRepository.getById(id);
    }
    
    public void create(LastNameDto lastname) {
        lastNameRepository.create(lastname);
    }
    
    public void update(LastNameDto lastname) {
        lastNameRepository.update(lastname);
    }
    
    public void delete(String id) {
        lastNameRepository.delete(id);
    }
}