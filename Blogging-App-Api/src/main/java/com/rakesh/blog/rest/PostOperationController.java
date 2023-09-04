package com.rakesh.blog.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.blog.playlods.PostDto;
import com.rakesh.blog.service.IPostService;

@RestController
@RequestMapping("/api/post")
public class PostOperationController {

	@Autowired
	private IPostService postService;

	@PostMapping("/save/{userId}/{catId}")
	public ResponseEntity<?> savePost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("catId") Integer catId) {
		PostDto savePost = postService.createPost(postDto, userId, catId);
		return new ResponseEntity<PostDto>(savePost, HttpStatus.CREATED);

	}
	
    @GetMapping("getpost/{userId}")
	public ResponseEntity<?> fetchAllPostByUser(@PathVariable("userId") Integer userId) {

		return new ResponseEntity<List<PostDto>>(postService.getAllPostByUser(userId), HttpStatus.OK);

	}
    @GetMapping("/{catId}")
   	public ResponseEntity<?> fetchAllPostByCategory(@PathVariable("catId") Integer catId) {

   		return new ResponseEntity<List<PostDto>>(postService.getPostByCategoryId(catId), HttpStatus.OK);

   	}
    

}
