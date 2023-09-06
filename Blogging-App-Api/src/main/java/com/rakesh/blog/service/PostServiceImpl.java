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
	public PostDto createPost(PostDto postDto, int userId, int catId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Catagory catagory = categoryRepository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", catId));
		Post post = mapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCatagory(catagory);
		Post savePost = postRepository.save(post);
		PostDto convertToDtoPost = mapper.map(savePost, PostDto.class);
		;
		return convertToDtoPost;

	}

	@Override
	public String updatePost(PostDto postDto, int postId) {
		Optional<Post> opt = postRepository.findById(postId);
		if (opt.isPresent()) {
			Post post = opt.get();
			post.setTitle(postDto.getTitle());
			post.setContent(postDto.getContent());
			 postRepository.save(post);
			 PostDto updated=mapper.map(post, PostDto.class);;
			return "post updated with id " + updated.getPId();
		}
		return "post not found";
	}

	@Override
	public void deletePost(int postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		postRepository.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {

		List<Post>allPost=postRepository.findAll();
		List<PostDto>finalPost=allPost.stream().map(result->mapper.map(result, PostDto.class)).collect(Collectors.toList());
		return finalPost;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post= postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return mapper.map(post, PostDto.class);

	}

	@Override
	public List<PostDto> getAllPostByUser(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		List<Post> post = postRepository.findByUser(user);
		List<PostDto> list = post.stream().map(posts -> mapper.map(posts, PostDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<PostDto> getAllPostByKeyword(String keyword) {
		List<Post> post=postRepository.findByTitle(keyword);
		List<PostDto>result=post.stream().map(results->mapper.map(results, PostDto.class)).collect(Collectors.toList());
		return result;
	}

	@Override
	public List<PostDto> getPostByCategoryId(int catId) {
		Catagory catagory = categoryRepository.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", catId));
		List<Post> post = postRepository.findByCatagory(catagory);
		List<PostDto> list = post.stream().map(posts -> mapper.map(posts, PostDto.class)).collect(Collectors.toList());

		return list;
	}

}
