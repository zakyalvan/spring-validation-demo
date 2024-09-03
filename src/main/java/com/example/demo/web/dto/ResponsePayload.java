package com.example.demo.web.dto;

import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record ResponsePayload<T>(String code, String message, T data, List<? extends Object> errors, LocalDateTime timestamp) {
    public static <T> ResponsePayload<T> ok(T data) {
        return new ResponsePayload<>("ok", "Success", data, Collections.emptyList(), LocalDateTime.now());
    }
    public static <T> ResponsePayload<T> badRequest(List<ObjectError> errors) {
        return new ResponsePayload<>("bad-request", "Bad Request", null, errors, LocalDateTime.now());
    }
}
