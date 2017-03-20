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

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getUrl() { return url; }

    public String getThumbnailUrl() { return thumbnailUrl; }

    public void setAlbum(Album album) { this.album = album; }

    public void setTitle(String title) { this.title = title; }

    public void setUrl(String url) { this.url = url; }

    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
}
