package com.nagarro.imageManagement.Exceptions;

public class UserNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String reason) {
		super(reason);
	}

}
