package com.example.demo.services;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts(Integer userId) {
        checkUserExists(userId);
        return postRepository.findAll();
    }

    public Post getPost(Integer userId, Integer postId) {
        checkUserExists(userId);
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    public Post createPost(Integer userId, Post post) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException(userId);
        }

        post.setUser(userOptional.get());
        return postRepository.save(post);
    }

    public void deletePost(Integer userId, Integer postId) {
        checkUserExists(userId);
        checkPostExists(postId);

        postRepository.deleteById(postId);
    }

    private void checkUserExists(Integer userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    private void checkPostExists(Integer postId) {
        if(!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }
    }
}
