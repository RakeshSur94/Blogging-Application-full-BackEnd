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

import com.rakesh.blog.playlods.ApiResponse;
import com.rakesh.blog.playlods.PostDto;
import com.rakesh.blog.service.IPostService;

@RestController
@RequestMapping("/api/")
public class PostOperationController {

	@Autowired
	private IPostService postService;

	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<?> savePost(@RequestBody PostDto postDto, @PathVariable("userId") int userId,
			@PathVariable("catId") int catId) {
		PostDto savePost = postService.createPost(postDto, userId, catId);
		return new ResponseEntity<PostDto>(savePost, HttpStatus.CREATED);

	}

	@GetMapping("/fetch/{userId}")
	public ResponseEntity<?> fetchAllPostByUser(@PathVariable("userId") int userId) {
		List<PostDto> allPost = postService.getAllPostByUser(userId);

		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);

	}

	@GetMapping("/category/{catId}")
	public ResponseEntity<?> fetchAllPostByCategory(@PathVariable("catId") int catId) {

		return new ResponseEntity<List<PostDto>>(postService.getPostByCategoryId(catId), HttpStatus.OK);

	}
	@GetMapping("/findby/{keyword}")
	public ResponseEntity<?> getPostBykeyword(@PathVariable("keyword")String keyword){
		return new ResponseEntity<List<PostDto>>(postService.getAllPostByKeyword(keyword), HttpStatus.OK);
	}
	//get all post
	@GetMapping("/all/post")
	public ResponseEntity<?> fetchAllPost(){
		return new ResponseEntity<List<PostDto>>(postService.getAllPost(), HttpStatus.OK);
	}
	@GetMapping("/post/{postId}")
	public ResponseEntity<?> fetchPostById(@PathVariable("postId")int postId){
		return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.OK);
	}
	//update Post
	@PutMapping("/update/{postId}")
	public ResponseEntity<?> updatePost(@RequestBody PostDto postDto,@PathVariable("postId")int postId){
		return new ResponseEntity<String>(postService.updatePost(postDto, postId), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletePost/{postId}")
	public ApiResponse removePostById(@PathVariable("postId")int postId){
		postService.deletePost(postId);
		return new ApiResponse("Post Sucessfully deleted", true);
		
	}

}
