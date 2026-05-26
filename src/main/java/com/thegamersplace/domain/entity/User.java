package com.thegamersplace.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Builder
@Getter
@Setter
@AllArgsConstructor

public class User {

    private Uuid id;
    private String login;
    private String password;
    private String imageURL;
    private UserRole role;
    private String name;
    private String city;
    private Boolean blocked;
    private Set<VideogameCard> favourites;
    private Set<VideogameCard> played;
    private Set<Integer> watched;
    private Map<String, VideogameList> videogameLists;
    private Set<Review> reviews;


    public User() {
    }

    public User(String login, String password, String imageName, String name, String city, UserRole role) {
        this.id = new Uuid();
        this.login = login;
        this.password = password;
        this.role = role;
        this.imageURL = ProfilePhotoURL.getUrlByAlias(imageName);
        this.name = name;
        this.city = city;
        this.favourites = new LinkedHashSet<>();
        this.played = new LinkedHashSet<>();
        this.watched = new LinkedHashSet<>();
        this.videogameLists = new HashMap<>();
        this.reviews = new LinkedHashSet<>();
        this.blocked = false;
    }

    public User(String login, String password, UserRole role) {
        this.id = new Uuid();
        this.login = login;
        this.password = password;
        this.role = role;
        this.imageURL = ProfilePhotoURL.getUrlByAlias("undefined");
        this.name = "undefined";
        this.city = "undefined";
        this.favourites = new LinkedHashSet<>();
        this.played = new LinkedHashSet<>();
        this.watched = new LinkedHashSet<>();
        this.videogameLists = new HashMap<>();
        this.reviews = new LinkedHashSet<>();
        this.blocked = false;
    }

    public Boolean validateUser() {
        return (this.password.length() > 5 && this.password.matches(".*[@#$%&].*"));
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
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }

    public void addWatched(Integer videogameId) {
        if (this.watched == null) {
            this.watched = new LinkedHashSet<>();
        }
        if (this.watched.contains(videogameId)) {
            this.watched.remove(videogameId);
        }

        if(this.watched.size()==10){
            Integer videogameIdNext = this.watched.iterator().next();
            this.watched.remove(videogameIdNext);
        }

        this.watched.add(videogameId);
    }
}
