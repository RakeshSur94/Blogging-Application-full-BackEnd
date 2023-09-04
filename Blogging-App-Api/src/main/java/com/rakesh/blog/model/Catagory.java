package com.rakesh.blog.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="USER_CATEGORY")
@Getter
@Setter
@NoArgsConstructor
public class Catagory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	@Column(name="titel",length = 100,nullable = false)
	private String categoryTitle;
	@Column(name="description",nullable = false,length = 500)
	private String categoryDescription;
	
	@OneToMany(mappedBy = "catagory",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post>post=new ArrayList<>();

}
