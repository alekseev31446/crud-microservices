package com.example.firstname.repository;

import com.example.firstname.dto.StudentDto;
import static com.example.firstname.transformer.StudentDtoTransformer.toStudentDto;
import lombok.RequiredArgsConstructor;
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
    private final MongoTemplate mongoTemplate;

    public StudentDto create(StudentDto student) {
        return mongoTemplate.save(student);
    }

    public StudentDto getById(String id) {
        return mongoTemplate.findById(id, StudentDto.class);
    }
    
    public List<StudentDto> getAll() {
        Query query = new Query(Criteria.where("_id").exists(true));
        return mongoTemplate.find(query, StudentDto.class)
                            .stream()
                            .map(student -> toStudentDto(student.getId(), student.getFirstname()))
                            .collect(Collectors.toList());
    }
    
    public void update(StudentDto student) {
        Query query = new Query(Criteria.where("_id").is(student.getId()));
        Update update = new Update().set("firstname", student.getFirstname());

        mongoTemplate.updateFirst(query, update, StudentDto.class);
    }
    
    public void delete(String id) {
        mongoTemplate.remove(mongoTemplate.findById(id, StudentDto.class));
    }

}
