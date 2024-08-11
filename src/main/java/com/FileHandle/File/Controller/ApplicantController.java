package com.FileHandle.File.Controller;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FileHandle.File.DTO.ApplicantDto;
import com.FileHandle.File.Service.FileService;
import com.FileHandle.File.Utils.ConvertJsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/resume-app/v1/applicant")
public class ApplicantController {
	
		private FileService service;
		private ConvertJsonUtils utils;
	

		public ApplicantController(FileService service, ConvertJsonUtils utils) {
			super();
			this.service = service;
			this.utils = utils;
		}

		@PostMapping(path="/save", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
		public ResponseEntity<ApplicantDto> saveApplicant(@RequestPart String applicant, @RequestPart MultipartFile resume) throws JsonMappingException, JsonProcessingException{
			
			ApplicantDto entity = utils.convertToObject(applicant);
			
			ApplicantDto saveApplicant = service.addApplicant(entity, resume);
			
			return new ResponseEntity<ApplicantDto>(saveApplicant, HttpStatus.OK);
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<ApplicantDto> getApplicant(@PathVariable
				Integer id){
			
				ApplicantDto applicantInfo = service.getApplicantInfo(id);
				return new ResponseEntity<ApplicantDto>(applicantInfo,HttpStatus.OK);
		}
		
		@PutMapping(path="/update", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
		public ResponseEntity<ApplicantDto> updateApplicant(@RequestParam Integer id,@RequestPart String applicant, @RequestPart MultipartFile resume) throws IOException{
			
			ApplicantDto entity = utils.convertToObject(applicant);
			ApplicantDto saveApplicant = service.updateApplicant(id,entity, resume);
			
			return new ResponseEntity<ApplicantDto>(saveApplicant, HttpStatus.OK);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<String> removeApplicant(@PathVariable Integer id) throws IOException{
				String applicantInfo = service.removeApplicant(id);
				return new ResponseEntity<String>(applicantInfo,HttpStatus.OK);
		}
}







