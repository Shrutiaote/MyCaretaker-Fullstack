package com.mycaretaker.service;

import org.springframework.stereotype.Service;

import com.mycaretaker.entity.Role;
import com.mycaretaker.entity.User;
import com.mycaretaker.repository.RoleRepository;
import com.mycaretaker.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerUser(User user, String roleName) {

        // Check whether email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Find the requested role
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Assign role to user
        user.setRole(role);

        // Save user
        return userRepository.save(user);
    }
}