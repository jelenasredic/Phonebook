package com.ingsoftware.contactmanager.config;


import com.ingsoftware.contactmanager.entity.Role;
import com.ingsoftware.contactmanager.service.UserRepositoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    private final UserRepositoryUserDetailsService UserDetailsService;

    public SecurityConfig(UserRepositoryUserDetailsService UserDetailsService) {
        this.UserDetailsService = UserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> auth
                        .antMatchers("/user/**").hasAuthority(Role.USER.name())
                        .antMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
                        .antMatchers("/api/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                        .anyRequest()
                        .authenticated()
                )
                .userDetailsService(UserDetailsService)
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

}

