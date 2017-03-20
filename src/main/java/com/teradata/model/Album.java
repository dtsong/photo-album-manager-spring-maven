package com.teradata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teradata.exception.LinkedPhotosExistException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Album {

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private Set<Photo> photos = new HashSet<>();

    @Id
    @JsonProperty
    private Long id;

    @JsonProperty
    private Long userId;

    @JsonProperty
    private String title;

    public Album(Long id, Long userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public Set<Photo> getPhotos() { return photos; }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public String getTitle() { return title; }

    public void setId(Long id) { this.id = id; }

    public void setUserId(Long userId) { this.userId = userId; }

    public void setTitle(String title) { this.title = title; }

    @PreRemove
    private void preventDeleteIfPhotosExist() {
        if (!photos.isEmpty()) {
            throw new LinkedPhotosExistException(id);
        }
    }

}
