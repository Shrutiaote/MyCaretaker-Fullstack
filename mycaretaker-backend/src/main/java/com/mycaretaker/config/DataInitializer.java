package com.mycaretaker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mycaretaker.entity.Role;
import com.mycaretaker.repository.RoleRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeRoles(RoleRepository roleRepository) {

        return args -> {

            if (roleRepository.findByRoleName("ELDERLY_PERSON").isEmpty()) {
                roleRepository.save(new Role("ELDERLY_PERSON"));
            }

            if (roleRepository.findByRoleName("FAMILY_MEMBER").isEmpty()) {
                roleRepository.save(new Role("FAMILY_MEMBER"));
            }

            if (roleRepository.findByRoleName("CARETAKER").isEmpty()) {
                roleRepository.save(new Role("CARETAKER"));
            }
            
            if (roleRepository.findByRoleName("ADMIN").isEmpty()) {
                roleRepository.save(new Role("ADMIN"));
            }
        };
    }
}