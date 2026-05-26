package com.thegamersplace.infrastructure.repository;

import com.thegamersplace.domain.entity.Videogame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VideogameRepository extends MongoRepository<Videogame, Integer> {
    Page<Videogame> findAll(Pageable pageable);
    Page<Videogame> findByGenres(String genre, Pageable pageable);
    Page<Videogame> findByPlatforms(String platform, Pageable pageable);
    Page<Videogame> findByNameContains(String name, Pageable pageable);
    Page<Videogame> findByRatingGreaterThanEqual(Float rating, Pageable pageable);
    Page<Videogame> findByOrderByRatingDesc(Pageable pageable);
    Optional<Videogame> findBySlug(String slug);
    List<Videogame> findTop500ByOrderByMetacriticDesc();
}