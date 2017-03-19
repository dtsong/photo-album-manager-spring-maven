package com.teradata.controller;


import com.teradata.model.Photo;
import com.teradata.dao.AlbumRepository;
import com.teradata.dao.PhotoRepository;
import com.teradata.exception.AlbumNotFoundException;
import com.teradata.resource.PhotoResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/{albumTitle}/photos")
public class PhotoRestController {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    PhotoRestController(PhotoRepository photoRepository,
                        AlbumRepository albumRepository) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Resources<PhotoResource> readPhotos(@PathVariable String albumTitle) {
        this.validateAlbum(albumTitle);

        List<PhotoResource> photoResourceList = photoRepository
                                            .findByAlbumTitle(albumTitle).stream().map(PhotoResource::new)
                                            .collect(Collectors.toList());

        return new Resources<>(photoResourceList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable String albumId, @RequestBody Photo input) {
        this.validateAlbum(albumId);

        return albumRepository
                .findByTitle(albumId)
                .map(album -> {
                    Photo photo = photoRepository.save(new Photo(album, input.title, input.url, input.thumbnailUrl));

                    Link forOnePhoto = new PhotoResource(photo).getLink("self");

                    return ResponseEntity.created(URI.create(forOnePhoto.getHref())).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET, value="/{photoId}")
    public PhotoResource readPhoto(@PathVariable String albumId, @PathVariable Long photoId) {
        this.validateAlbum(albumId);
        return new PhotoResource(this.photoRepository.findOne(photoId));
    }


    private void validateAlbum(String albumTitle) {
        this.albumRepository
                .findByTitle(albumTitle)
                .orElseThrow(() -> new AlbumNotFoundException(albumTitle));
    }
}
