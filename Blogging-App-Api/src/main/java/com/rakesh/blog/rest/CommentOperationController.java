package com.rakesh.blog.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.blog.playlods.ApiResponse;
import com.rakesh.blog.playlods.CommentDto;
import com.rakesh.blog.service.ICommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentOperationController {
	@Autowired
	private ICommentService commentService;

	@PostMapping("/save/{postId}/{userId}")
	public ResponseEntity<?> savedComment(@RequestBody CommentDto commentDto, @PathVariable int postId,@PathVariable int userId) {

		return new ResponseEntity<CommentDto>(commentService.createComment(commentDto, postId,userId), HttpStatus.CREATED);
	}
	@DeleteMapping("/remove/{cId}")
	public ApiResponse reomveComment(@PathVariable int cId) {
		this.commentService.deleteComment(cId);
		return new ApiResponse("Comment Delete sucessfully", true);
	}
	@GetMapping("/find/{userId}")
	public ResponseEntity<?> fetchCommentByUser(@PathVariable int userId){
		return new ResponseEntity<List<CommentDto>>(commentService.getCommentByUser(userId), HttpStatus.OK);
	}
	@GetMapping("/find/string/{keyword}")
	public ResponseEntity<?> getAllTheCommentMatchByKeyWord(@PathVariable String keyword){
		return new ResponseEntity<List<CommentDto>>(commentService.fetchMatchedCommentByKeyWord(keyword), HttpStatus.OK);
	}

}
