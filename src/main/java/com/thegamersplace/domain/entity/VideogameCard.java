package com.thegamersplace.domain.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public class VideogameCard {

    private Integer id;

    private String name;

    private String slug;

    private String background_image;

    private Integer metacritic;

    public VideogameCard(){

    }

    public VideogameCard(Videogame videogame) {
        this.id = videogame.getId();
        this.name = videogame.getName();
        this.slug = videogame.getSlug();
        this.background_image = videogame.getBackground_image();
        this.metacritic = videogame.getMetacritic();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VideogameCard other = (VideogameCard) obj;
        return Objects.equals(id, other.id);
    }

}


