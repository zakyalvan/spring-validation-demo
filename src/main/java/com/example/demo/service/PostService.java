package com.example.demo.service;

import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    String createPost(Post post);
    Page<Post> listPosts(Pageable pageable);
}
