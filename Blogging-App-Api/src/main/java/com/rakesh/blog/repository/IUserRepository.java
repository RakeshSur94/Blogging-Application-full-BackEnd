package com.rakesh.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.blog.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String username);

}
