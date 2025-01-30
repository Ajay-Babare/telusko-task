package com.telusko.service;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.telusko.entity.FileMetadata; // Replace with your actual package for FileMetadata
import com.telusko.repo.FileMetadataRepository; // Replace with your actual package for the repository

@Service
public class FileStorageService {
    
    private final Path root = Paths.get("uploads");
    
    @Autowired
    private FileMetadataRepository fileMetadataRepository;

    public FileStorageService() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        
        // Reject files with "virus" in the name
        if (fileName != null && fileName.toLowerCase().contains("virus")) {
            return "Security risk! File contains 'virus' keyword and is deleted.";
        }

        // Restrict allowed file types
        String contentType = file.getContentType();
        if (!Arrays.asList("application/pdf","image/png", "image/jpeg", "text/plain").contains(contentType)) {
        	
            return "Security risk! Only PDF, PNG, JPEG, and TXT files are allowed.";
        }

        // Save file locally
        Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        
        // Save metadata
        FileMetadata metadata = new FileMetadata();
        metadata.setName(fileName);
        metadata.setSize(file.getSize());
        metadata.setType(contentType);
        metadata.setUploadTime(LocalDateTime.now());
        fileMetadataRepository.save(metadata);
        
        return "File uploaded successfully!";
    }
}