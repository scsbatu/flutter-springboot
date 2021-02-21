package com.example.springbootwebflux;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class SpringbootwebfluxApplication {

	@Value("${firebase.credential.resource-path}")
	private String keyPath;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootwebfluxApplication.class, args);
	}


	@Bean
	@Primary
	public void firebaseInitialization() throws IOException {
		Resource resource = new ClassPathResource(keyPath);
		FileInputStream serviceAccount = new FileInputStream(resource.getFile());
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
	}

	@Bean
	@Primary
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.build();
		return objectMapper;
	}


}
