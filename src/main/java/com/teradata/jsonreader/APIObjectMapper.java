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
    private static final String ALBUM_SOURCE_JSON_URL = "https://jsonplaceholder.typicode.com/albums";
    private static final String PHOTO_SOURCE_JSON_URL = "https://jsonplaceholder.typicode.com/photos";

    /* Grabs the JSON data from above URL source
     * and appends to a List<Album> to be saved into the DB.
     */

    public List<Album> readAlbumJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL(ALBUM_SOURCE_JSON_URL);
        List<Album> albums = Arrays.asList(objectMapper.readValue(url, Album[].class));
        logger.info(albums.toString());
        return albums;
    }

    /* Grabs the JSON data from URL source
     * and appends to a List<Photo> to be saved into the DB.
     */
    public List<Photo> readPhotoJsonWithObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL(PHOTO_SOURCE_JSON_URL);
        List<Photo> photos = Arrays.asList(objectMapper.readValue(url, Photo[].class));
        logger.info(photos.toString());
        return photos;
    }

}
