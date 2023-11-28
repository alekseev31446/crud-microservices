package com.example.middlename.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.example.middlename.dto.StudentDto;
import com.example.middlename.transformer.StudentDtoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.FindAndModifyOptions;

@Repository
@RequiredArgsConstructor
public class MiddleNameRepository {

    private final MongoTemplate mongoTemplate;
    
    public StudentDto create(StudentDto student) {
        return mongoTemplate.save(student);
    }

    public String getById(String id) {
        return mongoTemplate.findById(id, StudentDto.class).getMiddlename();
    }
    
    public List<StudentDto> getAll() {
        Query query = new Query(Criteria.where("_id").exists(true));
        return mongoTemplate.find(query, StudentDto.class)
                            .stream()
                            .map(student -> StudentDtoTransformer.toStudentDto(student.getId(), null, student.getMiddlename(), null))
                            .collect(Collectors.toList());
    }

    public StudentDto update(String id, StudentDto student) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("middlename", student.getMiddlename());

        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);
        
        String middlename = mongoTemplate.findAndModify(query, update, options, StudentDto.class).getMiddlename();

        return StudentDtoTransformer.toStudentDto(id, null, middlename, null);
    }
    
    public void delete(String id) {
        mongoTemplate.remove(mongoTemplate.findById(id, StudentDto.class));
    }

}