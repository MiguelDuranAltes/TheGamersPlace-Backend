package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

/**
 * schema.graphqls
 * User types
 */
@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class UserInfoGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String login;
    private String name;
    @jakarta.validation.constraints.NotNull
    private String imageUrl;
    private String city;
    private boolean blocked;
    private boolean admin;
    private java.util.List<ReviewGraphqlType> reviews;
    private int videogamesVoted;

    public UserInfoGraphqlType() {
    }

    public UserInfoGraphqlType(String login, String name, String imageUrl, String city, boolean blocked, boolean admin, java.util.List<ReviewGraphqlType> reviews, int videogamesVoted) {
        this.login = login;
        this.name = name;
        this.imageUrl = imageUrl;
        this.city = city;
        this.blocked = blocked;
        this.admin = admin;
        this.reviews = reviews;
        this.videogamesVoted = videogamesVoted;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public boolean getBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean getAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public java.util.List<ReviewGraphqlType> getReviews() {
        return reviews;
    }
    public void setReviews(java.util.List<ReviewGraphqlType> reviews) {
        this.reviews = reviews;
    }

    public int getVideogamesVoted() {
        return videogamesVoted;
    }
    public void setVideogamesVoted(int videogamesVoted) {
        this.videogamesVoted = videogamesVoted;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserInfoGraphqlType that = (UserInfoGraphqlType) obj;
        return Objects.equals(login, that.login)
            && Objects.equals(name, that.name)
            && Objects.equals(imageUrl, that.imageUrl)
            && Objects.equals(city, that.city)
            && Objects.equals(blocked, that.blocked)
            && Objects.equals(admin, that.admin)
            && Objects.equals(reviews, that.reviews)
            && Objects.equals(videogamesVoted, that.videogamesVoted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, imageUrl, city, blocked, admin, reviews, videogamesVoted);
    }


    public static UserInfoGraphqlType.Builder builder() {
        return new UserInfoGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private String login;
        private String name;
        private String imageUrl;
        private String city;
        private boolean blocked;
        private boolean admin;
        private java.util.List<ReviewGraphqlType> reviews;
        private int videogamesVoted;

        public Builder() {
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setBlocked(boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public Builder setAdmin(boolean admin) {
            this.admin = admin;
            return this;
        }

        public Builder setReviews(java.util.List<ReviewGraphqlType> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setVideogamesVoted(int videogamesVoted) {
            this.videogamesVoted = videogamesVoted;
            return this;
        }


        public UserInfoGraphqlType build() {
            return new UserInfoGraphqlType(login, name, imageUrl, city, blocked, admin, reviews, videogamesVoted);
        }

    }
}
