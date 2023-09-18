package com.rakesh.blog.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.blog.playlods.UserDto;
import com.rakesh.blog.security.JWTAuthRequest;
import com.rakesh.blog.security.JWTAuthResponse;
import com.rakesh.blog.service.IUserService;
import com.rakesh.blog.service.JwtTokenHelper;
import com.rakesh.blog.service.UserDetialsInfoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/auth/")
@Tag(name = "AuthController",description ="Authorization Api!!!")
@SecurityRequirement(name= "bearerAuth")
public class UserSecurityController {
	@Autowired
	private IUserService userService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetialsInfoService userDetailsService;
	


	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest  request) {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = jwtTokenHelper.generateToken(userDetails);
		JWTAuthResponse response=new JWTAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);
		
	}
	public void authenticate(String username,String passowrd) {
		UsernamePasswordAuthenticationToken authenticationManager=new UsernamePasswordAuthenticationToken(username,passowrd);
		try {
		
		this.authenticationManager.authenticate(authenticationManager);
		}
		catch(BadCredentialsException bc) {
			bc.printStackTrace();
			System.out.println(bc.getMessage());
		}
		
		
	}
	
	//register new user
	@PostMapping("/register")
	public ResponseEntity<?> registerNewUser(@RequestBody UserDto  userDto){
		return new ResponseEntity<UserDto>(userService.registerNewUser(userDto), HttpStatus.CREATED); 
	}

}
