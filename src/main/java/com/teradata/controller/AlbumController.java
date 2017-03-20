package com.teradata.controller;


import com.teradata.dao.AlbumRepository;
import com.teradata.model.Album;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("{username}/albums/")
public class AlbumController {

    private final AlbumRepository albumRepository;

    AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Album> readPhotos(@PathVariable String username) {
        this.validateUser(username);
        return this.albumRepository.findByUsername(username);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{albumId}")
    Album readAlbum(@PathVariable String username, @PathVariable Long albumId) {
        this.validateUser(username);
        return this.albumRepository.findOne(albumId);
    }

    private void validateUser(String username) {
        this.albumRepository.findByUsername(userId)
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
