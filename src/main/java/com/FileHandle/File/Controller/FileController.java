package com.FileHandle.File.Controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FileHandle.File.Service.FileAddingService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
	
	
	@Value("${spring.path}")
	private String path;
	
	@Value("${resume.baseUrl}")
	private String uri;
	
	@Autowired
	private FileAddingService fileService;
	

	
		@PostMapping
		public String addFile( @RequestPart MultipartFile file	) throws IOException {
				
				 String resume = fileService.addResume(path, file);
				 return uri+"//"+resume;
		}
		
		@GetMapping("/{filename}")
		public void getFile(@PathVariable String filename,HttpServletResponse response) throws IOException {
			
			  InputStream resume = fileService.getResume(filename);
			  response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
			  StreamUtils.copy(resume, response.getOutputStream());
		}
		
		@PutMapping("/update/{filename}")
		public String updateFile(@RequestParam String filename, @RequestPart MultipartFile file) throws IOException {
				
			return fileService.updateResume(filename,file);
			  
		}
}
