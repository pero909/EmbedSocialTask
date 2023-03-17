package com.example.demo.repository;

import com.example.demo.model.Review;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryReviewRepository {
    private Set<Review> reviews = new HashSet<>();

    public void addReview(Review review){
        reviews.add(review);
    }

    public Set<Review> getAll(){
        return reviews;
    }

    public Optional<Review> findById(Long id){
       return reviews.stream().filter(review -> review.getId().equals(id)).findFirst();
    }
    public Review save(Review r){
        Review review = new Review();
        review.setReviewText(r.getReviewText());
        review.setId(r.getId());
        review.setRating(r.getRating());
        review.setReviewCreatedOnDate(r.getReviewCreatedOnDate());
        return review;
    }


}
