package com.rakesh.blog.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.blog.playlods.CategoryDto;
import com.rakesh.blog.service.ICategoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cat/")
@Tag(name = "CategoryController",description ="Create, update,delete,get Category Api!!!")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping("/save")
	public ResponseEntity<?> registerCategories(@RequestBody CategoryDto caDto){
		CategoryDto register=categoryService.registerCategory(caDto);
		return new ResponseEntity<CategoryDto>(register, HttpStatus.CREATED);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCategoryById(@RequestBody CategoryDto caDto,@PathVariable("id")int id){
		String updateCatagory=categoryService.updateCategory(caDto, id);
		return new ResponseEntity<String>(updateCatagory, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> removeCategories(@PathVariable("id") int id){
		String reomveCat=categoryService.deleteCatagory(id);
		return new ResponseEntity<String>(reomveCat, HttpStatus.OK);
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> fetchAllCategories(){
		List<CategoryDto> list=categoryService.getAllCategoryList();
		return new ResponseEntity<List<CategoryDto>>(list, HttpStatus.OK);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<?> fetchCategoryById(@PathVariable("id") int id){
		CategoryDto result=categoryService.getCategoryById(id);
		return new ResponseEntity<CategoryDto>(result, HttpStatus.OK);
	}

}
