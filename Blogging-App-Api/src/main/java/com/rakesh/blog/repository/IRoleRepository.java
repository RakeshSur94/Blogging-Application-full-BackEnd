package com.rakesh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.blog.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer>{

}
