package com.rakesh.blog.playlods;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.rakesh.blog.model.Catagory;
import com.rakesh.blog.model.Comment;
import com.rakesh.blog.model.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer pId;
	@NotBlank(message="Title shouldnot be blanked")
	@Size(max = 50,min=10,message = "title should be greater than 10 char  lessthan or equal to 50 char")
	private String title;
	private String content;
	private String imageName;
	private Date date;
	private CategoryDto catagory;
	private UserDto user;
	private Set<CommentDto>comment=new HashSet<>();
	
	

}
