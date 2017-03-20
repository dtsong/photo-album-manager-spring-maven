package com.teradata.jsonreader;


import com.teradata.model.Album;
import com.teradata.model.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class APIObjectMapper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Album readAlbumJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Album album = objectMapper.readValue(new File("album.json"), Album.class);
        logger.info(album.toString());
        return album;
    }

    public Photo readPhotoJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Photo photo = objectMapper.readValue(new File("photo.json"), Photo.class);
        logger.info(photo.toString());
        return photo;
    }

}
