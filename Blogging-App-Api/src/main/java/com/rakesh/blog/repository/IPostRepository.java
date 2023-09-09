package com.rakesh.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rakesh.blog.model.Post;
import com.rakesh.blog.model.User;
import com.rakesh.blog.model.Catagory;

public interface IPostRepository extends JpaRepository<Post, Integer> {

	// finder method
	
	public List<Post> findByUser(User user);

	public List<Post> findByCatagory(Catagory catagory);
	//@Query(value = "FROM Post where title=:keyword")
	public List<Post> findByTitleContaining(String keyword);

}
