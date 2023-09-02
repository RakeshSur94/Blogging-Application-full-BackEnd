package com.rakesh.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakesh.blog.exception.ResourceNotFoundException;
import com.rakesh.blog.model.User;
import com.rakesh.blog.playlods.UserDto;
import com.rakesh.blog.repository.IUserRepository;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToUserDto(savedUser);
	}
 
	/*
	  @Override
	   public String createUser(UserDto userDto) { 
		User user=this.dtoToUser(userDto);
		return "saved wtih id"+userRepository.save(user).getUser_id(); 
		
		}
	*/

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> allUserDto = users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
		return allUserDto;

	}

	@Override
	public String deleteUser(int userId) {
	User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
	UserDto userDto=userToUserDto(user);
	this.userRepository.deleteById(userDto.getUser_id());
	return "user is deleted";
	

	}

	@Override
	public UserDto updateUserDto(UserDto userDto, int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		user.setUser_name(userDto.getUser_name());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser = userRepository.save(user);
		UserDto userDto1 = this.userToUserDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		return this.userToUserDto(user);
	}

	public User dtoToUser(UserDto userDto) {
		
		User user = new User();
		user=modelMapper.map(userDto, User.class);
		/*user.setUser_id(userDto.getUser_id());
		user.setUser_name(userDto.getUser_name());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		*/
		return user;
	}

	public UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto=modelMapper.map(user, UserDto.class);
		/*
		userDto.setUser_id(user.getUser_id());
		userDto.setUser_name(user.getUser_name());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		*/
		return userDto;
	}

}
