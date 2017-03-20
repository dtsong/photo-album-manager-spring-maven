package com.teradata.controller;


import com.teradata.model.Photo;
import com.teradata.dao.AlbumRepository;
import com.teradata.dao.PhotoRepository;
import com.teradata.exception.AlbumNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;


@RestController
@RequestMapping("/{albumTitle}/photos")
public class PhotoController {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    PhotoController(PhotoRepository photoRepository,
                    AlbumRepository albumRepository) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Photo> readPhotos(@PathVariable String albumTitle) {
        this.validateAlbum(albumTitle);

        return this.photoRepository.findByAlbumTitle(albumTitle);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable String albumTitle, @RequestBody Photo input) {
        this.validateAlbum(albumTitle);

        return albumRepository
                .findByTitle(albumTitle)
                .map(album -> {
                    Photo photo = photoRepository.save(new Photo(input.getId(), input.getAlbumId(),
                            input.getTitle(), input.getUrl(), input.getThumbnailUrl()));

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(photo.getId()).toUri();

                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public Photo readPhoto(@PathVariable String albumTitle, @PathVariable Long id) {
        this.validateAlbum(albumTitle);
        return this.photoRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    public Photo update(@PathVariable Long id, @RequestBody Photo photo) {
        Photo update = this.photoRepository.findOne(id);
        update.setId(photo.getId());
        update.setTitle(photo.getTitle());
        update.setUrl(photo.getUrl());
        update.setThumbnailUrl(photo.getThumbnailUrl());
        return this.photoRepository.save(update);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    public void delete(@PathVariable Long id) {
        this.photoRepository.delete(id);
    }


    private void validateAlbum(String albumTitle) {
        this.albumRepository
                .findByTitle(albumTitle)
                .orElseThrow(() -> new AlbumNotFoundException(albumTitle));
    }
}
