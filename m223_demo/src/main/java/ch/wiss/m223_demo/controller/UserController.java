package ch.wiss.m223_demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223_demo.model.ERole;
import ch.wiss.m223_demo.model.Role;
import ch.wiss.m223_demo.model.User;
import ch.wiss.m223_demo.repository.RoleRepository;
import ch.wiss.m223_demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User userRequest) {
        // Check if username exists
        if (userRepository.existsByName(userRequest.getName())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        
        // Check if email exists
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        
        // Create new user with encoded password
        User user = new User(
            userRequest.getName(), 
            userRequest.getEmail(), 
            passwordEncoder.encode(userRequest.getPassword())
        );
        
        // Assign default user role
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        
        userRepository.save(user);
        
        return ResponseEntity.ok("User registered successfully");
    }
} 