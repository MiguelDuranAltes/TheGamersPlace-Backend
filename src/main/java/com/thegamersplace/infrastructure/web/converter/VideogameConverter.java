package com.thegamersplace.infrastructure.web.converter;

import com.thegamersplace.domain.entity.*;
import com.thegamersplace.infrastructure.graphql.types.VideogameListGraphqlType;
import org.springframework.data.domain.Page;

public class VideogameConverter {

    public static com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType toVideoGameCardGraphqlType(Videogame videogame) {
        return new com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType(videogame.getId(), videogame.getName(), videogame.getSlug(),
                videogame.getBackground_image(), videogame.getRating(), videogame.getMetacritic());
    }

    public static com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType toVideoGameCardGraphqlType(VideogameCard videogameCard) {
        return new com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType(videogameCard.getId(), videogameCard.getName(), videogameCard.getSlug(),
                videogameCard.getBackground_image(), null, videogameCard.getMetacritic());
    }

    public static com.thegamersplace.infrastructure.graphql.types.VideogameCardFeaturesGraphqlType toVideoGameCardFeaturesGraphqlType(Videogame videogame) {
        return new com.thegamersplace.infrastructure.graphql.types.VideogameCardFeaturesGraphqlType(videogame.getId(), videogame.getName(), videogame.getSlug(),
                videogame.getBackground_image(), videogame.getMetacritic(),
                videogame.getGenres(), videogame.getTags(),
                videogame.getPlatforms().stream().map((platform -> platform.getName())).toList());
    }
    public static com.thegamersplace.infrastructure.graphql.types.VideogameGraphqlType toVideogameGraphqlType(Videogame videogame) {
        return new com.thegamersplace.infrastructure.graphql.types.VideogameGraphqlType(videogame.getId(), videogame.getName(), videogame.getSlug(),
                videogame.getReleased().toString(), videogame.getBackground_image(), videogame.getRating().floatValue(), videogame.getMetacritic(),
                videogame.getPlaytime(), videogame.getShort_screenshots(), videogame.getGenres(),
                videogame.getPlatforms().stream().map(VideogameConverter::toPlatformGraphqlType).toList(),
                videogame.getStores().stream().map(VideogameConverter::toStoreGraphqlType).toList(),
                videogame.getTags(),
                videogame.getRatings().stream().map(VideogameConverter::toRatingGraphqlType).toList(),
                videogame.getReviews() != null ? videogame.getReviews().stream().map(ReviewConverter::toReviewGraphqlType).toList() : null);
    }


    public static com.thegamersplace.infrastructure.graphql.types.PlatformGraphqlType toPlatformGraphqlType(Platform platform) {
        return new com.thegamersplace.infrastructure.graphql.types.PlatformGraphqlType(
                platform.getName(),
                platform.getSlug(),
                platform.getRequirements_en() != null ? toRequirementGraphqlType(platform.getRequirements_en()) : null
        );
    }

    public static com.thegamersplace.infrastructure.graphql.types.RequirementGraphqlType toRequirementGraphqlType(Requirements requirement) {
        return new com.thegamersplace.infrastructure.graphql.types.RequirementGraphqlType(requirement.getMinimum(), requirement.getRecommended());
    }

    public static com.thegamersplace.infrastructure.graphql.types.StoreGraphqlType toStoreGraphqlType(Store store) {
        return new com.thegamersplace.infrastructure.graphql.types.StoreGraphqlType(store.getName(), store.getSlug());
    }

    public static com.thegamersplace.infrastructure.graphql.types.RatingGraphqlType toRatingGraphqlType(Ratings rating) {
        return new com.thegamersplace.infrastructure.graphql.types.RatingGraphqlType(rating.getTitle(), rating.getCount().intValue(), rating.getPercent());
    }

    public static com.thegamersplace.infrastructure.graphql.types.VideogamePageGraphqlType toVideogamePageGraphqlType
            (Page<com.thegamersplace.infrastructure.graphql.types.VideogameCardGraphqlType> page) {


        return new com.thegamersplace.infrastructure.graphql.types.VideogamePageGraphqlType(
                page.getContent(),
                new com.thegamersplace.infrastructure.graphql.types.PageInfoGraphqlType(((int) page.getTotalElements()), page.getTotalPages(), page.getNumber())
        );
    }

    public static com.thegamersplace.infrastructure.graphql.types.VideogameListGraphqlType toVideogameListGraphqlType (VideogameList list) {
        return new VideogameListGraphqlType(list.getName(),
                list.getVideogames().stream().map(videogame -> toVideoGameCardGraphqlType(videogame)).toList(),
                list.getPublicView());
    }

}

