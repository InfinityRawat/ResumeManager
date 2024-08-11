package com.FileHandle.File.Utils;

import org.springframework.stereotype.Service;

import com.FileHandle.File.DTO.ApplicantDto;
import com.FileHandle.File.Entity.ApplicantEntity;

@Service
public class ModelMapper {
	
		public ApplicantDto mapToDto(ApplicantEntity entity) {
			
			return ApplicantDto.builder()
					.id(entity.getId())
					.address(entity.getAddress())
					.name(entity.getName())
					.profileDescription(entity.getProfileDescription())
					.build();
		}
		
	public ApplicantEntity mapToEntity(ApplicantDto entity) {
			
			return ApplicantEntity.builder()
					.address(entity.getAddress())
					.name(entity.getName())
					.profileDescription(entity.getProfileDescription())
					.build();
		}
}
