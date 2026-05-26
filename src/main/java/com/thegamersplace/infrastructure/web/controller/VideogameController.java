package com.thegamersplace.infrastructure.web.controller;

import com.thegamersplace.domain.exception.NotFoundException;
import com.thegamersplace.domain.service.RecommendationService;
import com.thegamersplace.domain.service.UserService;
import com.thegamersplace.domain.service.VideogameService;
import com.thegamersplace.infrastructure.graphql.types.VideogameGraphqlType;
import com.thegamersplace.infrastructure.graphql.types.VideogamePageGraphqlType;
import com.thegamersplace.infrastructure.web.converter.UserConverter;
import com.thegamersplace.infrastructure.web.converter.VideogameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideogameController {


    private final VideogameService videogameService;

    private final UserService userService;

    private final RecommendationService recommendationService;

    @Autowired
    public VideogameController(VideogameService videogameService, UserService userService, RecommendationService recommendationService) {
        this.videogameService = videogameService;
        this.userService = userService;
        this.recommendationService = recommendationService;
    }

    @QueryMapping
    public VideogameGraphqlType getVideogameById(@Argument Integer id) throws NotFoundException {
        return videogameService.getVideogameById(id);
    }

    @QueryMapping
    public VideogamePageGraphqlType getAllVideogames
            (@Argument com.thegamersplace.infrastructure.graphql.types.PageInputGraphqlType pageInput) {
        Pageable pageable = PageRequest.of(pageInput.getPage(), pageInput.getSize());
        Page<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> page = videogameService.getAllVideogames(pageable);

        return VideogameConverter.toVideogamePageGraphqlType(page);
    }


    @QueryMapping
    public VideogamePageGraphqlType getVideogamesFiltered(
            @Argument com.thegamersplace.infrastructure.graphql.types.VideogameFilterInputGraphqlType filter,
            @Argument com.thegamersplace.infrastructure.graphql.types.PageInputGraphqlType pageInput
    ) {
        Pageable pageable = PageRequest.of(pageInput.getPage(), pageInput.getSize());

        Page<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> page = videogameService.getVideogamesByFilter(filter, pageable);

        return VideogameConverter.toVideogamePageGraphqlType(page);
    }

    @QueryMapping
    public List<String> getAllGenres() {
        return videogameService.getAllGenres();
    }

    @QueryMapping
    public List<String> getAllPlatforms() {
        return videogameService.getAllPlatforms();
    }

    @QueryMapping
    public List<String> getAllTags() {
        return videogameService.getAllTags();
    }

    @MutationMapping
    public com.thegamersplace.infrastructure.graphql.types.UserInfoGraphqlType addReview(
            @Argument com.thegamersplace.infrastructure.graphql.types.ReviewInputGraphqlType review
    ) throws NotFoundException {
        videogameService.addReview(review);
        return UserConverter.toUserInfoGraphqlType(userService.addReview(review));
    }

    @QueryMapping
    public List<com.thegamersplace.infrastructure.graphql.types.OptionGraphqlType> getRandomVideogames(){
        return videogameService.getRandomVideogames(20);
    }


    @QueryMapping
    public List<com.thegamersplace.infrastructure.graphql.types.VideogameCardFeaturesGraphqlType> getRecommendations(
            @Argument List<com.thegamersplace.infrastructure.graphql.types.ChoiceGraphqlType> choices){
        return recommendationService.getRecommendations(choices).stream().map(
                videogame -> VideogameConverter.toVideoGameCardFeaturesGraphqlType(videogame)
        ).toList();
    }
}
