package ch.wiss.m223_demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.m223_demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String username);
    
    Boolean existsByName(String username);
    
    Boolean existsByEmail(String email);
} 