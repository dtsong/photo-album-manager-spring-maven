package com.teradata.controller;


import com.teradata.dao.AlbumRepository;
import com.teradata.model.Album;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@RestController
@RequestMapping("albums")
public class AlbumController {

    private final AlbumRepository albumRepository;

    AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Album> readAlbums() {
        return this.albumRepository.findAllByOrderByIdAsc();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{albumId}")
    Album readAlbum(@PathVariable Long albumId) {
        return this.albumRepository.findOne(albumId);
    }

}
