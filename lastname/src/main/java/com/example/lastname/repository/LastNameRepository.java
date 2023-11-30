package com.example.lastname.repository;

import com.example.lastname.dto.StudentDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import static com.example.lastname.transformer.StudentDtoTransformer.toStudentDto;


@RequiredArgsConstructor
@Repository
public class LastNameRepository {

    private final MongoTemplate mongoTemplate;

    public StudentDto getById(String id) {
        return mongoTemplate.findById(id, StudentDto.class);
    }
    
    public List<StudentDto> getAll() {
        Query query = new Query(Criteria.where("_id").exists(true));
        return mongoTemplate.find(query, StudentDto.class)
                            .stream()
                            .map(student -> toStudentDto(student.getId(), student.getLastname()))
                            .collect(Collectors.toList());
    }
    
    public StudentDto create(StudentDto student) {
        return mongoTemplate.save(student);
    }

    public StudentDto update(StudentDto student) {
        Query query = new Query(Criteria.where("_id").is(student.getId()));
        Update update = new Update().set("lastname", student.getLastname());

        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);
        
        String lastname = mongoTemplate.findAndModify(query, update, options, StudentDto.class).getLastname();

        return toStudentDto(student.getId(), lastname);
    }


    public void delete(String id) {
        mongoTemplate.remove(mongoTemplate.findById(id, StudentDto.class));
    }
}