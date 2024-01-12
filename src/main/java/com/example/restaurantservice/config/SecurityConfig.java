package com.example.restaurantservice.config;

import com.example.restaurantservice.entity.enums.UserRole;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()

                        // ProductService
                        .requestMatchers(HttpMethod.POST, "/api/v1/product/create").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/product/update/**").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/product/delete/**").hasRole(UserRole.ADMIN.name())

                        // CategoryController
                        .requestMatchers(HttpMethod.POST, "/api/v1/category/create").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/category/update/**").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/category/delete/**").hasRole(UserRole.ADMIN.name())

                        // OrderService
                        .requestMatchers(HttpMethod.POST, "/api/v1/order/create").hasRole(UserRole.USER.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/order/update-status/**").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/order/update/**").hasRole(UserRole.USER.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/order/delete/**").hasRole(UserRole.ADMIN.name())

                        // CartController
                        .requestMatchers(HttpMethod.POST, "/api/v1/cart/create").hasRole(UserRole.USER.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cart/update/**").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/cart/delete/**").hasRole(UserRole.ADMIN.name())

                        // ImageController
                        .requestMatchers(HttpMethod.POST, "/api/v1/image/upload").hasRole(UserRole.ADMIN.name())

                        .anyRequest()
                        .authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return httpSecurity.build();
    }

}
