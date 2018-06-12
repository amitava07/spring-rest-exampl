package com.example.demo.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class CustomFieldErrorResponse {

    private final Date time;
    private final String message;
    private List<CustomFieldError> details = new ArrayList<>();

    public void addFieldError(String fieldName, String errorMessage) {
        details.add(new CustomFieldError(fieldName, errorMessage));
    }
}
