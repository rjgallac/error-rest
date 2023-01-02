package com.example.demo.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestErrorController {

    @GetMapping("test")
    public ResponseEntity<String> getTest(){
        return ResponseEntity.ok("test");
    }

    @GetMapping("testerror")
    public ResponseEntity<String> getError(){
        if(true) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok("test");
    }
}
