package com.example.blog.posts.reposetories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.posts.entities.Posts;

public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByTitleContainingIgnoreCase(String keyword);

}
