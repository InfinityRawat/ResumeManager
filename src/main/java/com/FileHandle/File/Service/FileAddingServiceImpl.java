package com.FileHandle.File.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.FileHandle.File.Repository.FileRepository;
import com.FileHandle.File.Utils.ModelMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileAddingServiceImpl implements FileAddingService {
	

	private FileRepository repo;
	private ModelMapper map;
	
	@Value("${spring.path}")
	private String filePath;

	public FileAddingServiceImpl(FileRepository repo, ModelMapper map) {
		super();
		this.repo = repo;
		this.map = map;
	}

	@Override
	public String addResume(String path,MultipartFile resume) throws IOException {	
		Path filePath = Path.of(path, resume.getOriginalFilename());
//		create dir if not exist
		File file = new File(path);
		
			if(!file.exists()) {
				
				file.mkdir();
			}
		log.info(" content is {} - size is {} Path of file {}",resume.getContentType(),resume.getSize(),filePath);
		long readbytes = Files.copy(resume.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
		 
		 System.out.println(readbytes);		 	
		return resume.getOriginalFilename();
	}
		
	
	@Override
	public InputStream getResume(String fileName) throws FileNotFoundException {
			
		Path path = Path.of(filePath,fileName);
		
		if(!Files.exists(path)) {
			throw new FileNotFoundException("File is not available with file name");
		}
		
		return new FileInputStream(path.toFile());
	}

	@Override
	public String removeResume(String name) throws IOException {
		
		Path path = Path.of(name);
		
		if(Files.exists(path)) {
				Files.deleteIfExists(path);
		}
		
		return "FileRemoved";
	}

	@Override
	public String updateResume(String path, MultipartFile file) throws IOException {
//		removing old Resume
		Path filePath = Path.of(path,file.getOriginalFilename());
		removeResume(filePath.toString());
		
		return addResume(path,file);
	}
	
	

}
