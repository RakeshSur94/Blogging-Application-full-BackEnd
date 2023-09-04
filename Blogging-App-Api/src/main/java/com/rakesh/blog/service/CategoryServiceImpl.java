package com.rakesh.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.blog.exception.ResourceNotFoundException;
import com.rakesh.blog.model.Catagory;
import com.rakesh.blog.playlods.CategoryDto;
import com.rakesh.blog.repository.ICategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	@Autowired
	private ModelMapper mapper;
	@Override
	public CategoryDto registerCategory(CategoryDto categoryDto) {
		Catagory catagory=mapper.map(categoryDto, Catagory.class);
		Catagory savedCatagory= categoryRepository.save(catagory);
		return mapper.map(savedCatagory,CategoryDto.class);
	}

	@Override
	public String updateCategory(CategoryDto categoryDto, int id) {
		Optional<Catagory>opt=categoryRepository.findById(id);
		if(opt.isPresent()) {
			Catagory catagory=opt.get();
			catagory.setCategoryTitle(categoryDto.getCategoryTitle());
			catagory.setCategoryDescription(categoryDto.getCategoryDescription());
			Catagory updateCategory=categoryRepository.save(catagory);
			CategoryDto updated=mapper.map(updateCategory,CategoryDto.class);
			return updated.getCategoryId()+" Category Id is Updated";
		}
		else {
			throw new ResourceNotFoundException("Catargory", "Catagory ", id);
		}
	}

	@Override
	public String deleteCatagory(int id) {
		Catagory catagory=categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Catagory", "Catagory", id));
		CategoryDto categoryDto=mapper.map(catagory,CategoryDto.class);
		categoryRepository.deleteById(categoryDto.getCategoryId());		
		return "Deleted "; 
		
		

	}

	@Override
	public CategoryDto getCategoryById(int catId) {
		Catagory catagory = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Catagory", "Catagory", catId));
		
		return mapper.map(catagory,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategoryList() {
		List<Catagory> catagories=categoryRepository.findAll();
		List<CategoryDto> list=catagories.stream().map(cat->mapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return list;
	}
	

}
