package com.FileHandle.File.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.FileHandle.File.DTO.ApplicantDto;
import com.FileHandle.File.Entity.ApplicantEntity;
import com.FileHandle.File.Exception.ApplicantNotFoundException;
import com.FileHandle.File.Repository.FileRepository;
import com.FileHandle.File.Utils.ModelMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FileServiceImpl implements FileService{
	
	private FileRepository repo;
	private FileAddingService fileService;
	
	@Value("${spring.path}")
	private String path;
	
	@Value("${resume.baseUrl}")
	private String uri;
	
	private ModelMapper mapUtils;

	

	public FileServiceImpl(FileRepository repo, FileAddingService fileService, ModelMapper map) {
		super();
		this.repo = repo;
		this.fileService = fileService;
		this.mapUtils = map;
	}

	@Override
	public ApplicantDto getApplicantInfo(Integer id) {
		
		ApplicantEntity applicant = repo.findById(id).orElseThrow(()-> new ApplicantNotFoundException("applicant not found in db"));
		 ApplicantDto mapToDto = mapUtils.mapToDto(applicant);
		 String uriString = genereteUrl(applicant.getFileName());
		 mapToDto.setUrl(uriString);
		 mapToDto.setId(id);
		 return mapToDto;
		
	}

	@Override
	public ApplicantDto addApplicant(ApplicantDto dto, MultipartFile resume) {
		ApplicantEntity saveEntity =null;
		 ApplicantDto resp=null;
		try {
			
			String filename = fileService.addResume(path,resume);
			
			 String uriString = genereteUrl(filename);
			
			ApplicantEntity mapToEntity = mapUtils.mapToEntity(dto);
			mapToEntity.setFileName(filename);
			 ApplicantEntity save = repo.save(mapToEntity);
			 dto.setUrl(uriString);
			 dto.setId(save.getId());
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		
		return dto;
	}

	@Override
	public String removeApplicant(Integer id) throws IOException {
		
		ApplicantEntity entity = repo.findById(id).orElseThrow(()->new ApplicantNotFoundException("applicant is not stored in db"));
		
		String removeResume = fileService.removeResume(path+"//"+entity.getFileName());
		repo.delete(entity);
		return "Applicant removed "+removeResume;
	}

	@Override
	public ApplicantDto updateApplicant(Integer id, ApplicantDto dto, MultipartFile resume) throws IOException {
		ApplicantEntity entity = repo.findById(id).orElseThrow(()->new ApplicantNotFoundException("applicant not exist"));
		String location = fileService.updateResume(path, resume);
		
		
		entity.setAddress(dto.getAddress());
		entity.setName(dto.getName());
		entity.setFileName(location);
		entity.setProfileDescription(dto.getProfileDescription());
		
		ApplicantEntity save = repo.save(entity);
		
		 ApplicantDto mapToDto = mapUtils.mapToDto(save);
		 mapToDto.setUrl(location);
		 
		 return mapToDto;
	}

	private String genereteUrl(String filename) {
		
		return ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("file")
        .path("/"+filename)
        .toUriString();
		
	}
	

}
