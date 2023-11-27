package com.example.middlename.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.middlename.dto.StudentDto;

@FeignClient(name = "last-name-service")
public interface LastNameFeignClient {

    @PostMapping("/lastname/add/{id}")
    void addLastName(@RequestParam String id, @RequestParam String lastname);
    
    @GetMapping("/lastname/find/{id}")
    StudentDto getById(@RequestParam String id);
    
    @GetMapping("/lastname/find/all")
    List<StudentDto> getAll();
    
    @PutMapping("/lastname/update/")
    void update(@RequestBody StudentDto student);
    
    @DeleteMapping("/lastname/delete/{id}")
    void delete(@PathVariable String id);
}
