package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class VideogameCardGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    @jakarta.validation.constraints.NotNull
    private String name;
    @jakarta.validation.constraints.NotNull
    private String slug;
    private String background;
    private Double rating;
    private Integer metacritic;

    public VideogameCardGraphqlType() {
    }

    public VideogameCardGraphqlType(int id, String name, String slug, String background, Double rating, Integer metacritic) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.background = background;
        this.rating = rating;
        this.metacritic = metacritic;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBackground() {
        return background;
    }
    public void setBackground(String background) {
        this.background = background;
    }

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getMetacritic() {
        return metacritic;
    }
    public void setMetacritic(Integer metacritic) {
        this.metacritic = metacritic;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final VideogameCardGraphqlType that = (VideogameCardGraphqlType) obj;
        return Objects.equals(id, that.id)
            && Objects.equals(name, that.name)
            && Objects.equals(slug, that.slug)
            && Objects.equals(background, that.background)
            && Objects.equals(rating, that.rating)
            && Objects.equals(metacritic, that.metacritic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug, background, rating, metacritic);
    }


    public static VideogameCardGraphqlType.Builder builder() {
        return new VideogameCardGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private int id;
        private String name;
        private String slug;
        private String background;
        private Double rating;
        private Integer metacritic;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder setBackground(String background) {
            this.background = background;
            return this;
        }

        public Builder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setMetacritic(Integer metacritic) {
            this.metacritic = metacritic;
            return this;
        }


        public VideogameCardGraphqlType build() {
            return new VideogameCardGraphqlType(id, name, slug, background, rating, metacritic);
        }

    }
}
