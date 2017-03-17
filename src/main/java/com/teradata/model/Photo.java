package com.teradata.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Photo {

    @JsonIgnore
    @ManyToOne
    private Album album;

    @Id
    @GeneratedValue
    private Long id;

    Photo() {}

    public Photo(Album album, String title, String url, String thumbnailUrl) {
        this.album = album;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String title;
    public String url;
    public String thumbnailUrl;

    public Album getAlbum() {
        return album;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
