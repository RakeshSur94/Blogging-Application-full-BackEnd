package com.rakesh.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rakesh.blog.config.AppConstant;
import com.rakesh.blog.model.Role;
import com.rakesh.blog.repository.IRoleRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
public class BloggingAppApiApplication implements CommandLineRunner {
	@Autowired
	private IRoleRepository roleRepository;
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role1=new Role();
			role1.setId(AppConstant.ROLE_ADMIN);
			role1.setName("ROLE_ADMIN");
			Role role2=new Role();
			role2.setId(AppConstant.ROLE_USER);
			role2.setName("ROLE_USER");
			List<Role> list = List.of(role1,role2);
			List<Role> saveAll = roleRepository.saveAll(list);
			saveAll.forEach(System.out::println);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}	

	

}
