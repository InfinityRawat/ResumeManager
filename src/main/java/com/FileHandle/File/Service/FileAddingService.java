package com.FileHandle.File.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileAddingService {
		
		String addResume(String path, MultipartFile resume) throws IOException;
		String removeResume(String name) throws IOException;
		InputStream getResume(String filename) throws FileNotFoundException;
		String updateResume(String filename, MultipartFile file) throws IOException;
}
