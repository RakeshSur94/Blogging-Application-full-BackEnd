package com.rakesh.blog.security.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.blog.security.entity.User_Details;
import com.rakesh.blog.security.service.UserUserDetailsService;

@RestController
@RequestMapping("/auth")
public class SecurityOpertaionController {
	@Autowired
	private UserUserDetailsService service;
	
	@PostMapping("/save")
	public ResponseEntity<?> createCredential(@RequestBody User_Details user_Details){
	
		return new ResponseEntity<String>(service.saveUser(user_Details), HttpStatus.CREATED);
		
	}

}
