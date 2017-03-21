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

    /* CREATE - create an album
     * REQUEST: POST /albums
     */
    @RequestMapping(method = RequestMethod.POST)
    public Album create(@RequestBody Album album) {
        return this.albumRepository.save(album);
    }

    /* READ - read all albums
     * REQUEST: GET /albums
     */
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Album> getAlbums() {
        return this.albumRepository.findAllByOrderByIdAsc();
    }

    /* READ - read an album
     * REQUEST: GET /albums/{id}
     */
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return this.albumRepository.findOne(id);
    }

    /* UPDATE - update an album
     * REQUEST: PUT /albums/{id}
     */
    @RequestMapping(method = RequestMethod.PUT, value="{id}")
    public Album update(@PathVariable Long id, @RequestBody Album album) {
        Album update = this.albumRepository.findOne(id);
        update.setId(album.getId());
        update.setTitle(album.getTitle());
        update.setUserId(album.getUserId());
        return this.albumRepository.save(update);
    }

    /* DELETE - delete an album
     * REQUEST: DELETE /albums/{id}
     */
    @RequestMapping(method = RequestMethod.DELETE, value="{id}")
    public void delete(@PathVariable Long id) {
        this.albumRepository.delete(id);
    }



}
