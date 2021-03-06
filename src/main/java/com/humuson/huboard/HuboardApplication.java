package com.humuson.huboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.humuson.huboard.tensorflow.service.storage.StorageProperties;
import com.humuson.huboard.tensorflow.service.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class, ApplicationProperties.class})
public class HuboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuboardApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

}