package com.example.demo.web;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import com.example.demo.validate.PostValidator;
import com.example.demo.web.dto.ResponsePayload;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private PostValidator postValidator;

    @PostMapping
    ResponseEntity<ResponsePayload<Void>> create(@Validated @RequestBody Post post, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            LOGGER.error("Contains validation errors, abort create post");
            return ResponseEntity.badRequest().body(ResponsePayload.badRequest(errors.getAllErrors()));
        }
        var slug = postService.createPost(post);
        var uri = UriComponentsBuilder.fromUriString(request.getRequestURI()).path("/{slug}").build(slug);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    ResponsePayload<Page<Post>> list(Pageable pageable) {
        return ResponsePayload.ok(postService.listPosts(pageable));
    }

    /**
     * Init data binder, here we set custom validator.
     *
     * @param binder
     * @param request
     * @see https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-initbinder.html#mvc-ann-initbinder-model-design
     */
    @InitBinder
    void initBinder(WebDataBinder binder, WebRequest request) {
        LOGGER.info("Init binder, registering custom validator");
        binder.setValidator(postValidator);
    }
}
