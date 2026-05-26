package com.thegamersplace.infrastructure.repository;

import com.thegamersplace.domain.entity.Videogame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomVideogameRepository {
    Page<Videogame> findByFilter(com.thegamersplace.infrastructure.graphql.types.VideogameFilterInputGraphqlType filter, Pageable pageable);

    List<String> getAllGenres();

    List<String> getAllPlatforms();

    List<String> getAllTags();

    List<Videogame> getRandomVideogames(int minTotalRatings, int count);

}
