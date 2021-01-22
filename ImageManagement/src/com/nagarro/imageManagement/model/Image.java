package com.nagarro.imageManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Image")
public class Image {
	@Id
	@GeneratedValue
	@Column(name="id")
	int id;
	
	@Column(name = "image_name")
	String imgName;
	
	@Column(name="image_size")
	Double imgSize;
	
	@Column(name="user_name")
	String username;
	
	@Column(name="image_path")
	String imagePath;
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public Double getImgSize() {
		return imgSize;
	}

	public void setImgSize(Double imgSize) {
		this.imgSize = imgSize;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
