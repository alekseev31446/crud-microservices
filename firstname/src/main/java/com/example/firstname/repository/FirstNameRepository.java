package com.example.firstname.repository;

import com.example.firstname.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class FirstNameRepository {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public StudentDto create(StudentDto student) {
        return mongoTemplate.save(student);
    }

    public String getById(String id) {
        return mongoTemplate.findById(id, StudentDto.class).getFirstname();
    }
    
    public void update(StudentDto student) {
        Query query = new Query(Criteria.where("_id").is(student.getId()));
        Update update = new Update().set("firstname", student.getFirstname());

        mongoTemplate.updateFirst(query, update, StudentDto.class);
    }

}
