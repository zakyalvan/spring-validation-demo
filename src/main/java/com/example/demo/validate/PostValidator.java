package com.example.demo.validate;

import com.example.demo.entity.Post;
import com.example.demo.repo.AuthorRepository;
import com.example.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PostValidator implements Validator {
    @Autowired
    private Validator beanValidator;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Post.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Post post = (Post) target;

        // Validate constraints e.g @NotNull, @NotEmpty, @NotBlank etc.
        beanValidator.validate(post, errors);

        // Custom validations for slug.
        if (!errors.hasFieldErrors("slug") && postRepository.existsById(post.getSlug())) {
            errors.rejectValue("slug", "alreadyExists", "Post slug already exists");
        }

        // Custom validation for author email.
        if (!errors.hasFieldErrors("author.email") && !authorRepository.existsById(post.getAuthor().getEmail())) {
            errors.rejectValue("author.email", "notExists", "Post author invalid email");
        }
    }
}