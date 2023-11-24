package com.example.lastname.repository;

import com.example.lastname.dto.LastNameDto;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LastNameRepository {

    private final MongoTemplate mongoTemplate;
    
    public LastNameDto create(LastNameDto lastName) {
        return mongoTemplate.save(lastName);
    }

    public LastNameDto getById(String id) {
        return mongoTemplate.findById(id, LastNameDto.class);
    }

    public LastNameDto update(LastNameDto updatedLastName) {
        LastNameDto existingLastName = mongoTemplate.findById(updatedLastName.getId(), LastNameDto.class);
        existingLastName.setLastName(updatedLastName.getLastName());
        return  mongoTemplate.save(existingLastName);
    }

    public void delete(String id) {
        mongoTemplate.remove(mongoTemplate.findById(id, LastNameDto.class));
    }
}