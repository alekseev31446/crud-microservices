package com.example.firstname.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "middle-name-service")
public interface MiddleNameFeignClient {

    @PostMapping("/middlename/add/{id}")
    void addMiddleName(@PathVariable String id, @RequestParam String middlename);
    
    @GetMapping("/middlename/find/{id}")
    String getMiddleName(@RequestParam String id);
    
    @PutMapping("/middlename/update/{id}")
    void updateMiddleName(@PathVariable String id, @RequestParam String middlename);
    
    @DeleteMapping("/middlename/delete/{id}")
    void deleteMiddleName(@PathVariable String id);
}
