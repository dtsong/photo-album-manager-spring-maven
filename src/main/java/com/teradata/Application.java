package com.teradata;

import com.teradata.jsonreader.APIObjectMapper;
import com.teradata.model.Album;
import com.teradata.model.Photo;
import com.teradata.dao.AlbumRepository;
import com.teradata.dao.PhotoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init() throws IOException {
	    APIObjectMapper objectMapper = new APIObjectMapper();
        objectMapper.readPhotoJsonWithObjectMapper();
        objectMapper.readAlbumJsonWithObjectMapper();
	}
}
