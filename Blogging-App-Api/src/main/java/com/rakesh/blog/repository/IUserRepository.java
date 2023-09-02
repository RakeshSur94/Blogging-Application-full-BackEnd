package com.rakesh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.blog.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
