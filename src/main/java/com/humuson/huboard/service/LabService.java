package com.humuson.huboard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humuson.huboard.model.TagVo;
import com.humuson.huboard.model.dto.CounterDto;
import com.humuson.huboard.repository.BoardRepository;
import com.humuson.huboard.repository.LabRepository;

@Service
public class LabService {
	@Autowired
	private LabRepository labRepo;
	
	public TagVo getTagVo(Long userNum) {
		if (labRepo.findByUserNum(userNum).isPresent()) {
			return labRepo.findByUserNum(userNum).get();
		}
		
		return new TagVo();
	}
	
	public List<String> getPrevRecogTag(Long userNum) {
		List<String> prevTags = new ArrayList<>();
		String tags = "";
		if (labRepo.findByUserNum(userNum).isPresent()) {
			tags = labRepo.findByUserNum(userNum).get().getSearchedTag();
			tags = tags.substring(1,tags.length()-1);
			prevTags = Arrays.asList(tags.split(", "));
		}
		return prevTags;
	}

	public List<String> getBestTag(List<String> recogTag) {
		List<String> bestTags = new ArrayList<>();
		CounterDto<String> counts = new CounterDto<>();
		
		String[] tagTypes = {"비행기","자전거","새","보트","물병","버스","자동차","고양이","의자","소","식탁","강아지","말","오토바이","사람","화분","양","소파","기차","모니터"};
		
		for (String r: recogTag) counts.add(r);
		
		int maxCnt = 0;
		for (String t: tagTypes) {
			int tmpCnt = counts.count(t);
			if (maxCnt < tmpCnt) {
				maxCnt = tmpCnt;
			}
		}
		
		for (String t: tagTypes) {
			if (counts.count(t) == maxCnt) {
				bestTags.add(t);
			};
		}
		
		return bestTags;
	}

	public void addTag(TagVo tag) {
		labRepo.save(tag);
		
	}

	
	
	
	
}
