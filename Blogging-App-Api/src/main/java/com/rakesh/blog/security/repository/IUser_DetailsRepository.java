package com.rakesh.blog.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.blog.security.entity.User_Details;

public interface IUser_DetailsRepository extends JpaRepository<User_Details, String> {
	Optional<User_Details> findByName(String email);

}
