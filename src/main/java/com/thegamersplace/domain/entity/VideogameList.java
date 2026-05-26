package com.thegamersplace.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class VideogameList {
    private String name;
    private Set<VideogameCard> videogames;
    private Boolean publicView;

    public VideogameList(){
    }
}
