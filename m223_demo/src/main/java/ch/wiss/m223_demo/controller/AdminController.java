package ch.wiss.m223_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public ResponseEntity<String> getAdminContent() {
        return ResponseEntity.ok("Admin content - requires ADMIN role");
    } 
}