package com.example.demo.controllers;

import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class PopulateListController {
    private final ReviewService service;
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
}