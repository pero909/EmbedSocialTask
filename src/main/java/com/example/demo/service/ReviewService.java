package com.example.demo.service;

import com.example.demo.model.Review;

import java.util.Optional;
import java.util.Set;

public interface ReviewService {
    void addReview(Review review);
    Set<Review> getAll();
    Optional<Review> findById(Long id);
    Review save(Review r);
}
