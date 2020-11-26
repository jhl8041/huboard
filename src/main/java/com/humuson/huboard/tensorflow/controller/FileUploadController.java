package com.humuson.huboard.tensorflow.controller;

import com.humuson.huboard.tensorflow.service.ObjectDetector;
import com.humuson.huboard.tensorflow.service.exception.ServiceException;
import com.humuson.huboard.tensorflow.service.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileUploadController {
	@Autowired
    private StorageService storageService;
	
	@Autowired
    private ObjectDetector objectDetector;

	
    @GetMapping("/lab")
    public String listUploadedFiles(Model model) throws IOException {
        return "lab/labUpload";
    }

    @GetMapping("/lab/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    public String fileToServer(@RequestBody MultipartFile file) throws Exception {
		String basePath = "C:\\Users\\humuson\\Desktop\\humusOn Workspace\\huboard\\src\\main\\resources\\static\\input";		
		UUID uuid = UUID.randomUUID();
		String storedName = uuid.toString()+"_"+file.getOriginalFilename();
		String filePath = basePath + "/" + storedName;
		file.transferTo(new File(filePath));	
		return storedName;
	}
    
    @PostMapping("/lab")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) throws Exception {
        String storedName = fileToServer(file);
    	
    	String originalImagePath = "C:\\Users\\humuson\\Desktop\\humusOn Workspace\\huboard\\src\\main\\resources\\static\\input\\"+storedName;
        String originalImageWebPath = "http://localhost:8080/resources/input/"+storedName;
        String processedImage = "http://localhost:8080/resources/predicted/"+storedName;
        Map<String, Object> result = objectDetector.detect(originalImagePath, storedName);
        model.addAttribute("originalName", file.getOriginalFilename());
        model.addAttribute("originalImage", originalImageWebPath);
        model.addAttribute("predictedImage",processedImage);
        model.addAttribute("recognitions", result.get("recognitions"));
        return "lab/labResult";
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleStorageFileNotFound(ServiceException ex) {
        return ResponseEntity.notFound().build();
    }
}
