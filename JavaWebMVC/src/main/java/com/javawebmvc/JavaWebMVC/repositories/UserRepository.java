package com.javawebmvc.JavaWebMVC.repositories;

import com.javawebmvc.JavaWebMVC.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByNameContaining(String filter);
}
