package com.rakesh.blog.playlods;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	private Integer categoryId;
	
	 @NotBlank(message="Sould not be blank")
	 @Size(min = 5,message="Should be equal to 5 or more than 5")
	private String categoryTitle;
	@NotBlank
	private String categoryDescription;

}
