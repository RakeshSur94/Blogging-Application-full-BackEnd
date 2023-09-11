package com.rakesh.blog.service;

import java.util.List;

import com.rakesh.blog.playlods.CommentDto;

public interface ICommentService {
	public CommentDto createComment(CommentDto commentDto,int postId,int userId);
	public void deleteComment(int commentId);
	public List<CommentDto> getCommentByUser(int userId);
	public List<CommentDto> fetchMatchedCommentByKeyWord(String keyword);

}
