package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class DefaultPostService implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public String createPost(Post post) {
        return postRepository.save(post).getSlug();
    }

    @Override
    public Page<Post> listPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
