package com.teradata.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty
    private Long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String url;

    @JsonProperty
    private String thumbnailUrl;

    Photo() {}

    public Photo(Long id, Album album, String title, String url, String thumbnailUrl) {
        this.id = id;
        this.album = album;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Album getAlbum() {
        return album;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getUrl() { return url; }

    public String getThumbnailUrl() { return thumbnailUrl; }

    public void setId(Long id) { this.id = id; }

    public void setAlbum(Album album) { this.album = album; }

    public void setTitle(String title) { this.title = title; }

    public void setUrl(String url) { this.url = url; }

    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
}
