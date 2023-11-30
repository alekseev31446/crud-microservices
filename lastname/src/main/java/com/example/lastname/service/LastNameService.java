package com.example.lastname.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.lastname.dto.StudentDto;
import com.example.lastname.repository.LastNameRepository;
import lombok.RequiredArgsConstructor;
import static com.example.lastname.transformer.StudentDtoTransformer.toStudentDto;

@RequiredArgsConstructor
@Service
public class LastNameService {

    private final LastNameRepository lastNameRepository;

    public StudentDto getById(String id) {
        return toStudentDto(id, lastNameRepository.getById(id).getLastname());
    }
    
    public List<StudentDto> getAll() {
        return lastNameRepository.getAll();
    }
    
    public StudentDto create(StudentDto student) {
        return lastNameRepository.create(toStudentDto(null, student.getLastname()));
    }

    public StudentDto update(StudentDto student) {
        return lastNameRepository.update(student);
    }
    
    public void delete(String id) {
        lastNameRepository.delete(id);
    }
}