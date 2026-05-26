package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class VideogameGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    @jakarta.validation.constraints.NotNull
    private String name;
    @jakarta.validation.constraints.NotNull
    private String slug;
    @jakarta.validation.constraints.NotNull
    private String releaseDate;
    @jakarta.validation.constraints.NotNull
    private String background;
    private double rating;
    private Integer metacritic;
    private int playtime;
    @jakarta.validation.constraints.NotNull
    private java.util.List<String> screenshots;
    @jakarta.validation.constraints.NotNull
    private java.util.List<String> genres;
    @jakarta.validation.constraints.NotNull
    private java.util.List<PlatformGraphqlType> platforms;
    @jakarta.validation.constraints.NotNull
    private java.util.List<StoreGraphqlType> stores;
    @jakarta.validation.constraints.NotNull
    private java.util.List<String> tags;
    @jakarta.validation.constraints.NotNull
    private java.util.List<RatingGraphqlType> ratings;
    private java.util.List<ReviewGraphqlType> reviews;

    public VideogameGraphqlType() {
    }

    public VideogameGraphqlType(int id, String name, String slug, String releaseDate, String background, double rating, Integer metacritic, int playtime, java.util.List<String> screenshots, java.util.List<String> genres, java.util.List<PlatformGraphqlType> platforms, java.util.List<StoreGraphqlType> stores, java.util.List<String> tags, java.util.List<RatingGraphqlType> ratings, java.util.List<ReviewGraphqlType> reviews) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.releaseDate = releaseDate;
        this.background = background;
        this.rating = rating;
        this.metacritic = metacritic;
        this.playtime = playtime;
        this.screenshots = screenshots;
        this.genres = genres;
        this.platforms = platforms;
        this.stores = stores;
        this.tags = tags;
        this.ratings = ratings;
        this.reviews = reviews;
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

    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBackground() {
        return background;
    }
    public void setBackground(String background) {
        this.background = background;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getMetacritic() {
        return metacritic;
    }
    public void setMetacritic(Integer metacritic) {
        this.metacritic = metacritic;
    }

    public int getPlaytime() {
        return playtime;
    }
    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public java.util.List<String> getScreenshots() {
        return screenshots;
    }
    public void setScreenshots(java.util.List<String> screenshots) {
        this.screenshots = screenshots;
    }

    public java.util.List<String> getGenres() {
        return genres;
    }
    public void setGenres(java.util.List<String> genres) {
        this.genres = genres;
    }

    public java.util.List<PlatformGraphqlType> getPlatforms() {
        return platforms;
    }
    public void setPlatforms(java.util.List<PlatformGraphqlType> platforms) {
        this.platforms = platforms;
    }

    public java.util.List<StoreGraphqlType> getStores() {
        return stores;
    }
    public void setStores(java.util.List<StoreGraphqlType> stores) {
        this.stores = stores;
    }

    public java.util.List<String> getTags() {
        return tags;
    }
    public void setTags(java.util.List<String> tags) {
        this.tags = tags;
    }

    public java.util.List<RatingGraphqlType> getRatings() {
        return ratings;
    }
    public void setRatings(java.util.List<RatingGraphqlType> ratings) {
        this.ratings = ratings;
    }

    public java.util.List<ReviewGraphqlType> getReviews() {
        return reviews;
    }
    public void setReviews(java.util.List<ReviewGraphqlType> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final VideogameGraphqlType that = (VideogameGraphqlType) obj;
        return Objects.equals(id, that.id)
            && Objects.equals(name, that.name)
            && Objects.equals(slug, that.slug)
            && Objects.equals(releaseDate, that.releaseDate)
            && Objects.equals(background, that.background)
            && Objects.equals(rating, that.rating)
            && Objects.equals(metacritic, that.metacritic)
            && Objects.equals(playtime, that.playtime)
            && Objects.equals(screenshots, that.screenshots)
            && Objects.equals(genres, that.genres)
            && Objects.equals(platforms, that.platforms)
            && Objects.equals(stores, that.stores)
            && Objects.equals(tags, that.tags)
            && Objects.equals(ratings, that.ratings)
            && Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug, releaseDate, background, rating, metacritic, playtime, screenshots, genres, platforms, stores, tags, ratings, reviews);
    }


    public static VideogameGraphqlType.Builder builder() {
        return new VideogameGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private int id;
        private String name;
        private String slug;
        private String releaseDate;
        private String background;
        private double rating;
        private Integer metacritic;
        private int playtime;
        private java.util.List<String> screenshots;
        private java.util.List<String> genres;
        private java.util.List<PlatformGraphqlType> platforms;
        private java.util.List<StoreGraphqlType> stores;
        private java.util.List<String> tags;
        private java.util.List<RatingGraphqlType> ratings;
        private java.util.List<ReviewGraphqlType> reviews;

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

        public Builder setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder setBackground(String background) {
            this.background = background;
            return this;
        }

        public Builder setRating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setMetacritic(Integer metacritic) {
            this.metacritic = metacritic;
            return this;
        }

        public Builder setPlaytime(int playtime) {
            this.playtime = playtime;
            return this;
        }

        public Builder setScreenshots(java.util.List<String> screenshots) {
            this.screenshots = screenshots;
            return this;
        }

        public Builder setGenres(java.util.List<String> genres) {
            this.genres = genres;
            return this;
        }

        public Builder setPlatforms(java.util.List<PlatformGraphqlType> platforms) {
            this.platforms = platforms;
            return this;
        }

        public Builder setStores(java.util.List<StoreGraphqlType> stores) {
            this.stores = stores;
            return this;
        }

        public Builder setTags(java.util.List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder setRatings(java.util.List<RatingGraphqlType> ratings) {
            this.ratings = ratings;
            return this;
        }

        public Builder setReviews(java.util.List<ReviewGraphqlType> reviews) {
            this.reviews = reviews;
            return this;
        }


        public VideogameGraphqlType build() {
            return new VideogameGraphqlType(id, name, slug, releaseDate, background, rating, metacritic, playtime, screenshots, genres, platforms, stores, tags, ratings, reviews);
        }

    }
}
