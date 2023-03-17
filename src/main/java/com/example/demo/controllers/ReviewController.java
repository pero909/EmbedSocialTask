package com.example.demo.controllers;

import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService service;
    @GetMapping("/noFilter")
    public String noFilterPage(Model model, HttpServletRequest request){
        model.addAttribute("reviews",service.getAll());
        System.out.println(service.getAll());
        return "site";
    }
    //
    @GetMapping("/populateList")
    public String populate() throws IOException {
        File jsonFile = new ClassPathResource("templates/reviews.json").getFile();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<Review> reviewList = objectMapper.readValue(jsonFile,new TypeReference<>(){});


        reviewList.forEach(service::addReview);

        System.out.println(service.getAll());

        return "site";

    }
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false)String orderByRating,
                         @RequestParam(required = false)Integer orderMinRating,
                         @RequestParam(required = false)String orderByDate,
                         @RequestParam(required = false)String orderByText,
                         Model model,HttpServletRequest request){

        System.out.println(orderByText);
        List<Review> reviews = new ArrayList<>(service.getAll());
       if(getTextComparator(orderByText)==null){
           reviews=reviews.stream()
                   .filter(review -> review.getRating()>=orderMinRating)
                   .collect(Collectors.toList());

           reviews.sort(getDateComparator(orderByDate));
           reviews.sort(getRatingComparator(orderByRating));
       }else{


           reviews=reviews.stream()
                   .filter(review -> review.getRating()>=orderMinRating)
                   .sorted(getTextComparator(orderByText))
                   .collect(Collectors.toList());

        //   reviews.sort(getTextComparator(orderByText));
           reviews.sort(getDateComparator(orderByDate));
           reviews.sort(getRatingComparator(orderByRating));
       }
        model.addAttribute("reviews",reviews);
        return "site";
    }

    public Comparator<Review> getRatingComparator(String order){
        Comparator<Review> byRating = Comparator.comparing(Review::getRating);
        if(order.equals("lowestFirst")){
             return byRating;
        }
        return byRating.reversed();
    }
    public Comparator<Review> getDateComparator(String order){
        Comparator<Review> byDate = Comparator.comparing(Review::getReviewCreatedOnDate);
        if(order.equals("oldestFirst")){
            return byDate;
        }
        return byDate.reversed();
    }
    public Comparator<Review> getTextComparator(String order){
        Comparator<Review> textComparator = Comparator.comparing(Review::getReviewText,
                Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
        if(order.equals("No")){
            return null;
        }else return textComparator.reversed();

    }

}