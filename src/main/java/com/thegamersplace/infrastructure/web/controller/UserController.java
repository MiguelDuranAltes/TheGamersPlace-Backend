package com.thegamersplace.infrastructure.web.controller;


import com.thegamersplace.domain.entity.*;
import com.thegamersplace.domain.exception.NotFoundException;
import com.thegamersplace.domain.exception.OperationNotAllowed;
import com.thegamersplace.domain.exception.UserLoginExistsException;
import com.thegamersplace.domain.service.UserService;
import com.thegamersplace.domain.service.VideogameService;
import com.thegamersplace.infrastructure.web.converter.UserConverter;
import com.thegamersplace.infrastructure.web.converter.VideogameConverter;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;

@Controller
@Slf4j

public class UserController {

    private final UserService userService;

    private final VideogameService videogameService;

    @Autowired
    public UserController(UserService userService, VideogameService videogameService) {
        this.userService = userService;
        this.videogameService = videogameService;
    }

    @RolesAllowed("ADMIN")
    @MutationMapping
    public Boolean createAdmin(@Argument com.thegamersplace.infrastructure.graphql.types.UserInputGraphqlType input)
            throws OperationNotAllowed, UserLoginExistsException {
        User user = UserConverter.toUser(input);
        userService.createBasicUser(user.getLogin(), user.getPassword(), UserRole.ADMIN);
        return true;
    }

    @QueryMapping
    public com.thegamersplace.infrastructure.graphql.types.UserInfoGraphqlType getUser
            (@Argument String login) throws NotFoundException {
        return userService.getUserByLogin(login);

    }
    @QueryMapping
    public com.thegamersplace.infrastructure.graphql.types.UserListGraphqlType getUsers(){
        return UserConverter.toUserListGraphqlType(userService.getAllUsers());
    }

    @QueryMapping
    public List<com.thegamersplace.infrastructure.graphql.types.ImageGraphqlType> getAllImages() {
        return userService.getAllImages();
    }

    @MutationMapping
    public com.thegamersplace.infrastructure.graphql.types.UserInfoGraphqlType updateUser
            (@Argument com.thegamersplace.infrastructure.graphql.types.UserInfoInputGraphqlType input) throws NotFoundException {
        User user = userService.updateUser(UserConverter.toUser(input));
        return UserConverter.toUserInfoGraphqlType(user);
    }

    @MutationMapping
    public Boolean updateBlocked(@Argument String login) throws NotFoundException {
        return userService.updateBlocked(login);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument String login) throws NotFoundException {
        return userService.deleteUser(login);

    }

    @MutationMapping
    public List<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> addVideogameToFavourites
            (@Argument Integer videogameId) throws NotFoundException {
        Collection<VideogameCard> videogameCollection = userService.addVideogameToFavourites(videogameId);
        return videogameCollection.stream().map(VideogameConverter::toVideoGameCardGraphqlType).toList();
    }

    @MutationMapping
    public List<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> removeVideogameFromFavourites
            (@Argument Integer videogameId) throws NotFoundException {
        Collection<VideogameCard> videogameCollection = userService.removeVideogameFromFavourites(videogameId);
        return videogameCollection.stream().map(VideogameConverter::toVideoGameCardGraphqlType).toList();
    }

    @QueryMapping
    public Boolean isOnFavourites(@Argument Integer videogameId) throws NotFoundException {
        return userService.isOnFavourites(videogameId);
    }

    @QueryMapping
    public List<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> getFavourites
            (@Argument String login) throws NotFoundException {
        Collection<VideogameCard> videogameCollection = userService.getFavourites(login);
        return videogameCollection.stream().map(VideogameConverter::toVideoGameCardGraphqlType).toList();
    }

    @MutationMapping
    public List<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> addVideogameToPlayed
            (@Argument Integer videogameId) throws NotFoundException {
        Collection<VideogameCard> videogameCollection = userService.addVideogameToPlayed(videogameId);
        return videogameCollection.stream().map(VideogameConverter::toVideoGameCardGraphqlType).toList();
    }

