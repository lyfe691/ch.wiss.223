package ch.wiss.m223_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    
    @GetMapping
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Public content");
    } 
    
}
