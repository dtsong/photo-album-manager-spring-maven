package com.teradata.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.teradata.model.Photo;

import java.util.Collection;


public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Collection<Photo> findByAlbumTitle(String album);
}
