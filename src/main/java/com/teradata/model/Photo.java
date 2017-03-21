package com.teradata.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Photo {

    @Id
    @JsonProperty
    private Long id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "album_id")
//    @JsonIgnore
//    private Album album;

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

//    public Album getAlbum() { return album; }
//
//    public void setAlbum(Album album) { this.album = album; }

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

    Photo() { //jpa only
    }
}
