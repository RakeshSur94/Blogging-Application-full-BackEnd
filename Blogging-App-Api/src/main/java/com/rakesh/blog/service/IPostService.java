package com.rakesh.blog.service;

import java.util.List;

import com.rakesh.blog.model.Post;
import com.rakesh.blog.playlods.PostDto;

public interface IPostService {
	//save post
	public PostDto createPost(PostDto postDto,Integer userId,Integer catId);
	//update post
	public String updatePost(PostDto postDto,Integer postId);
	//delete post
	public String deletePost(Integer postId);
	//get all posts
	public List<PostDto> getAllPost();
	//get single post
	public Post getPostById(Integer postId);
	//get posts by category
	public List<PostDto> getPostByCategoryId(Integer catId);
	//get post by user
	public List<PostDto> getAllPostByUser(Integer userId);
	//get post by keyword
	public List<PostDto> getAllPostByKeyword(String keyword);

}
