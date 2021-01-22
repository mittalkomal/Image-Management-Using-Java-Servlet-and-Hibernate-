package com.nagarro.imageManagement.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import com.nagarro.imageManagement.Exceptions.CRUDFailedException;
import com.nagarro.imageManagement.Exceptions.ImageSizeExceededException;
import com.nagarro.imageManagement.Exceptions.TotalImageSizeExceededException;
import com.nagarro.imageManagement.model.Image;

public interface ImageManagementService {

	/*
	 * Method used to save image for a username
	 * 
	 * @param username
	 * 
	 * @param part
	 * 
	 * @returns Image Image object after entry in database
	 * 
	 * @throws IOException, ImageSizeExceededException,
	 * TotalImageSizeExceededException, CRUDFailedException
	 */
	public Image saveImage(String username, Part part)
			throws IOException, ImageSizeExceededException, TotalImageSizeExceededException, CRUDFailedException;

	/*
	 * Method used to get list of images for username
	 * 
	 * @param username
	 * 
	 * @returns List<Image> object after entry in database
	 * 
	 * @throws CRUDFailedException
	 */
	public List<Image> getImageListForUser(String username) throws CRUDFailedException;

	/*
	 * Method used to get delete image from database
	 * 
	 * @param id of image
	 * 
	 * @param part
	 * 
	 * @throws CRUDFailedException
	 */
	public void deleteImage(String id) throws CRUDFailedException;

	/*
	 * Method used to get delete image from database
	 * 
	 * @param id of image
	 * 
	 * @param newName of image
	 * 
	 * @throws CRUDFailedException
	 */
	public void updateImageName(String id, String newName) throws CRUDFailedException;;

	/*
	 * Method used to get all image size from database
	 * 
	 * @param username
	 * 
	 * @returns Double
	 * 
	 */
	public Double getTotalImageSizeForUsername(String username);

}
