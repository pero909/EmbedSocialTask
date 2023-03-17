package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value ={ "reviewId","reviewFullText","numLikes",
        "numComments","numShares","reviewCreatedOn","reviewCreatedOnTime","reviewerId"
        ,"reviewerUrl","reviewerName","reviewerEmail","sourceType","isVerified",
        "source","sourceName","sourceId","tags","href",
        "logoHref","photos"})
public class Review {
    private Long id;
    private String reviewText;
    private Integer rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime reviewCreatedOnDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return getId().equals(review.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
