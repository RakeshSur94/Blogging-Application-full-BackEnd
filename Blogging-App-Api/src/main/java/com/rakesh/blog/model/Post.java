package com.rakesh.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="POST_DETAILS")
@NoArgsConstructor
@Getter
@Setter
public class Post implements Serializable{
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
	@JoinColumn(name="catId")
	private Catagory catagory;
	@ManyToOne
	private User user;
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Comment> comment=new HashSet<>();

}
