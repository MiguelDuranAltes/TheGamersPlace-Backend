package com.thegamersplace.domain.service;

import com.thegamersplace.domain.entity.User;
import com.thegamersplace.domain.entity.Videogame;
import com.thegamersplace.domain.entity.VideogameCard;
import com.thegamersplace.infrastructure.repository.UserRepository;
import com.thegamersplace.infrastructure.repository.VideogameRepository;
import com.thegamersplace.infrastructure.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final VideogameRepository videogameRepository;

    private final UserRepository userRepository;

    @Autowired
    public RecommendationService(VideogameRepository videogameRepository, UserRepository userRepository) {
        this.videogameRepository = videogameRepository;
        this.userRepository = userRepository;
    }

    public List<Videogame> getRecommendations(
            List<com.thegamersplace.infrastructure.graphql.types.ChoiceGraphqlType> choices) {

        User user = userRepository.findByLogin(SecurityUtils.getCurrentUserLogin()).get();


        Set<VideogameCard> favourites = user.getFavourites();

        Set<VideogameCard> played = user.getPlayed();

        Set<Integer> watched = user.getWatched();

        Set<Integer> profileids = favourites.stream()
                .map(VideogameCard::getId)
                .collect(Collectors.toSet());

        profileids.addAll(played.stream()
                .map(VideogameCard::getId)
                .collect(Collectors.toSet()));

        List <Videogame> profileVideogames = videogameRepository.findAllById(profileids);

        // Contar géneros y etiquetas en el perfil
        Map<String, Long> genreCountProfile = profileVideogames.stream()
                .flatMap(v -> v.getGenres().stream())
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));

        Map<String, Long> tagCountProfile = profileVideogames.stream()
                .flatMap(v -> v.getTags().stream())
                .collect(Collectors.groupingBy(tag -> tag, Collectors.counting()));

        // Obtener los 2 géneros y 4 etiquetas más frecuentes en el perfil
        List<String> topGenresProfile = genreCountProfile.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(2)
                .map(Map.Entry::getKey)
                .toList();
        List<String> topTagsProfile = tagCountProfile.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(4)
                .map(Map.Entry::getKey)
                .toList();


        List<Integer> likesIds = choices.stream()
                .map(com.thegamersplace.infrastructure.graphql.types.ChoiceGraphqlType::getLike)
                .toList();

        List <Videogame> likes = videogameRepository.findAllById(likesIds);

        // Contar géneros y etiquetas en likes
        Map<String, Long> genreCountLikes = likes.stream()
                .flatMap(v -> v.getGenres().stream())
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));

        Map<String, Long> tagCountLikes = likes.stream()
                .flatMap(v -> v.getTags().stream())
                .collect(Collectors.groupingBy(tag -> tag, Collectors.counting()));

        // Obtener los 3 géneros y 6 etiquetas más frecuentes en likes
        List<String> topGenresLikes = genreCountLikes.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        List<String> topTagsLikes = tagCountLikes.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(6)
                .map(Map.Entry::getKey)
                .toList();


        List<Integer> dislikesIds = choices.stream()
                .map(com.thegamersplace.infrastructure.graphql.types.ChoiceGraphqlType::getDislike)
                .toList();

        List <Videogame> dislikes = videogameRepository.findAllById(dislikesIds);

        // Contar géneros y etiquetas en dislikes
        Map<String, Long> genreCountDislikes = dislikes.stream()
                .flatMap(v -> v.getGenres().stream())
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));

        Map<String, Long> tagCountDislikes = dislikes.stream()
                .flatMap(v -> v.getTags().stream())
                .collect(Collectors.groupingBy(tag -> tag, Collectors.counting()));

        // Obtener los 3 géneros y 6 etiquetas más frecuentes en dislikes
        List<String> topGenresDislikes = genreCountDislikes.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        List<String> topTagsDislikes = tagCountDislikes.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(6)
                .map(Map.Entry::getKey)
                .toList();

        // Filtrar videojuegos recientes y favoritos
        List<Videogame> popularVideogames = videogameRepository.findTop500ByOrderByMetacriticDesc();

        List<Videogame> recentGames = videogameRepository.findAllById(watched);

        // Sumar +10 al metacritic de los videojuegos en watched y añadirlos a la lista
        for (Videogame game : recentGames) {
            if(game.getMetacritic() != null) {
                VideogameCard gameCard = new VideogameCard(game);
                if (!played.contains(gameCard) && !favourites.contains(gameCard)) {
                    Optional<Videogame> existingGame = popularVideogames.stream()
                            .filter(v -> v.getId().equals(game.getId()))
                            .findFirst();

                    if (existingGame.isPresent()) {
                        existingGame.get().setMetacritic(existingGame.get().getMetacritic() + 10);
                    } else {
                        game.setMetacritic(game.getMetacritic() + 10);
                        popularVideogames.add(game);
                    }
                }
            }

        }

        popularVideogames.stream()
                .filter(game -> !played.contains(new VideogameCard(game)) && !favourites.contains(new VideogameCard(game)))
                .collect(Collectors.toList());

        // Ajustar el metacritic según los géneros y tags
        for (Videogame game : popularVideogames) {
            int adjustment = 0;

            for (String genre : game.getGenres()) {
                if (topGenresLikes.contains(genre)) {
                    adjustment += 4;
                }
                if (topGenresProfile.contains(genre)) {
                    adjustment += 2;
                }
                if (topGenresDislikes.contains(genre)) {
                    adjustment -= 1;
                }
            }

            for (String tag : game.getTags()) {
                if (topTagsLikes.contains(tag)) {
                    adjustment += 2;
                }
                if (topTagsProfile.contains(tag)) {
                    adjustment += 1;
                }
                if (topTagsDislikes.contains(tag)) {
                    adjustment -= 1;
                }
            }

            if (game.getMetacritic() != null) {
                game.setMetacritic(game.getMetacritic() + adjustment);
            }
        }

        // Reordenar la lista por metacritic en orden descendente
        popularVideogames.sort((g1, g2) -> g2.getMetacritic().compareTo(g1.getMetacritic()));

        // Devolver los 10 primeros videojuegos
        List<Integer> videogameIds = popularVideogames.stream().limit(10).map(Videogame::getId).toList();

        return videogameRepository.findAllById(videogameIds);

    }

}
