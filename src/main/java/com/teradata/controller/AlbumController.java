package com.teradata.controller;


import com.teradata.dao.AlbumRepository;
import com.teradata.model.Album;
import com.teradata.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("{userId}/albums/")
public class AlbumController {

    private final AlbumRepository albumRepository;

    AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Album> readAlbums(@PathVariable Long userId) {
        this.validateUser(userId);
        return this.albumRepository.findAlbumsByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{albumId}")
    Album readAlbum(@PathVariable Long userId, @PathVariable Long albumId) {
        this.validateUser(userId);
        return this.albumRepository.findOne(albumId);
    }

    private void validateUser(Long userId) {
        this.albumRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
