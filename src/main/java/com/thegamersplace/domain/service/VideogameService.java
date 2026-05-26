package com.thegamersplace.domain.service;

import com.thegamersplace.domain.entity.Review;
import com.thegamersplace.domain.entity.User;
import com.thegamersplace.domain.entity.Videogame;
import com.thegamersplace.domain.exception.NotFoundException;
import com.thegamersplace.infrastructure.repository.CustomVideogameRepository;
import com.thegamersplace.infrastructure.repository.UserRepository;
import com.thegamersplace.infrastructure.repository.VideogameRepository;
import com.thegamersplace.infrastructure.web.converter.ReviewConverter;
import com.thegamersplace.infrastructure.web.converter.VideogameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
public class VideogameService {

    private final VideogameRepository videogameRepository;

    private final CustomVideogameRepository customVideogameRepository;
    private final UserRepository userRepository;

    @Autowired
    public VideogameService(VideogameRepository videogameRepository, CustomVideogameRepository customVideogameRepository, UserRepository userRepository) {
        this.videogameRepository = videogameRepository;
        this.customVideogameRepository = customVideogameRepository;
        this.userRepository = userRepository;
    }

    public Page<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> getAllVideogames(Pageable pageable) {
        Page<Videogame> videogamePage = videogameRepository.findAll(pageable);

        Page<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> videogameCardPage = videogamePage.map(videogame -> VideogameConverter.toVideoGameCardGraphqlType(videogame));
        return videogameCardPage;
    }

    public com.thegamersplace.infrastructure.graphql.types.VideogameGraphqlType getVideogameById(Integer id) throws NotFoundException {
        Optional<Videogame> videogameOpt = videogameRepository.findById(id);
        if (videogameOpt.isEmpty()) {
            throw new NotFoundException(id.toString(), Videogame.class);
        }
        Videogame videogame = videogameOpt.get();
        if (videogame.getReviews() == null) {
            videogame.setReviews(new LinkedHashSet<>());
            videogameRepository.save(videogame);
        }
        return VideogameConverter.toVideogameGraphqlType(videogame);
    }


    public Page<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> getVideogamesByFilter(com.thegamersplace.infrastructure.graphql.types.VideogameFilterInputGraphqlType filter, Pageable pageable) {
        Page<Videogame> videogames = customVideogameRepository.findByFilter(filter, pageable);
        return videogames.map(VideogameConverter::toVideoGameCardGraphqlType);
    }

    public List<String> getAllGenres() {
        return customVideogameRepository.getAllGenres();
    }

    public List<String> getAllPlatforms() {
        return customVideogameRepository.getAllPlatforms();
    }

    public List<String> getAllTags() {
        return customVideogameRepository.getAllTags();
    }

    public void addReview(com.thegamersplace.infrastructure.graphql.types.ReviewInputGraphqlType review) throws NotFoundException {
        Optional<User> userOpt = userRepository.findByLogin(review.getLogin());
        if (userOpt.isEmpty()) {
            throw new NotFoundException(review.getLogin(), User.class);
        }

        Optional<Videogame> videogameOpt = videogameRepository.findBySlug(review.getVideogame());
        if (videogameOpt.isEmpty()) {
            throw new NotFoundException(review.getVideogame(), Videogame.class);
        }
        Videogame videogame = videogameOpt.get();

        videogame.addReview(ReviewConverter.toReview(review));

        videogameRepository.save(videogame);
    }

    public void removeReview(com.thegamersplace.infrastructure.graphql.types.ReviewInfoInputGraphqlType reviewInput) throws NotFoundException {
        Optional<User> userOpt = userRepository.findByLogin(reviewInput.getLogin());
        if (userOpt.isEmpty()) {
            throw new NotFoundException(reviewInput.getLogin(), User.class);
        }

        Optional<Videogame> videogameOpt = videogameRepository.findBySlug(reviewInput.getVideogame());
        if (videogameOpt.isEmpty()) {
            throw new NotFoundException(reviewInput.getVideogame(), Videogame.class);
        }
        Videogame videogame = videogameOpt.get();

        for(Review review : videogame.getReviews()){
            if(review.getVideogame_slug().equals(videogame.getSlug())){
                videogame.getReviews().remove(review);
                break;
            }
        }
        videogameRepository.save(videogame);
    }

    public List<com.thegamersplace.infrastructure.graphql.types.OptionGraphqlType> getRandomVideogames(int count){
        List<Videogame> videogames =  customVideogameRepository.getRandomVideogames(200, count);

        List<com.thegamersplace.infrastructure.graphql.types.OptionGraphqlType> videogameOptions = new ArrayList<>();

        for(int i = 0; i < videogames.size(); i=i+2){
            com.thegamersplace.infrastructure.graphql.types.OptionGraphqlType option = new com.thegamersplace.infrastructure.graphql.types.OptionGraphqlType();
            option.setOption1(VideogameConverter.toVideoGameCardFeaturesGraphqlType(videogames.get(i)));
            option.setOption2(VideogameConverter.toVideoGameCardFeaturesGraphqlType(videogames.get(i+1)));
            videogameOptions.add(option);
        }
        return videogameOptions;
    }
}
