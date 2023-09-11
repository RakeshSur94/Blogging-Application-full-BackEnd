package com.rakesh.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rakesh.blog.model.Comment;
import com.rakesh.blog.model.User;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
	
	
	public List<Comment> findByUser(User user);
	public List<Comment>findByContentContaining(String keyword);

}
