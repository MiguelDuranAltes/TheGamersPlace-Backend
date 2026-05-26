package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

/**
 * Reviews
 */
@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class ReviewInputGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int rating;
    private String comment;
    @jakarta.validation.constraints.NotNull
    private String videogame;
    @jakarta.validation.constraints.NotNull
    private String login;

    public ReviewInputGraphqlType() {
    }

    public ReviewInputGraphqlType(int rating, String comment, String videogame, String login) {
        this.rating = rating;
        this.comment = comment;
        this.videogame = videogame;
        this.login = login;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVideogame() {
        return videogame;
    }
    public void setVideogame(String videogame) {
        this.videogame = videogame;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ReviewInputGraphqlType that = (ReviewInputGraphqlType) obj;
        return Objects.equals(rating, that.rating)
            && Objects.equals(comment, that.comment)
            && Objects.equals(videogame, that.videogame)
            && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating, comment, videogame, login);
    }


    public static ReviewInputGraphqlType.Builder builder() {
        return new ReviewInputGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private int rating;
        private String comment;
        private String videogame;
        private String login;

        public Builder() {
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setVideogame(String videogame) {
            this.videogame = videogame;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }


        public ReviewInputGraphqlType build() {
            return new ReviewInputGraphqlType(rating, comment, videogame, login);
        }

    }
}
