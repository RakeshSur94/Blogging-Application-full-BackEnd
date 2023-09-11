package com.rakesh.blog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service("fileService")
public class FileOperationServiceImpl implements IFileOperationService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//File name
		String fileName=file.getOriginalFilename();
		//random name generated  file
		String randomID=UUID.randomUUID().toString();
		String filename1=randomID.concat(fileName.substring(fileName.lastIndexOf(".")));
		
		//full path
		String filePath=path + File.separator + filename1;
		
		//create folder if not there
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		//File copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return filename1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path + File.separator +fileName;
		InputStream is=new FileInputStream(fullPath);
		//db logic to return isStream
		return is;
	}

}
