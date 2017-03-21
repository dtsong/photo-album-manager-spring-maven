package com.teradata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teradata.exception.LinkedPhotosExistException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Album {

    @Id
    @JsonProperty
    private Long id;

    @JsonProperty
    private Long userId;

    @JsonProperty
    private String title;

    @OneToMany(mappedBy = "albumId", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Photo> photos = new HashSet<>();

    public Album(Long id, Long userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public String getTitle() { return title; }

    public void setId(Long id) { this.id = id; }

    public void setUserId(Long userId) { this.userId = userId; }

    public void setTitle(String title) { this.title = title; }

    @PreRemove
    private void preventDeleteIfPhotosAssociated() {
        if (!photos.isEmpty()) {
            throw new LinkedPhotosExistException(id);
        }
    }

    Album() { //jpa only
    }
}
