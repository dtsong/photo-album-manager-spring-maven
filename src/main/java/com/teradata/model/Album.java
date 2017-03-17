package com.teradata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Album {

    @OneToMany(mappedBy = "album")
    private Set<Photo> photos = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    public String title;

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Album(String title) {
        this.title = title;
    }

    Album() {}

}
