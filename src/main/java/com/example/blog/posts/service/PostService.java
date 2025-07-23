package com.example.blog.posts.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.posts.entities.Posts;
import com.example.blog.posts.reposetories.PostRepository;
import com.example.blog.users.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Posts createPost(Posts post, User author) {
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("posr title cant be empty");

        }

        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);

    }

}
