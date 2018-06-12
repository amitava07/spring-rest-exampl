package com.example.demo.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static String ERR_MESSAGE = "User with id %s not found";

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Integer userId) {
        super(String.format(ERR_MESSAGE, userId));
    }
}
