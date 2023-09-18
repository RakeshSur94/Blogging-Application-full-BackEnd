package com.rakesh.blog.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rakesh.blog.rest.UserSecurityController;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;



/*import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;*/

@Configuration	
@SecurityScheme(name = "bearerAuth",
                                  description = "JWT Auth description",
                                 scheme = "bearer" ,
                                 type = SecuritySchemeType.HTTP,
                                 bearerFormat = "JWT",
                                 in =SecuritySchemeIn.HEADER)

public class SwaggerApiDocsConfig {
	//public static final String AUTHORIZATION_HEADER="Authorization";
	/*
	 * SPRING FOX SWAGGER CODE WHICH IS DEPRICATED IN SPRING BOOT 3.X SO USING OPEN API
	 private ApiKey createApiKey() {
	 
		return new ApiKey("jwt", AUTHORIZATION_HEADER, "header");
	}
	private List<SecurityContext> createSecurityContext(){
		
		return Arrays.asList(SecurityContext.builder().securityReferences(createSecurityReference()).build());
	}

	private List<SecurityReference> createSecurityReference() {
		AuthorizationScope scopes=new AuthorizationScope("global", "Acess Everything");
		
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scopes}));
	}
	@Bean
	public Docket docketApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(createSecurityContext())
				.securitySchemes(Arrays.asList(createApiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getInfo() {
		
		return new ApiInfo("Blogging Application", "This project is devloped by Rakesh Sur", "1.0", "Terms of Service",
				new Contact("Rakesh Sur", "www.atfoods.com", "rakeshsur72@gmail.com"), "Licsence of API",
				"Api license url", Collections.emptyList());
	}
	*/
	
	//OPEN API CODE
	@Bean
	public  OpenAPI creOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Blogging Api")
						.description("Its a Bolgging api where a user can create post,comment on post, and categories his/her post")
						.contact(new Contact().name("Rakesh Sur").email("rakeshsur72@gmail.com").url("https://github.com/RakeshSur94"))
						.version("3.1").license(new License().name("Clik here to go to GitHub").url("https://github.com/RakeshSur94/Blogging-Application-full-BackEnd")))
				.externalDocs(new ExternalDocumentation()
						.description("Blogging api documentation")
						.url("https://github.com/RakeshSur94/Blogging-Application-full-BackEnd"));
	}
			
	
	
	

}
