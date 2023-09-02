package com.rakesh.blog.service;

import java.util.List;

import com.rakesh.blog.playlods.UserDto;

public interface IUserService {
	public UserDto createUser(UserDto userDto);
	//public String createUser(UserDto userDto);
	public List<UserDto> getAllUser();
	public UserDto updateUserDto(UserDto userDto,int  userId);
	public UserDto getUserById(int userId);
	public String deleteUser(int userId);

}
