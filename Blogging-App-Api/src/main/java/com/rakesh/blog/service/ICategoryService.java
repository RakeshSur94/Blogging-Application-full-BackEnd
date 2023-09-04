package com.rakesh.blog.service;

import java.util.List;

import com.rakesh.blog.playlods.CategoryDto;

public interface ICategoryService {
	public CategoryDto  registerCategory(CategoryDto categoryDto);
    public String updateCategory(CategoryDto categoryDto,int id);
    public String deleteCatagory(int id);
    public CategoryDto getCategoryById(int catId);
    public List<CategoryDto> getAllCategoryList();
	
}
