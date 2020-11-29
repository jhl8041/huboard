package com.humuson.huboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humuson.huboard.model.CategoryVo;
import com.humuson.huboard.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepo;
	
	public CategoryVo getCategory(Long categoryId){
		return categoryRepo.findById(categoryId).get();
	}
	
	public List<CategoryVo> getAllCategory(){
		return categoryRepo.findAll();
	}
	
	public void addCategory(CategoryVo categoryvo) {
		categoryRepo.save(categoryvo);
	}
	
	public void deleteCategory(Long categoryId) {
		categoryRepo.deleteById(categoryId);
	}
}
