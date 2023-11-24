package com.example.middlename.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.example.middlename.dto.MiddleNameDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MiddleNameRepository {

    private final MongoTemplate mongoTemplate;

    public MiddleNameDto create(MiddleNameDto middleName) {
        return mongoTemplate.save(middleName);
    }

    public MiddleNameDto getById(String id) {
        return mongoTemplate.findById(id, MiddleNameDto.class);
    }

    public MiddleNameDto update(MiddleNameDto updatedMiddleName) {
        MiddleNameDto existingMiddleName = mongoTemplate.findById(updatedMiddleName.getId(), MiddleNameDto.class);
        existingMiddleName.setMiddleName(updatedMiddleName.getMiddleName());
        return  mongoTemplate.save(existingMiddleName);
    }

    public void delete(String id) {
        mongoTemplate.remove(mongoTemplate.findById(id, MiddleNameDto.class));
    }
}