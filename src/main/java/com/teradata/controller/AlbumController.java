package com.teradata.controller;


import com.teradata.dao.AlbumRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{userId}/albums")
public class AlbumController {

    private final AlbumRepository albumRepository;

    AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
}
