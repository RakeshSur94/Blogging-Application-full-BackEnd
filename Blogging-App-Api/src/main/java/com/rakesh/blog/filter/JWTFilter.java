package com.rakesh.blog.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rakesh.blog.service.JwtTokenHelper;
import com.rakesh.blog.service.UserDetialsInfoService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenHelper jwtService;
	@Autowired
	private UserDetialsInfoService userDetialsInfoService;

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		String token=null;
		String username=null;
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username=jwtService.extractUsername(token);
			
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userDetialsInfoService.loadUserByUsername(username);
			if(jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
