package com.teradata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teradata.exception.LinkedPhotosExistException;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.PreRemove;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Album")
public class Album {

    /*
     * :id: primary key for Album
     * :userId: foreign key to User
     * :title: Album's title
     *
     * NOTE:
     * @JsonProperty maps these fields to the APIObjectMapper
     * such that the data will be read into these fields.
     */

    @Id
    @JsonProperty
    public Long id;

    @JsonProperty
    private Long userId;

    @JsonProperty
    private String title;

    public Album(Long albumId, Long userId, String title) {
        this.id = albumId;
        this.userId = userId;
        this.title = title;
    }

    /* Denotes the One-to-Many relationship between Album to Photos
     * @JsonIgnore: Prevents the APIObjectMapper from mapping data to this attribute.
     */

    @OneToMany(mappedBy = "album", targetEntity = Photo.class, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Photo> photos = new HashSet<>();

    /*
     * Getters and setters for model attributes
     */

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) { this.photos = photos; }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public String getTitle() { return title; }

    public void setId(Long id) { this.id = id; }

    public void setUserId(Long userId) { this.userId = userId; }

    public void setTitle(String title) { this.title = title; }

    /*
     * This is called before the Album object is removed
     * in order to prevent delete if the Album has Photo
     * objects associated to it.
     */

    @PreRemove
    private void preventDeleteIfPhotosAssociated() {
        if (!photos.isEmpty()) {
            throw new LinkedPhotosExistException(id);
        }
    }

    Album() { //jpa only
    }
}
