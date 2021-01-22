package com.nagarro.imageManagement.Exceptions;

public class ImageSizeExceededException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageSizeExceededException(String reason)
	{
		super(reason);
	}

}
