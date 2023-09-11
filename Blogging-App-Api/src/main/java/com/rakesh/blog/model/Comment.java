package com.rakesh.blog.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="COMMENTS")
@Getter
@Setter
public class Comment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cId;
	private String content;
	@ManyToOne
	@JoinColumn(name = "post_Id")
	private Post post;
	@ManyToOne
	@JoinColumn(name="fk_userID")
	private User user;  
	

}
