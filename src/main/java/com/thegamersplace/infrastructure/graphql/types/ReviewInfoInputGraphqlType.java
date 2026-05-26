package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class ReviewInfoInputGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String videogame;
    @jakarta.validation.constraints.NotNull
    private String login;

    public ReviewInfoInputGraphqlType() {
    }

    public ReviewInfoInputGraphqlType(String videogame, String login) {
        this.videogame = videogame;
        this.login = login;
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
        final ReviewInfoInputGraphqlType that = (ReviewInfoInputGraphqlType) obj;
        return Objects.equals(videogame, that.videogame)
            && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videogame, login);
    }


    public static ReviewInfoInputGraphqlType.Builder builder() {
        return new ReviewInfoInputGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private String videogame;
        private String login;

        public Builder() {
        }

        public Builder setVideogame(String videogame) {
            this.videogame = videogame;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }


        public ReviewInfoInputGraphqlType build() {
            return new ReviewInfoInputGraphqlType(videogame, login);
        }

    }
}
