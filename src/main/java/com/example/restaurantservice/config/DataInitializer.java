package com.example.restaurantservice.config;

import com.example.restaurantservice.entity.User;
import com.example.restaurantservice.entity.enums.UserRole;
import com.example.restaurantservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initializeUsers();
    }

    private void initializeUsers() {
        log.info("Checking if admin exist");
        if (userRepository.existsByUserRole(UserRole.ADMIN)) {
            return;
        }

        log.info("Creating admin if not exist");
        User admin = User.builder()
                .firstname("admin")
                .lastname("admin")
                .email("admin@example.com")
                .password(passwordEncoder.encode("password"))
                .userRole(UserRole.ADMIN)
                .build();

        userRepository.save(admin);
    }
}
