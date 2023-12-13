package com.javawebmvc.JavaWebMVC.services;

import com.javawebmvc.JavaWebMVC.models.UserEntity;
import com.javawebmvc.JavaWebMVC.policies.ValidationResult;
import com.javawebmvc.JavaWebMVC.repositories.UserRepository;
import com.javawebmvc.JavaWebMVC.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    private final UserRepository userRepository;

    @Autowired
    public FormServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ValidationResult validateForm(UserEntity userEntity) {
        ValidationResult validationResult = new ValidationResult();

        // Validate name
        if (isBlank(userEntity.getName())) {
            validationResult.addError("name", "Name is required");
        }

        // Validate email
        if (isBlank(userEntity.getEmail())) {
            validationResult.addError("email", "Email is required");
        }

        // Validate password
        if (isBlank(userEntity.getPassword())) {
            validationResult.addError("password", "Password is required");
        }

        return validationResult;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> getUsersByFilter(String filter) {
        return userRepository.findByNameContaining(filter);
    }
}
