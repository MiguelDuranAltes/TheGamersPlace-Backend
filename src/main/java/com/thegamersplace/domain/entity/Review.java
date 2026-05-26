package com.thegamersplace.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor

public class Review {

    private Integer rating;
    private String comment;
    private String videogame_slug;
    private String user_login;

    public Review(){

    }

    @Override
    public int hashCode() {
        return Objects.hash(videogame_slug, user_login);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        return Objects.equals(user_login, other.user_login) && Objects.equals(videogame_slug, other.videogame_slug);
    }
}
