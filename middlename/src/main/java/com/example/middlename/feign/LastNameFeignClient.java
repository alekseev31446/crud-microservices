package com.example.middlename.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.middlename.dto.StudentDto;

@FeignClient(url = "http://lastname-service:27020", name = "lastname-service")
public interface LastNameFeignClient { 
    @GetMapping("/lastname/find/{id}")
    StudentDto getById(@PathVariable String id);
    
    @PutMapping("/lastname/update/{id}")
    StudentDto update(@PathVariable String id, @RequestBody StudentDto student);
}
