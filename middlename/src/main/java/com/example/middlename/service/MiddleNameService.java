package com.example.middlename.service;

import org.springframework.stereotype.Service;

import com.example.middlename.dto.MiddleNameDto;
import com.example.middlename.repository.MiddleNameRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MiddleNameService {

    private final MiddleNameRepository middleNameRepository;

    public MiddleNameDto getById(String id) {
        return middleNameRepository.getById(id);
    }
    
    public void create(MiddleNameDto lastname) {
        middleNameRepository.create(lastname);
    }
    
    public void update(MiddleNameDto lastname) {
        middleNameRepository.update(lastname);
    }
    
    public void delete(String id) {
        middleNameRepository.delete(id);
    }
}