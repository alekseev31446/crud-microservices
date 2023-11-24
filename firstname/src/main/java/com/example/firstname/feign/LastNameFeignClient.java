package com.example.firstname.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "last-name-service")
public interface LastNameFeignClient {

    @PostMapping("/lastname/add/{id}")
    void addLastName(@RequestParam String id, @RequestParam String lastname);
    
    @GetMapping("/lastname/find/{id}")
    String getLastName(@RequestParam String id);
    
    @PutMapping("/lastname/update/{id}")
    void updateLastName(@PathVariable String id, @RequestParam String lastname);
    
    @DeleteMapping("/lastname/delete/{id}")
    void deleteLastName(@PathVariable String id);
}
