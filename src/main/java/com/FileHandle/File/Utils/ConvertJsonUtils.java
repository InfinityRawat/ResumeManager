package com.FileHandle.File.Utils;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FileHandle.File.DTO.ApplicantDto;
import com.FileHandle.File.Exception.ValidationViolationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;


@Service
public class ConvertJsonUtils {
	
	@Autowired
    private Validator validator; 
	
		public ApplicantDto convertToObject(String applicant) throws JsonMappingException, JsonProcessingException {
			ApplicantDto convertValue=null;
			ObjectMapper map = new ObjectMapper();
			convertValue = map.readValue(applicant,ApplicantDto.class);
			
			Set<ConstraintViolation<ApplicantDto>> validate = validator.validate(convertValue);
			
			if(!validate.isEmpty()) {
//				append all validation error we got from validator
				StringBuilder errors = new StringBuilder();
	            for (ConstraintViolation<ApplicantDto> violation : validate) {
	                errors.append(violation.getMessage()).append("\n");
	            }
	            throw new ValidationViolationException("Validation failed: " + errors.toString()); 
			}
						
			 return convertValue;

		}
}
