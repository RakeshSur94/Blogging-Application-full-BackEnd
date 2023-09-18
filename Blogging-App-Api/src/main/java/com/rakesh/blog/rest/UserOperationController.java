package com.rakesh.blog.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.blog.playlods.UserDto;
import com.rakesh.blog.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "UserController",description ="create , delete , update,fetch api !!!")
public class UserOperationController {
	@Autowired
	private IUserService userService;
	
	
	//register user
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Operation(summary = "Create new User",description = "This is To register new User User APi")
	                            @ApiResponses(value = {
	                            		 @ApiResponse(responseCode = "200",description = "sucess !!!"),
	                            		 @ApiResponse(responseCode = "401",description = "Authetcation is failed"),
	                            		 @ApiResponse(responseCode = "201",description = "sucess !! but not output")
	                            })                                                                   
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto){
	UserDto createUserDto=	userService.createUser(userDto);
		//String createuserDto=userService.createUser(userDto);
	return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
	}
	//create user
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") int userId){
		UserDto updateUserDto=userService.updateUserDto(userDto, userId);
		return new ResponseEntity<UserDto>(updateUserDto, HttpStatus.OK);
	}
	//delete
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId){
		return new ResponseEntity<String>(userService.deleteUser(userId), HttpStatus.OK);
		//return new ResponseEntity<ApiResponse>(new ApiResponse("sucessfully deleted.........", true));
	}
	//get user
	@GetMapping("/users")
	public  ResponseEntity<?> getAllUsers(){
		List<UserDto> list=userService.getAllUser();
		return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
	}
	//get single user
	@GetMapping("/{userId}")
	public  ResponseEntity<?> fetchAllUserById(@PathVariable("userId") int id){
		UserDto user=userService.getUserById(id);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}

}
