package com.example.demo.resources;

import com.example.demo.domain.Post;
import com.example.demo.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/posts")
public class PostResource {
    private PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@PathVariable Integer userId) {
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Integer userId, @PathVariable Integer postId) {
        return postService.getPost(userId, postId);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@PathVariable Integer userId, @RequestBody Post post) {
        Post createdPost = postService.createPost(userId, post);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getPostId())
                .toUri();

        //ResponseEntity.created() creates a new builder with HttpStatus.CREATED status and a
        // location header set to the given URI
        return ResponseEntity.created(uri).body(createdPost);
    }

    @DeleteMapping
    public void deletePost(@PathVariable Integer userId, @PathVariable Integer postId) {
        postService.deletePost(userId, postId);
    }
}
