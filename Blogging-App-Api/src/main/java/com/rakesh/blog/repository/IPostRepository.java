package com.rakesh.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rakesh.blog.model.Post;
import com.rakesh.blog.model.User;
import com.rakesh.blog.model.Catagory;

public interface IPostRepository extends JpaRepository<Post, Integer> {

	// finder method
	@Query("select post from User")
	public List<Post> getByUser(User user);

	public List<Post> findByCatagory(Catagory catagory);

}
