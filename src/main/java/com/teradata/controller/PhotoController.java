package com.teradata.controller;


import com.teradata.model.Photo;
import com.teradata.dao.AlbumRepository;
import com.teradata.dao.PhotoRepository;
import com.teradata.exception.AlbumNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;


@RestController
@RequestMapping("/{albumId}/photos")
public class PhotoController {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    PhotoController(PhotoRepository photoRepository,
                    AlbumRepository albumRepository) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
    }

    /* CREATE - create a photo
     * REQUEST: POST /{albumId}/photos
     */
    @RequestMapping(method = RequestMethod.POST)
    public Photo create(@RequestBody Photo photo) {
        return this.photoRepository.save(photo);
    }

    /* READ - read a list of photos for an albumId
     * REQUEST: GET /{albumId}/photos
     */
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Photo> getAllPhotos(@PathVariable Long albumId) {
        this.validateAlbum(albumId);
        return this.photoRepository.findByAlbumId(albumId);
    }

    /* READ - read a specific photo
     * REQUEST: GET /{albumId}/photos/{id}
     */
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public Photo getPhotoById(@PathVariable Long albumId, @PathVariable Long id) {
        this.validateAlbum(albumId);
        return this.photoRepository.findOne(id);
    }

    /* UPDATE - update a photo
     * REQUEST: PUT /{albumId}/photos/{id}
     */
    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    public Photo update(@PathVariable Long id, @RequestBody Photo photo) {
        Photo update = this.photoRepository.findOne(id);
        update.setId(photo.getId());
        update.setTitle(photo.getTitle());
        update.setUrl(photo.getUrl());
        update.setThumbnailUrl(photo.getThumbnailUrl());
        return this.photoRepository.save(update);
    }

    /* DELETE - delete a photo
     * REQUEST: DELETE /{albumId}/photos/{id}
     */
    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public void delete(@PathVariable Long id) {
        this.photoRepository.delete(id);
    }

    private void validateAlbum(Long albumId) {
        this.albumRepository
                .findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));
    }
}
