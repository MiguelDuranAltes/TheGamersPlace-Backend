package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class VideogameCardFeaturesGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    @jakarta.validation.constraints.NotNull
    private String name;
    @jakarta.validation.constraints.NotNull
    private String slug;
    private String background;
    private Integer metacritic;
    @jakarta.validation.constraints.NotNull
    private java.util.List<String> genres;
    @jakarta.validation.constraints.NotNull
    private java.util.List<String> tags;
    @jakarta.validation.constraints.NotNull
    private java.util.List<String> platforms;

    public VideogameCardFeaturesGraphqlType() {
    }

    public VideogameCardFeaturesGraphqlType(int id, String name, String slug, String background, Integer metacritic, java.util.List<String> genres, java.util.List<String> tags, java.util.List<String> platforms) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.background = background;
        this.metacritic = metacritic;
        this.genres = genres;
        this.tags = tags;
        this.platforms = platforms;
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

    public Integer getMetacritic() {
        return metacritic;
    }
    public void setMetacritic(Integer metacritic) {
        this.metacritic = metacritic;
    }

    public java.util.List<String> getGenres() {
        return genres;
    }
    public void setGenres(java.util.List<String> genres) {
        this.genres = genres;
    }

    public java.util.List<String> getTags() {
        return tags;
    }
    public void setTags(java.util.List<String> tags) {
        this.tags = tags;
    }

    public java.util.List<String> getPlatforms() {
        return platforms;
    }
    public void setPlatforms(java.util.List<String> platforms) {
        this.platforms = platforms;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final VideogameCardFeaturesGraphqlType that = (VideogameCardFeaturesGraphqlType) obj;
        return Objects.equals(id, that.id)
            && Objects.equals(name, that.name)
            && Objects.equals(slug, that.slug)
            && Objects.equals(background, that.background)
            && Objects.equals(metacritic, that.metacritic)
            && Objects.equals(genres, that.genres)
            && Objects.equals(tags, that.tags)
            && Objects.equals(platforms, that.platforms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug, background, metacritic, genres, tags, platforms);
    }


    public static VideogameCardFeaturesGraphqlType.Builder builder() {
        return new VideogameCardFeaturesGraphqlType.Builder();
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
        private Integer metacritic;
        private java.util.List<String> genres;
        private java.util.List<String> tags;
        private java.util.List<String> platforms;

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

        public Builder setMetacritic(Integer metacritic) {
            this.metacritic = metacritic;
            return this;
        }

        public Builder setGenres(java.util.List<String> genres) {
            this.genres = genres;
            return this;
        }

        public Builder setTags(java.util.List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder setPlatforms(java.util.List<String> platforms) {
            this.platforms = platforms;
            return this;
        }


        public VideogameCardFeaturesGraphqlType build() {
            return new VideogameCardFeaturesGraphqlType(id, name, slug, background, metacritic, genres, tags, platforms);
        }

    }
}
