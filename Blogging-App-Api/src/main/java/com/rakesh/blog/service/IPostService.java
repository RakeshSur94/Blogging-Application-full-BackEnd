package com.rakesh.blog.service;

import java.util.List;

import com.rakesh.blog.model.Post;
import com.rakesh.blog.playlods.PostDto;

public interface IPostService {
	//save post
	public PostDto createPost(PostDto postDto,int userId,int catId);
	//update post
	public String updatePost(PostDto postDto,int postId);
	//delete post
	public void deletePost(int postId);
	//get all posts
	public List<PostDto> getAllPost();
	//get single post
	public PostDto getPostById(int postId);
	//get posts by category
	public List<PostDto> getPostByCategoryId(int catId);
	//get post by user
	public List<PostDto> getAllPostByUser(int userId);
	//get post by keyword
	public List<PostDto> getAllPostByKeyword(String keyword);

}
