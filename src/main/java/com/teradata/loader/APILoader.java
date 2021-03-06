package com.teradata.loader;


import com.teradata.dao.AlbumRepository;
import com.teradata.dao.PhotoRepository;
import com.teradata.jsonreader.APIObjectMapper;
import com.teradata.model.Album;
import com.teradata.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class APILoader implements ApplicationListener<ContextRefreshedEvent> {

    private AlbumRepository albumRepository;
    private PhotoRepository photoRepository;

    @Autowired
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Autowired
    public void setPhotoRepository(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    /* Upon running "mvn spring-boot:run" to start
     * the Application, invoke the APIObjectMapper to
     * populate the DB with Album and Photo data.
     */

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            APIObjectMapper objectMapper = new APIObjectMapper();

            List<Album> albumList = objectMapper.readAlbumJsonWithObjectMapper();
            albumRepository.save(albumList);

            List<Photo> photoList = objectMapper.readPhotoJsonWithObjectMapper();
            photoRepository.save(photoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
