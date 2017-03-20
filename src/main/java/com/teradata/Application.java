package com.teradata;

import com.teradata.jsonreader.APIObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public void init() throws IOException {
	    APIObjectMapper objectMapper = new APIObjectMapper();
        objectMapper.readPhotoJsonWithObjectMapper();
        objectMapper.readAlbumJsonWithObjectMapper();
	}
}
