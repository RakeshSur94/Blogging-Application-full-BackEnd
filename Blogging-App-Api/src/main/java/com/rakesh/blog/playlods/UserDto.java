package com.rakesh.blog.playlods;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private Integer user_id;
	@NotEmpty(message = "UserName ShouldNot be blanked")
	@Size(max = 15 ,min = 5, message = "Name shoud be min 5 char and maximum 15 char")
	private String user_name;
	@Size(min = 5,max = 10, message = "Password must be with in 5 to 10 chracter")
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

}
