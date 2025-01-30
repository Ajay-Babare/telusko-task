package com.telusko.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "file_metadata")
public class FileMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private long size;
    private String type;
    private LocalDateTime uploadTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDateTime getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(LocalDateTime uploadTime) {
		this.uploadTime = uploadTime;
	}
	public FileMetadata(Long id, String name, long size, String type, LocalDateTime uploadTime) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.type = type;
		this.uploadTime = uploadTime;
	}
	public FileMetadata() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Constructors, Getters, and Setters
    
    
    
}
