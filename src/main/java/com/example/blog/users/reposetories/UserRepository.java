package com.example.blog.users.reposetories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    




}
