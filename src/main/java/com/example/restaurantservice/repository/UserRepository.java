package com.example.restaurantservice.repository;

import com.example.restaurantservice.entity.User;
import com.example.restaurantservice.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);


    boolean existsByUserRole(UserRole admin);
}
