package com.example.demo.service.impl;

import com.example.demo.model.Review;
import com.example.demo.repository.InMemoryReviewRepository;
import com.example.demo.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final InMemoryReviewRepository reviewRepository;

    @Override
    public void addReview(Review review) {
        this.reviewRepository.addReview(review);
    }

    @Override
    public Set<Review> getAll() {
        return this.reviewRepository.getAll();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return this.reviewRepository.findById(id);
    }

    @Override
    public Review save(Review r) {
        return this.reviewRepository.save(r);
    }
}
