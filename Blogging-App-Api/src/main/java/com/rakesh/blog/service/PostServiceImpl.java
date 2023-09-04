package com.rakesh.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.blog.exception.ResourceNotFoundException;
import com.rakesh.blog.model.Catagory;
import com.rakesh.blog.model.Post;
import com.rakesh.blog.model.User;
import com.rakesh.blog.playlods.PostDto;
import com.rakesh.blog.repository.ICategoryRepository;
import com.rakesh.blog.repository.IPostRepository;
import com.rakesh.blog.repository.IUserRepository;
@Service("postService")
public class PostServiceImpl implements IPostService {
	@Autowired
	private IPostRepository postRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer catId) {
		User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Catagory  catagory=categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", catId));
		Post post=mapper.map(postDto, Post.class); 		
		//post.setTitle(postDto.getTitle()); post.setContent(postDto.getContent());		 
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCatagory(catagory);
		Post savePost=postRepository.save(post);
		PostDto savedPost=mapper.map(savePost, PostDto.class);;
		return savedPost;
		
	}

	@Override
	public String updatePost(PostDto postDto,Integer postId) {
		Optional<Post> opt=postRepository.findById(postId);
		if(opt.isPresent()) {
			Post post=opt.get();
			post.setTitle(postDto.getTitle());
			post.setContent(postDto.getContent());
			return "post updated with id"+postRepository.save(post).getPId();
		}
		return "post not found";
	}

	@Override
	public String deletePost(Integer postId) {
		Optional<Post> opt=postRepository.findById(postId);
		if(opt.isPresent()) {
		 	postRepository.deleteById(postId); 
		 	return "Post is delted";
		}
		return "Post not found";
	}

	@Override
	public List<PostDto> getAllPost() {
		
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		
	}



	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		List<Post> post=postRepository.getByUser(user);
		List<PostDto>list=post.stream().map(posts->mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<PostDto> getAllPostByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByCategoryId(Integer catId) {
		Catagory catagory=categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id", catId));
		List<Post>post=postRepository.findByCatagory(catagory);
	List<PostDto>list=	post.stream().map(posts->mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return list;
	}

}
