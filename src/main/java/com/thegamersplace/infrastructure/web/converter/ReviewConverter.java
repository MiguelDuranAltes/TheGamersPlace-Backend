package com.thegamersplace.infrastructure.web.converter;

import com.thegamersplace.domain.entity.Review;

public class ReviewConverter {

    public static Review toReview(com.thegamersplace.infrastructure.graphql.types.ReviewInputGraphqlType reviewInput){
        Review review = new Review(reviewInput.getRating(), reviewInput.getComment(), reviewInput.getVideogame(), reviewInput.getLogin());
        return review;
    }

    public static com.thegamersplace.infrastructure.graphql.types.ReviewGraphqlType toReviewGraphqlType(Review review){
        return new com.thegamersplace.infrastructure.graphql.types.ReviewGraphqlType(review.getRating(),
                review.getComment(), review.getVideogame_slug(), review.getUser_login());
    }
}
