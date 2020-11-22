package com.humuson.huboard.service.api;

import com.humuson.huboard.service.classifier.ObjectDetector;
import com.humuson.huboard.service.exception.ServiceException;
import com.humuson.huboard.service.storage.StorageService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class FileUploadController {
	@Autowired
    private final StorageService storageService;
	
	@Autowired
    private final ObjectDetector objectDetector;

    @Autowired
    public FileUploadController(final StorageService storageService, final ObjectDetector objectDetector) {
        this.storageService = storageService;
        this.objectDetector = objectDetector;
    }

    @GetMapping("/lab")
    public String listUploadedFiles(Model model) throws IOException {
        return "lab/upload-image";
    }

    @GetMapping("/lab/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/lab")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        String originalImagePath = "C:\\Users\\humuson\\Desktop\\humusOn Workspace\\huboard\\src\\main\\resources\\static\\uploads\\" + storageService.store(file);
        originalImagePath = "C:\\Users\\humuson\\Desktop\\objtest\\dog.jpg";
        Map<String, Object> result = objectDetector.detect(originalImagePath);
        model.addAttribute("originalName", file.getOriginalFilename());
        model.addAttribute("originalImage", originalImagePath);
        model.addAttribute("predictedImage", result.get("labeledFilePath"));
        model.addAttribute("recognitions", result.get("recognitions"));
        return "lab/display-result";
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleStorageFileNotFound(ServiceException ex) {
        return ResponseEntity.notFound().build();
    }
}
