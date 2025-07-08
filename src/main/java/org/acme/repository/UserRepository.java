package org.acme.repository;

import java.util.List;
import java.util.Optional;

import org.acme.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public List<User> findByNameContaining(String name) {
        return find("LOWER(name) LIKE LOWER(?1)", "%" + name + "%").list();
    }

    public boolean existsByEmail(String email) {
        return count("email", email) > 0;
    }

    public List<User> findAllOrderedByName() {
        return find("ORDER BY name ASC").list();
    }
}
