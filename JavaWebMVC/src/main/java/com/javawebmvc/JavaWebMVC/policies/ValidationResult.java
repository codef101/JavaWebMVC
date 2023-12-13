package com.javawebmvc.JavaWebMVC.policies;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private Map<String, String> errors = new HashMap<>();

    public void addError(String fieldName, String message) {
        errors.put(fieldName, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}

