package com.javawebmvc.JavaWebMVC.services;

import com.javawebmvc.JavaWebMVC.models.UserEntity;
import com.javawebmvc.JavaWebMVC.policies.ValidationResult;

import java.util.List;

public interface FormService {
    ValidationResult validateForm(UserEntity userEntity);
    void saveUser(UserEntity userEntity);
    List<UserEntity> getAllUsers();
    List<UserEntity> getUsersByFilter(String filter);
}
