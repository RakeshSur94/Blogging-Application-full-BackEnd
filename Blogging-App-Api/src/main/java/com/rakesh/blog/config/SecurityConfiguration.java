package com.rakesh.blog.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rakesh.blog.filter.JWTFilter;
import com.rakesh.blog.service.UserDetialsInfoService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	private static final String[] PUBLIC_URLS= {
			"/auth/**",
			"/v3/api-docs",
			//"/v3/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
	};
	@Autowired
	private JWTFilter filter;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetialsInfoService();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(encoder());
		return daoAuthenticationProvider;
	}
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
		
		return http.csrf(csrf->csrf.disable())
				           .authorizeHttpRequests(auth->{
				        	   
//				        	   auth.requestMatchers("/auth/register").permitAll()
//				        	   .requestMatchers("/auth/login").permitAll()
				        	   //auth.requestMatchers(PUBLIC_URLS).permitAll()
				        	  auth .requestMatchers(HttpMethod.GET).permitAll()
				        	   .requestMatchers(HttpMethod.POST).permitAll()
				        	   .anyRequest()
				        	   //.requestMatchers("/api/**")
				           	   .authenticated();
				           })
				           .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                           .authenticationProvider(authenticationProvider())
                           .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                           .build();
				
				
				
				
				
				
				 /*          .authorizeHttpRequests(auth->auth.requestMatchers("auth/login").permitAll())
				           .authorizeHttpRequests(auth->auth.requestMatchers("/auth/save").permitAll())
				           //.authorizeHttpRequests(auth->auth.requestMatchers("/api/**").authenticated())
				           .authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.GET).permitAll())				           
				           .formLogin()
				           .and()
				           .logout()
				           .and()
				           .build();			*/   
		 		 		 					
		
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	

}
