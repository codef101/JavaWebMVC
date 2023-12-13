package com.javawebmvc.JavaWebMVC.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javawebmvc.JavaWebMVC.models.UserEntity;
import com.javawebmvc.JavaWebMVC.policies.ValidationResult;
import com.javawebmvc.JavaWebMVC.services.FormService;
import com.javawebmvc.JavaWebMVC.services.PageHitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final FormService formService;
    private final PageHitsService pageHitsService;
    @Autowired
    public UserController(FormService formService, PageHitsService pageHitsService) {
        this.formService = formService;
        this.pageHitsService = pageHitsService;
    }

    @GetMapping("/")
    public String showForm(Model model) {

        model.addAttribute("user", new UserEntity());
        model.addAttribute("pageHits", pageHitsService.getPageHits());
        return "home";
    }

    @ResponseBody
    @PostMapping("/submit")
    public ResponseEntity<String> saveForm(@RequestBody  UserEntity user, Model model) {
        System.out.print(user);
        ValidationResult validationResult = formService.validateForm(user);

        if (validationResult.getErrors().isEmpty()) {
            formService.saveUser(user);

            model.addAttribute("successMessage", "User saved successfully");

            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("response", "success");

            return ResponseEntity.ok(convertObjectToJson(successResponse));
        } else {
            model.addAttribute("validationErrors", validationResult.getErrors());

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("response", "error");
            errorResponse.put("validationErrors", validationResult.getErrors());

            return ResponseEntity.badRequest().body(convertObjectToJson(errorResponse));
        }
    }

    private String convertObjectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    @GetMapping("/user-list")
    public String userList(@RequestParam(name = "filter", required = false) String filter, Model model) {

        List<UserEntity> filteredUserList;

        if (!StringUtils.isEmpty(filter)) {
            filteredUserList = formService.getUsersByFilter(filter);
            System.out.printf(convertObjectToJson(filteredUserList));
        } else {
            filteredUserList = formService.getAllUsers();
        }

        model.addAttribute("userList", filteredUserList);

        model.addAttribute("pageHits", pageHitsService.getPageHits());
        return "user-list";
    }


}
