package com.teradata.jsonreader;


import com.teradata.model.Album;
import com.teradata.model.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public List<Photo> readPhotoJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL("https://jsonplaceholder.typicode.com/photos");
        List<Photo> photos = Arrays.asList(objectMapper.readValue(url, Photo[].class));
        logger.info(photos.toString());
        return photos;
    }

}
