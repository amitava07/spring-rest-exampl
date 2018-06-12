package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteById(userId);
    }

    public User updateUser(User user) {
        if (user == null || user.getId() == null) {
            throw new UserNotFoundException("User id/data not passed");
        }
        return userRepository.save(user);
    }
}
