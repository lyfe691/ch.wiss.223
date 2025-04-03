package ch.wiss.m223_demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.m223_demo.model.ERole;
import ch.wiss.m223_demo.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRole(ERole name);
} 