    @MutationMapping
    public List<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> removeVideogameFromPlayed
            (@Argument Integer videogameId) throws NotFoundException {
        Collection<VideogameCard> videogameCollection = userService.removeVideogameFromPlayed(videogameId);
        return videogameCollection.stream().map(VideogameConverter::toVideoGameCardGraphqlType).toList();
    }

    @QueryMapping
    public Boolean isOnPlayed(@Argument Integer videogameId) throws NotFoundException {
        return userService.isOnPlayed(videogameId);
    }

    @QueryMapping
    public List<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> getPlayed
            (@Argument String login) throws NotFoundException {
        Collection<VideogameCard> videogameCollection = userService.getPlayed(login);
        return videogameCollection.stream().map(VideogameConverter::toVideoGameCardGraphqlType).toList();
    }

    @MutationMapping
    public List<Integer> addVideogameToWatched
            (@Argument Integer videogameId) throws NotFoundException {
        Collection<Integer> videogameCollection = userService.addVideogameToWatched(videogameId);
        return videogameCollection.stream().toList();
    }

    @MutationMapping
    public com.thegamersplace.infrastructure.graphql.types.VideogameListGraphqlType addVideogameToList
            (@Argument String listName, @Argument Integer idVideogame) throws NotFoundException {
        return VideogameConverter.toVideogameListGraphqlType(userService.addVideogameToList(listName, idVideogame));
    }

    @MutationMapping
    public com.thegamersplace.infrastructure.graphql.types.VideogameListGraphqlType removeVideogameFromList
            (@Argument String listName, @Argument Integer idVideogame) throws NotFoundException {
        return VideogameConverter.toVideogameListGraphqlType(userService.removeVideogameFromList(listName, idVideogame));
    }

    @QueryMapping
    public List<String> showAllVideogamesList
            (@Argument String login) throws NotFoundException {
        return userService.showAllVideogameList(login);
    }

    @QueryMapping
    public com.thegamersplace.infrastructure.graphql.types.VideogameListGraphqlType getVideogameList
            (@Argument String listName, @Argument String login) throws NotFoundException, OperationNotAllowed {
        VideogameList videogameList = userService.getVideogameList(login, listName);
        return VideogameConverter.toVideogameListGraphqlType(videogameList);
    }

    @MutationMapping
    public com.thegamersplace.infrastructure.graphql.types.VideogameListGraphqlType createVideogameList
            (@Argument String listName, @Argument Boolean isPublic){
        return VideogameConverter.toVideogameListGraphqlType(userService.createVideogameList(listName, isPublic));
    }

    @MutationMapping
    public Boolean removeVideogameList (@Argument String listName) throws NotFoundException {
        return userService.removeVideogameList(listName);
    }

    @MutationMapping
    public com.thegamersplace.infrastructure.graphql.types.VideogameListGraphqlType changeViewList
            (@Argument String listName) throws NotFoundException {
        return VideogameConverter.toVideogameListGraphqlType(userService.changePublicView(listName));
    }

    @MutationMapping
    public com.thegamersplace.infrastructure.graphql.types.UserInfoGraphqlType removeReview(
            @Argument com.thegamersplace.infrastructure.graphql.types.ReviewInfoInputGraphqlType reviewInput) throws NotFoundException {
        videogameService.removeReview(reviewInput);
        return UserConverter.toUserInfoGraphqlType((userService.removeReview(reviewInput)));
    }

    @QueryMapping
    public Integer getReview(@Argument String videogame, @Argument String login) throws NotFoundException {
        return userService.getReview(videogame, login);
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(@NonNull Throwable ex, @NonNull DataFetchingEnvironment environment){
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(ex.getMessage())
                .path(environment.getExecutionStepInfo().getPath())
                .location(environment.getField().getSourceLocation())
                .build();
    }

}
