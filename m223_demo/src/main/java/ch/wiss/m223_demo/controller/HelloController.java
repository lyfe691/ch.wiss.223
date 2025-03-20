package ch.wiss.m223_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Hello World!");
    }
}
