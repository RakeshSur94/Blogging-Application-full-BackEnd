package com.rakesh.blog.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rakesh.blog.security.entity.User_Details;
import com.rakesh.blog.security.repository.IUser_DetailsRepository;
@Service
public class UserUserDetailsService implements UserDetailsService {
	@Autowired
	private IUser_DetailsRepository user_DetailsRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User_Details>userInfo=	user_DetailsRepository.findByName(username);
		
		return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User name not Found"+username));
	}
	
	public String saveUser(User_Details user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return "user is save" +user_DetailsRepository.save(user);
	}

}
