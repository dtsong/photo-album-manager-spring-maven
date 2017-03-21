package com.teradata;

import com.teradata.dao.AlbumRepository;
import com.teradata.dao.PhotoRepository;
import com.teradata.jsonreader.APIObjectMapper;
import com.teradata.model.Album;
import com.teradata.model.Photo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.teradata.dao"})
@EntityScan(basePackages = {"com.teradata.model"})
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    public CommandLineRunner init(PhotoRepository photoRepository,
                           AlbumRepository albumRepository) throws IOException {
        return (args) -> {
            APIObjectMapper objectMapper = new APIObjectMapper();
            List<Album> albumList = objectMapper.readAlbumJsonWithObjectMapper();
            List<Photo> photoList = objectMapper.readPhotoJsonWithObjectMapper();

            photoRepository.save(photoList);
            albumRepository.save(albumList);
        };
    }
}
