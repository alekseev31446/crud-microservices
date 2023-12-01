package com.example.firstname.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.firstname.dto.StudentDto;

@FeignClient(url = "http://middlename-service:27019", name = "middlename-service")
public interface MiddleNameFeignClient {

    @GetMapping("/middlename/find/{id}")
    StudentDto getById(@RequestParam String id);
    
    @PutMapping("/middlename/update/{id}")
    StudentDto update(@PathVariable String id, @RequestBody StudentDto student);
}
