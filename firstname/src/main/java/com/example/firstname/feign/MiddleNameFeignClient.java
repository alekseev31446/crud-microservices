package com.example.firstname.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.firstname.dto.StudentDto;

@FeignClient(name = "middle-name-service")
public interface MiddleNameFeignClient {

    @PostMapping("/middlename/add/{id}")
    void addMiddleName(@PathVariable String id, @RequestParam String middlename);
    
    @GetMapping("/middlename/find/{id}")
    StudentDto getById(@RequestParam String id);
    
    @GetMapping("/middlename/find/all")
    List<StudentDto> getAll();
    
    @PutMapping("/middlename/update/")
    void update(@RequestBody StudentDto student);
    
    @DeleteMapping("/middlename/delete/{id}")
    void delete(@PathVariable String id);
}
