package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;
}
