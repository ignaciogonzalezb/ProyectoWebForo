package com.retroforo.backend.controller;

import com.retroforo.backend.model.Post;
import com.retroforo.backend.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id,@RequestBody Post updatedPost) {

    Post post = postRepository.findById(id)
    .orElseThrow();

    post.setTitle(updatedPost.getTitle());
    post.setContent(updatedPost.getContent());

    return postRepository.save(post);}

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
    postRepository.deleteById(id);}


}