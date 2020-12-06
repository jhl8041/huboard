package com.humuson.huboard.controller;

import com.humuson.huboard.model.MemberVo;
import com.humuson.huboard.model.TagVo;
import com.humuson.huboard.service.LabService;
import com.humuson.huboard.service.MemberService;
import com.humuson.huboard.tensorflow.model.Recognition;
import com.humuson.huboard.tensorflow.service.ObjectDetector;
import com.humuson.huboard.tensorflow.service.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class LabController {
	@Autowired
    private ObjectDetector objectDetector;
	
	@Autowired
	private LabService labService;
	
	@Autowired
	private MemberService memberService;
	
	
    @GetMapping("/lab")
    public String listUploadedFiles(Model model) throws IOException {
        return "lab/labUpload";
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
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model, @AuthenticationPrincipal User user) throws Exception {
        String storedName = fileToServer(file);
    	
    	String originalImagePath = "C:\\Users\\humuson\\Desktop\\humusOn Workspace\\huboard\\src\\main\\resources\\static\\input\\"+storedName;
        String originalImageWebPath = "http://localhost:8080/resources/input/"+storedName;
        String processedImage = "http://localhost:8080/resources/predicted/"+storedName;
        Map<String, Object> result = objectDetector.detect(originalImagePath, storedName);
        
        //로그인한 회원정보 추출
        MemberVo member = memberService.getMemberByUserId(user.getUsername());
        Long userNum = member.getUserNum();
        
        //추출된 태그 리스트
        List<String> recogTag = new ArrayList<>(); 
        recogTag.addAll(labService.getPrevRecogTag(userNum));
        //List<String> recogTag = new ArrayList<>();
        
        System.out.println(recogTag);
        
        //방금 추출된 태그, 리스트에 추가
        @SuppressWarnings("unchecked")
		List<Recognition> recogTagObj = (List<Recognition>) result.get("recognitions");
        for (Recognition r: recogTagObj) {
        	System.out.println(r.getTitle());
        	recogTag.add(r.getTitle());
        }
        
        //추출된 태그 중 가장 높은 빈도 태그 리스트
        List<String> bestTag = labService.getBestTag(recogTag);
        
        TagVo prevTag = labService.getTagVo(userNum);
        TagVo tag = new TagVo();
        tag.setUserNum(userNum);
        tag.setTagNum(prevTag.getTagNum());
        tag.setSearchedTag(recogTag.toString());
        tag.setBestTag(bestTag.toString());
        
        labService.addTag(tag);
        
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
