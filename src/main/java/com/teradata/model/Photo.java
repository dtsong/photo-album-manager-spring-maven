package com.teradata.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Photo")
public class Photo {

    /*
     * :id: primary key for Photo
     * :albumId: foreign key to Album
     * :title: the Photo's title
     * :url: the Photo's url
     * :thumbnailUrl: the Photo's thumbnailUrl
     *
     * NOTE:
     * @JsonProperty maps these fields to the APIObjectMapper
     * such that the data will be read into these fields.
     */

    @Id
    @JsonProperty
    private Long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private Long albumId;

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

    /* Denotes the Many-To-One relationship between Photos and Album
     * Read-Only: Prevents duplicate mapping since model has album and albumId referring to album.id
     * @JsonIgnore: Prevents the APIObjectMapper from mapping data to this attribute.
     */

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "albumId", insertable = false, updatable = false)
    @JsonIgnore
    private Album album;

    /*
     * Getters and setters for model attributes
     */

    public Album getAlbum() { return album; }

    public void setAlbum(Album album) { this.album = album; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getAlbumId() { return albumId; }

    public void setAlbumId(Long albumId) { this.albumId = albumId; }

    public String getTitle() { return title; }

    public String getUrl() { return url; }

    public String getThumbnailUrl() { return thumbnailUrl; }

    public void setTitle(String title) { this.title = title; }

    public void setUrl(String url) { this.url = url; }

    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    Photo() { //jpa only
    }
}
