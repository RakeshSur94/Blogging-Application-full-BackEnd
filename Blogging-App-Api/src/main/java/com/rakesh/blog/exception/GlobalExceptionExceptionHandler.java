package com.rakesh.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rakesh.blog.playlods.ApiResponse;

@ControllerAdvice
public class GlobalExceptionExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException rnfe){
		ApiResponse response=new ApiResponse(rnfe.getMessage(), false);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidExceptio(MethodArgumentNotValidException notvalid){
		Map<String,String>response= new HashMap<>();
		notvalid.getBindingResult().getAllErrors().forEach(error->{
			String fieldName=((FieldError)error).getField();
			String errorMssg=error.getDefaultMessage();
		    response.put(fieldName, errorMssg);
		});
		
		
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
	}

}
