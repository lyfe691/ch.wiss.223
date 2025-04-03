package ch.wiss.m223_demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import ch.wiss.m223_demo.model.ERole;
import ch.wiss.m223_demo.model.Role;
import ch.wiss.m223_demo.repository.RoleRepository;

@Configuration
@EnableMongoRepositories(basePackages = "ch.wiss.m223_demo.repository")
public class DatabaseInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {
        return args -> {
            initRoles(roleRepository);
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
} 