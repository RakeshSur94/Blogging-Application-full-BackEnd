package com.rakesh.blog.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rakesh.blog.config.AppConstant;
import com.rakesh.blog.playlods.ApiResponse;
import com.rakesh.blog.playlods.PostDto;
import com.rakesh.blog.playlods.PostResponse;
import com.rakesh.blog.service.IFileOperationService;
import com.rakesh.blog.service.IPostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostOperationController {

	@Autowired
	private IPostService postService;
	@Autowired
	private IFileOperationService fileService;
	@Value("{project.image}")
	private String path;
	private String uploadImage;

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

	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<?> getPostByTitle(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<List<PostDto>>(postService.getAllPostByKeyword(keyword), HttpStatus.OK);
	}

	// get all post
	@GetMapping("/all/post")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<?> fetchAllPost() {
		return new ResponseEntity<List<PostDto>>(postService.getAllPost(), HttpStatus.OK);
	}

	// get all post
	@GetMapping("/all")
	
	public ResponseEntity<?> fetchAllPostByPage(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
	      @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir){
		return new ResponseEntity<PostResponse>(postService.getAllPostByPage(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<?> fetchPostById(@PathVariable("postId") int postId) {
		return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.OK);
	}

	// update Post
	@PutMapping("/update/{postId}")
	public ResponseEntity<?> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") int postId) {
		return new ResponseEntity<String>(postService.updatePost(postDto, postId), HttpStatus.CREATED);
	}

	@DeleteMapping("/deletePost/{postId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ApiResponse removePostById(@PathVariable("postId") int postId) {
		postService.deletePost(postId);
		return new ApiResponse("Post Sucessfully deleted", true);

	}
	@PostMapping("/post/upload/image/{postId}")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile image,@PathVariable int postId) throws IOException{
		PostDto postById = postService.getPostById(postId);
		 String fileName = fileService.uploadImage(path, image);
		 postById.setImageName(fileName);
		 String updatePost = postService.updatePost(postById, postId);
		 return new ResponseEntity<String>(updatePost, HttpStatus.CREATED);		 
		
	}
	@GetMapping(value="/post/download/image/{imageName}" , produces =MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")String imageName,HttpServletResponse response) throws IOException {
		InputStream resource=fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
		
	}

}
