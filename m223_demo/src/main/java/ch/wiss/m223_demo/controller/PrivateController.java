package ch.wiss.m223_demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223_demo.service.UserDetailsImpl;

@RestController
@RequestMapping("/private")
public class PrivateController {
    
    @GetMapping
    public ResponseEntity<List<String>> getGreeting() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        
        String user = userDetails.getUsername();
        
        return ResponseEntity.ok(List.of("Private Area", "Your Username: " + user));
    }
}
