package com.thegamersplace.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.*;


@Builder
@Getter
@Setter
@AllArgsConstructor

public class Videogame {
    @Id
    private Integer id;

    private String name;

    private String slug;

    private String released;

    private String background_image;

    private Double rating;

    private List<Ratings> ratings;

    private Integer metacritic;

    private Integer playtime;

    private List<Platform> platforms;

    private List<String > genres;

    private List<Store> stores;

    private List<String> tags;

    private List<String> short_screenshots;

    private Set<Review> reviews;


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Videogame other = (Videogame) obj;
        return Objects.equals(id, other.id);
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new LinkedHashSet<>();
        }
        if (review.getComment() != null) {
            this.reviews.add(review);
        }

        double total = 0;
        double score = 0;

        for (Ratings r : ratings) {
            if(review.getRating().equals(r.castRating())){
                r.setCount(r.getCount()+1);
            }
            total+=r.getCount();
        }

        for (Ratings r : ratings) {
            Double calculatedPercent = (r.getCount()/total)*100;
            r.setPercent(Math.round(calculatedPercent * 100.0) / 100.0);
            score+=r.castRating() * r.getPercent();
        }

        this.rating = (Math.round(score) / 100.0);

    }

}
