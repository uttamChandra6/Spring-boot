package com.testSpringProj.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testSpringProj.bean.Category;
import com.testSpringProj.exceptions.ResourceNotFoundException;
import com.testSpringProj.payloads.CategoryDto;
import com.testSpringProj.repository.CategoryRepo;
import com.testSpringProj.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(category);
		
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " categoryId ", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepo.save(category);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", " categoryId ", categoryId));
		this.categoryRepo.delete(category);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", " categoryId", categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<CategoryDto> categoriesDto = new ArrayList<>();
		List<Category> categories = this.categoryRepo.findAll();
		for(int i=0;i<categories.size();i++) {
			categoriesDto.add(this.modelMapper.map(categories.get(i), CategoryDto.class));
		}
		
		return categoriesDto;
	}

}
