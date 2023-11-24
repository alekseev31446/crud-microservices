package com.example.firstname.repository;

import com.example.firstname.dto.FirstNameDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FirstNameRepository {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public FirstNameDto create(FirstNameDto firstName) {
        return mongoTemplate.save(firstName);
    }

    public FirstNameDto getById(String id) {
        return mongoTemplate.findById(id, FirstNameDto.class);
    }

    public List<FirstNameDto> getAll() {
        return mongoTemplate.findAll(FirstNameDto.class);
    }

    public FirstNameDto update(FirstNameDto updatedFirstName) {
        FirstNameDto existingFirstName = mongoTemplate.findById(updatedFirstName.getId(), FirstNameDto.class);
        existingFirstName.setFirstName(updatedFirstName.getFirstName());
        return  mongoTemplate.save(existingFirstName);
    }

    public void delete(String id) {
        mongoTemplate.remove(mongoTemplate.findById(id, FirstNameDto.class));
    }
}
