package com.teradata.controller;


import com.teradata.dao.AlbumRepository;
import com.teradata.model.Album;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumRepository albumRepository;

    AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Album create(@RequestBody Album album) {
        return this.albumRepository.save(album);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Album> readAlbums() {
        return this.albumRepository.findAllByOrderByIdAsc();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    Album readAlbum(@PathVariable Long id) {
        return this.albumRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="{id}")
    public void delete(@PathVariable Long id) {
        this.albumRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="{id}")
    public Album update(@PathVariable Long id, @RequestBody Album album) {
        Album update = this.albumRepository.findOne(id);
        update.setId(album.getId());
        update.setTitle(album.getTitle());
        update.setUserId(album.getUserId());
        return this.albumRepository.save(update);
    }

}
