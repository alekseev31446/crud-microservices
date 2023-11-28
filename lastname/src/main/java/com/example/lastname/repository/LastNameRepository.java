package com.example.lastname.repository;

import com.example.lastname.dto.StudentDto;
import com.example.lastname.transformer.StudentDtoTransformer;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.FindAndModifyOptions;

@RequiredArgsConstructor
@Repository
public class LastNameRepository {

    private final MongoTemplate mongoTemplate;

    public String getById(String id) {
        return mongoTemplate.findById(id, StudentDto.class).getLastname();
    }
    
    public List<StudentDto> getAll() {
        Query query = new Query(Criteria.where("_id").exists(true));
        return mongoTemplate.find(query, StudentDto.class)
                            .stream()
                            .map(student -> StudentDtoTransformer.toStudentDto(student.getId(), null, null, student.getLastname()))
                            .collect(Collectors.toList());
    }
    
    public StudentDto create(StudentDto student) {
        return mongoTemplate.save(student);
    }

    public StudentDto update(String id, StudentDto student) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("lastname", student.getLastname());

        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);
        
        String lastname = mongoTemplate.findAndModify(query, update, options, StudentDto.class).getLastname();

        return StudentDtoTransformer.toStudentDto(id, null, null, lastname);
    }


    public void delete(String id) {
        mongoTemplate.remove(mongoTemplate.findById(id, StudentDto.class));
    }
}