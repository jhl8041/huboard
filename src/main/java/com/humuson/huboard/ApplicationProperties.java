package com.humuson.huboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("application")
@Getter @Setter
public class ApplicationProperties {
	@Value("${application.graph}")
    private String graph;
	@Value("${application.label}")
    private String label;
    private String outputDir = "C:\\Users\\humuson\\Desktop\\humusOn Workspace\\huboard\\src\\main\\resources\\static\\predicted";
    private String uploadDir = "C:\\Users\\humuson\\Desktop\\humusOn Workspace\\huboard\\src\\main\\resources\\static\\input";
	@Value("${application.imageSize}")
    private Integer imageSize;
	@Value("${application.imageMean}")
    private Float imageMean;
}
