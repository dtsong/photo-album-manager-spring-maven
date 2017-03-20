package com.teradata.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Photo {

    @Id
    @JsonProperty
    private Long id;

    @JsonProperty
    private Long albumId;

    @JsonProperty
    private String title;

    @JsonProperty
    private String url;

    @JsonProperty
    private String thumbnailUrl;

    public Photo(Long id, Long albumId, String title, String url, String thumbnailUrl) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getId() { return id; }

    public Long getAlbumId() {
        return albumId;
    }

    public String getTitle() { return title; }

    public String getUrl() { return url; }

    public String getThumbnailUrl() { return thumbnailUrl; }

    public void setId(Long id) { this.id = id; }

    public void setAlbumId(Long albumId) { this.albumId = albumId; }

    public void setTitle(String title) { this.title = title; }

    public void setUrl(String url) { this.url = url; }

    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
}
