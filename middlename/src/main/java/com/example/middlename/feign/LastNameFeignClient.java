package com.example.middlename.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.middlename.dto.StudentDto;

@FeignClient(name = "last-name-service")
public interface LastNameFeignClient { 
    @GetMapping("/lastname/find/{id}")
    StudentDto getById(@PathVariable String id);
    
    @PutMapping("/lastname/update/{id}")
    StudentDto update(@PathVariable String id, @RequestBody StudentDto student);
}
