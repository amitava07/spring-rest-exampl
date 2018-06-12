package com.example.demo.exceptions;

public class PostNotFoundException extends RuntimeException {
    private static String ERR_MESSAGE = "Post with id %s not found";

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(Integer userId) {
        super(String.format(ERR_MESSAGE, userId));
    }
}
