package com.teradata;

import com.teradata.model.Album;
import com.teradata.model.Photo;
import com.teradata.dao.AlbumRepository;
import com.teradata.dao.PhotoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(AlbumRepository albumRepository,
						   PhotoRepository photoRepository) {
		return (evt) -> Arrays.asList(
				"Alaska Cruise, China Trip, Instagram, Wireframes".split(","))
				.forEach(
						a -> {
							Album album = albumRepository.save(new Album(a));
							photoRepository.save(new Photo(album,
									"Photo1" + a, "http://placehold.it/600/92c952", "http://placehold.it/150/92c952"));
							photoRepository.save(new Photo(album, "Photo2", "http://placehold.it/600/771796", "http://placehold.it/150/771796"));
						});
	}
}
