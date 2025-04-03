package ch.wiss.m223_demo.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.wiss.m223_demo.model.ERole;
import ch.wiss.m223_demo.model.Role;
import ch.wiss.m223_demo.model.User;
import ch.wiss.m223_demo.repository.RoleRepository;
import ch.wiss.m223_demo.repository.UserRepository;

@Configuration
@EnableMongoRepositories(basePackages = "ch.wiss.m223_demo.repository")
public class DatabaseInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            initRoles(roleRepository);
            initUsers(roleRepository, userRepository, passwordEncoder);
        };
    }

    private void initRoles(RoleRepository roleRepository) {
        // Initialize roles if they don't exist
        if (roleRepository.count() == 0) {
            for (ERole role : ERole.values()) {
                roleRepository.save(new Role(role));
            }
        }
    }
    
    private void initUsers(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        // Create admin user if it doesn't exist
        if (!userRepository.existsByName("admin")) {
            User adminUser = new User(
                "admin", 
                "admin@example.com", 
                passwordEncoder.encode("admin123")
            );
            
            Set<Role> roles = new HashSet<>();
            // Add ADMIN role
            Role adminRole = roleRepository.findByRole(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Admin Role not found."));
            roles.add(adminRole);
            
            // Also add USER role to admin so they can access user routes too
            Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: User Role not found."));
            roles.add(userRole);
            
            adminUser.setRoles(roles);
            
            userRepository.save(adminUser);
            System.out.println("Admin user created with username: admin and password: admin123");
        }
        
        // Create regular user if it doesn't exist
        if (!userRepository.existsByName("user")) {
            User regularUser = new User(
                "user", 
                "user@example.com", 
                passwordEncoder.encode("user123")
            );
            
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: User Role not found."));
            roles.add(userRole);
            regularUser.setRoles(roles);
            
            userRepository.save(regularUser);
            System.out.println("Regular user created with username: user and password: user123");
        }
    }
} 