package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class VideogameFilterInputGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private java.util.List<String> platforms;
    private java.util.List<String> genres;
    private java.util.List<String> tags;
    private Double rating;
    private String releaseYear;
    private Boolean orderByRatingDesc;
    private Boolean orderByReleaseDateDesc;

    public VideogameFilterInputGraphqlType() {
    }

    public VideogameFilterInputGraphqlType(String name, java.util.List<String> platforms, java.util.List<String> genres, java.util.List<String> tags, Double rating, String releaseYear, Boolean orderByRatingDesc, Boolean orderByReleaseDateDesc) {
        this.name = name;
        this.platforms = platforms;
        this.genres = genres;
        this.tags = tags;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.orderByRatingDesc = orderByRatingDesc;
        this.orderByReleaseDateDesc = orderByReleaseDateDesc;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public java.util.List<String> getPlatforms() {
        return platforms;
    }
    public void setPlatforms(java.util.List<String> platforms) {
        this.platforms = platforms;
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

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Boolean getOrderByRatingDesc() {
        return orderByRatingDesc;
    }
    public void setOrderByRatingDesc(Boolean orderByRatingDesc) {
        this.orderByRatingDesc = orderByRatingDesc;
    }

    public Boolean getOrderByReleaseDateDesc() {
        return orderByReleaseDateDesc;
    }
    public void setOrderByReleaseDateDesc(Boolean orderByReleaseDateDesc) {
        this.orderByReleaseDateDesc = orderByReleaseDateDesc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final VideogameFilterInputGraphqlType that = (VideogameFilterInputGraphqlType) obj;
        return Objects.equals(name, that.name)
            && Objects.equals(platforms, that.platforms)
            && Objects.equals(genres, that.genres)
            && Objects.equals(tags, that.tags)
            && Objects.equals(rating, that.rating)
            && Objects.equals(releaseYear, that.releaseYear)
            && Objects.equals(orderByRatingDesc, that.orderByRatingDesc)
            && Objects.equals(orderByReleaseDateDesc, that.orderByReleaseDateDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, platforms, genres, tags, rating, releaseYear, orderByRatingDesc, orderByReleaseDateDesc);
    }


    public static VideogameFilterInputGraphqlType.Builder builder() {
        return new VideogameFilterInputGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private String name;
        private java.util.List<String> platforms;
        private java.util.List<String> genres;
        private java.util.List<String> tags;
        private Double rating;
        private String releaseYear;
        private Boolean orderByRatingDesc;
        private Boolean orderByReleaseDateDesc;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPlatforms(java.util.List<String> platforms) {
            this.platforms = platforms;
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

        public Builder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setReleaseYear(String releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Builder setOrderByRatingDesc(Boolean orderByRatingDesc) {
            this.orderByRatingDesc = orderByRatingDesc;
            return this;
        }

        public Builder setOrderByReleaseDateDesc(Boolean orderByReleaseDateDesc) {
            this.orderByReleaseDateDesc = orderByReleaseDateDesc;
            return this;
        }


        public VideogameFilterInputGraphqlType build() {
            return new VideogameFilterInputGraphqlType(name, platforms, genres, tags, rating, releaseYear, orderByRatingDesc, orderByReleaseDateDesc);
        }

    }
}
