package com.teradata.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.teradata.model.Album;

import java.util.Collection;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findByTitle(String title);
    Optional<Album> findByUserId(Long userId);
    Collection<Album> findAlbumsByUserId(Long userId);
}
