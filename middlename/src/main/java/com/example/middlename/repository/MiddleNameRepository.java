package com.example.middlename.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.example.middlename.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;

@Repository
@RequiredArgsConstructor
public class MiddleNameRepository {

    private final MongoTemplate mongoTemplate;

    public String getById(String id) {
        return mongoTemplate.findById(id, StudentDto.class).getMiddlename();
    }

    public void update(StudentDto student) {
        Query query = new Query(Criteria.where("_id").is(student.getId()));
        Update update = new Update().set("middlename", student.getMiddlename());
        mongoTemplate.updateFirst(query, update, StudentDto.class);
    }

}