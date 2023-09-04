package com.rakesh.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rakesh.blog.playlods.ApiResponse;

@ControllerAdvice
public class HttpRequestMethodNotSupportedExceptionHandler {
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
	  	ApiResponse response=new ApiResponse(ex.getMessage(), false);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
		
	}
	

}
