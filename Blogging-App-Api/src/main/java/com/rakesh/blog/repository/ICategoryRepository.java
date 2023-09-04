package com.rakesh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.blog.model.Catagory;

public interface ICategoryRepository extends JpaRepository<Catagory, Integer>{

}
