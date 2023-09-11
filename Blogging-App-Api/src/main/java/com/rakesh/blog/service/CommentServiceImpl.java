package com.rakesh.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.blog.exception.ResourceNotFoundException;
import com.rakesh.blog.model.Comment;
import com.rakesh.blog.model.Post;
import com.rakesh.blog.model.User;
import com.rakesh.blog.playlods.CommentDto;
import com.rakesh.blog.repository.ICommentRepository;
import com.rakesh.blog.repository.IPostRepository;
import com.rakesh.blog.repository.IUserRepository;
@Service("commentService")
public class CommentServiceImpl implements ICommentService {
	@Autowired
	private ICommentRepository commentRepository;
	@Autowired
	private IPostRepository postRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, int postId,int userId) {
	
		Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST", "Post Id", postId));
		User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		Comment comment = mapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment save = commentRepository.save(comment);
		return mapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment=commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("COMMENT", "Comment Id", commentId));
		commentRepository.delete(comment);

	}
	@Override
	public List<CommentDto> getCommentByUser(int userId) {
		User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		List<Comment> comment=commentRepository.findByUser(user);
		List<CommentDto>finalResult=comment.stream().map(comments->mapper.map(comments, CommentDto.class)).collect(Collectors.toList());
		return finalResult;
	}

	@Override
	public List<CommentDto> fetchMatchedCommentByKeyWord(String keyword) {
	   List<Comment> containing = commentRepository.findByContentContaining(keyword);
	   List<CommentDto> result = containing.stream().map(contains->mapper.map(contains, CommentDto.class)).collect(Collectors.toList());
		return result;
	}

}
