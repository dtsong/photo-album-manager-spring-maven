package com.teradata.controller;


import com.teradata.model.Photo;
import com.teradata.repository.AlbumRepository;
import com.teradata.repository.PhotoRepository;
import com.teradata.exception.AlbumNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/{albumId}/photos")
public class PhotoRestController {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    PhotoRestController(PhotoRepository photoRepository,
                        AlbumRepository albumRepository) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Photo> readPhotos(@PathVariable Long albumId) {
        this.validateAlbum(albumId);
        return this.photoRepository.findByAlbumId(albumId);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable Long albumId, @RequestBody Photo input) {
        this.validateAlbum(albumId);

        return this.albumRepository
                .findById(albumId)
                .map(album -> {
                    Photo result = photoRepository.save(new Photo(album, input.title, input.url, input.thumbnailUrl));

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(result.getId()).toUri();

                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET, value="/{photoId}")
    Photo readPhoto(@PathVariable Long albumId, @PathVariable Long photoId) {
        this.validateAlbum(albumId);
        return this.photoRepository.findOne(photoId);
    }


    private void validateAlbum(Long albumId) {
        this.albumRepository.findById(albumId).orElseThrow(
                () -> new AlbumNotFoundException(albumId));
    }
}
