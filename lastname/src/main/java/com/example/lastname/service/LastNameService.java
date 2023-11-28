package com.example.lastname.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.lastname.dto.StudentDto;
import com.example.lastname.repository.LastNameRepository;
import com.example.lastname.transformer.StudentDtoTransformer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LastNameService {

    private final LastNameRepository lastNameRepository;

    public StudentDto getById(String id) {
        return StudentDtoTransformer.toStudentDto(id, null, null, lastNameRepository.getById(id));
    }
    
    public List<StudentDto> getAll() {
        return lastNameRepository.getAll();
    }
    
    public StudentDto create(StudentDto student) {
        return lastNameRepository.create(StudentDtoTransformer.toStudentDto(null, null, null, student.getLastname()));
    }

    public StudentDto update(String id, StudentDto student) {
        return lastNameRepository.update(id, student);
    }
    
    public void delete(String id) {
        lastNameRepository.delete(id);
    }
}