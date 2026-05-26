package com.thegamersplace.infrastructure.web.converter;

import com.thegamersplace.domain.entity.ProfilePhotoURL;
import com.thegamersplace.domain.entity.User;
import com.thegamersplace.domain.entity.UserRole;

import java.util.Collection;
import java.util.List;


public class UserConverter {

    public static User toUser(com.thegamersplace.infrastructure.graphql.types.UserInputGraphqlType userInputGraphqlType) {
        return User.builder().login(userInputGraphqlType.getLogin()).password(userInputGraphqlType.getPassword()).build();
    }

    public static com.thegamersplace.infrastructure.graphql.types.UserInfoGraphqlType toUserInfoGraphqlType(User user) {
        return new com.thegamersplace.infrastructure.graphql.types.UserInfoGraphqlType(user.getLogin(), user.getName(), user.getImageURL(),
                user.getCity(), user.getBlocked(), user.getRole().equals(UserRole.ADMIN),
                user.getReviews().stream().map(ReviewConverter::toReviewGraphqlType).toList(), user.getReviews().size());
    }
    public static com.thegamersplace.infrastructure.graphql.types.UserPublicGraphqlType toUserPublicGraphqlType(User user) {
        return new com.thegamersplace.infrastructure.graphql.types.UserPublicGraphqlType(user.getLogin(), user.getRole().equals(UserRole.ADMIN), user.getBlocked());
    }
    public static com.thegamersplace.infrastructure.graphql.types.UserListGraphqlType toUserListGraphqlType(Collection<User> users) {
        List<com.thegamersplace.infrastructure.graphql.types.UserPublicGraphqlType> userPublicGraphqlTypes = users.stream().map(UserConverter::toUserPublicGraphqlType).toList();
        return new com.thegamersplace.infrastructure.graphql.types.UserListGraphqlType(userPublicGraphqlTypes);
    }

    public static User toUser(com.thegamersplace.infrastructure.graphql.types.UserInfoInputGraphqlType userInfoInputGraphqlType) {
        return User.builder().login(userInfoInputGraphqlType.getLogin()).name(userInfoInputGraphqlType.getName()).
                imageURL(ProfilePhotoURL.getUrlByAlias(userInfoInputGraphqlType.getImageName())).
                city(userInfoInputGraphqlType.getCity()).role(userInfoInputGraphqlType.getAdmin() ? UserRole.ADMIN : UserRole.USER).blocked(userInfoInputGraphqlType.getBlocked()).build();
    }
}
