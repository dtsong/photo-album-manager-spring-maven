package com.teradata.jsonreader;


import com.teradata.model.Album;
import com.teradata.model.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class APIObjectMapper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Album> readAlbumJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL("https://jsonplaceholder.typicode.com/albums");
        List<Album> albums = Arrays.asList(objectMapper.readValue(url, Album[].class));
        logger.info(albums.toString());
        return albums;
    }

    public Photo readPhotoJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL("https://jsonplaceholder.typicode.com/photos");
        Photo photo = objectMapper.readValue(url, Photo.class);
        logger.info(photo.toString());
        return photo;
    }

}
