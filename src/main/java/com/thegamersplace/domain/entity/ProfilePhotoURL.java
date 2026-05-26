package com.thegamersplace.domain.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfilePhotoURL {

    private static final Map<String, String> ALIAS_TO_URL = new HashMap<>();

    static {
        ALIAS_TO_URL.put("default", "https://i.imgur.com/MjNmPFm.png");
        ALIAS_TO_URL.put("anakin", "https://i.imgur.com/TbB8VTg.jpg");
        ALIAS_TO_URL.put("arthur", "https://i.imgur.com/d6ZaZMN.png");
        ALIAS_TO_URL.put("batman", "https://i.imgur.com/GkXJO0p.jpg");
        ALIAS_TO_URL.put("crash", "https://i.imgur.com/UrR2D9K.jpg");
        ALIAS_TO_URL.put("franklin", "https://i.imgur.com/BYLQf6j.jpg");
        ALIAS_TO_URL.put("jill", "https://i.imgur.com/7CnTO3P.jpg");
        ALIAS_TO_URL.put("kratos", "https://i.imgur.com/GqlopXk.jpg");
        ALIAS_TO_URL.put("pikachu", "https://i.imgur.com/C4Y1iWm.jpg");
        ALIAS_TO_URL.put("lara", "https://i.imgur.com/DzuSoqf.jpg");
        ALIAS_TO_URL.put("mario", "https://i.imgur.com/2847N36.jpg");
        ALIAS_TO_URL.put("niko", "https://i.imgur.com/uuz98ED.jpg");
        ALIAS_TO_URL.put("ratchet", "https://i.imgur.com/hjU56rh.jpg");
        ALIAS_TO_URL.put("spyro", "https://i.imgur.com/kUwUWmK.png");
        ALIAS_TO_URL.put("leon", "https://i.imgur.com/pGgY0Wg.jpg");
        ALIAS_TO_URL.put("sonic", "https://i.imgur.com/G4CJaVY.jpg");
        ALIAS_TO_URL.put("spidey", "https://i.imgur.com/Pgzpokx.png");
        ALIAS_TO_URL.put("villager", "https://i.imgur.com/G4BVmtx.png");
    }

    public static String getUrlByAlias(String alias) {
        return ALIAS_TO_URL.getOrDefault(alias, "https://i.imgur.com/MjNmPFm.png");
    }

    public static List<com.thegamersplace.infrastructure.graphql.types.ImageGraphqlType> getAllImages() {
        List<com.thegamersplace.infrastructure.graphql.types.ImageGraphqlType> images = new ArrayList<>();
        for (Map.Entry<String, String> entry : ALIAS_TO_URL.entrySet()) {
            com.thegamersplace.infrastructure.graphql.types.ImageGraphqlType image = new com.thegamersplace.infrastructure.graphql.types.ImageGraphqlType();
            image.setAlias(entry.getKey());
            image.setUrl(entry.getValue());
            images.add(image);
        }

        return images;
    }
}
