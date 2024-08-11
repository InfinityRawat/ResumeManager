package com.FileHandle.File.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.FileHandle.File.DTO.ApplicantDto;

public interface FileService {
		
		 ApplicantDto getApplicantInfo(Integer id);
		 ApplicantDto addApplicant(ApplicantDto dto, MultipartFile resume);
		 String removeApplicant(Integer id) throws IOException;
		 ApplicantDto updateApplicant(Integer id, ApplicantDto dto, MultipartFile resume) throws IOException;

}
