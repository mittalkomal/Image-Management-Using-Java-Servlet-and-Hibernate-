package com.nagarro.imageManagement.Exceptions;

public class TotalImageSizeExceededException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TotalImageSizeExceededException(String reason) {
		super(reason);
	}

}