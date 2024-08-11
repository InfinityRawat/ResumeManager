package com.FileHandle.File.DTO;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicantDto {

	private Integer id;
	
	@NotBlank(message = "Please provide name")
	@Length(min = 2, max = 50,message = "name should be more than 2 and less than 50 letter")
	private String name;
	
	private String profileDescription;
	
	@NotBlank(message = "Please provide address")
	@Length(min = 2, max = 50,message = "Address should be more than 2 and less than 50 letter")
	private String address;
	
	private String url;
	
}
