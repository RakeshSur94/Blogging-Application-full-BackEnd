package com.rakesh.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rakesh.blog.model.User;
import com.rakesh.blog.repository.IUserRepository;


@Component
public class UserDetialsInfoService implements UserDetailsService {
	@Autowired
	private IUserRepository userRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userInfo=	userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User name not Found"));
		return userInfo;
		
		
	}
	

	

}
