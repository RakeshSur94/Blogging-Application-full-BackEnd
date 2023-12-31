package com.rakesh.blog.playlods;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rakesh.blog.model.Comment;
import com.rakesh.blog.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private Integer user_id;
	@NotEmpty(message = "UserName ShouldNot be blanked")
	@Size(max = 15 ,min = 5, message = "Name shoud be min 5 char and maximum 15 char")
	private String name;
	@Size(min = 5,max = 18, message = "Email must be with in 5 to 10 chracter")
	@Email(message="Please provide a valid email address")
	@Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message="Please provide a valid email address")
	@NotEmpty(message = "Email shoud not be blanked")
	@NotNull
	private String email;
	@NotNull
	@NotEmpty
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")
	private String password;
	@NotNull
	private String about;
	//private List<CommentDto>userComment=new ArrayList<>();
	private Set<RoleDto> roles = new HashSet<>();

}
