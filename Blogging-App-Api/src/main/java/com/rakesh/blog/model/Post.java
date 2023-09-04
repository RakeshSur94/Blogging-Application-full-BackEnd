package com.rakesh.blog.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="POST_DETAILS")
@NoArgsConstructor
@Getter
@Setter
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pId;
	@Column(name = "title_post",nullable = false,length = 50)
	private String title;
	@Column(name = "title_content",nullable = false,length = 500)
	private String content;
	private String  imageName;
	private Date date;
	@ManyToOne
	@JoinColumn(name="fk_catId")
	private Catagory catagory;
	@JoinColumn(name="fk_userId")
	@ManyToOne
	private User user;

}
