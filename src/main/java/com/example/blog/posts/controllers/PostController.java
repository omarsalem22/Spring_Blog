package com.example.blog.posts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.posts.entities.Posts;
import com.example.blog.posts.service.PostService;
import com.example.blog.users.User;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {

        this.postService = postService;

    }
    @PostMapping
    public ResponseEntity <Posts> createPost(@RequestBody Posts post, @RequestParam Integer authorId){
        User author=new User();
        author.setId(authorId);
        Posts createdPost=postService.createPost(post, author);
        return ResponseEntity.status(201).body(createdPost);

    }

}
