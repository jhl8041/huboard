package com.humuson.huboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("application")
public class ApplicationProperties {
	@Value("${application.graph}")
    private String graph;
	@Value("${application.label}")
    private String label;
	@Value("${application.outputDir}")
    private String outputDir;
	@Value("${application.uploadDir}")
    private String uploadDir;
	@Value("${application.imageSize}")
    private Integer imageSize;
	@Value("${application.imageMean}")
    private Float imageMean;

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

    public Float getImageMean() {
        return imageMean;
    }

    public void setImageMean(Float imageMean) {
        this.imageMean = imageMean;
    }
}
