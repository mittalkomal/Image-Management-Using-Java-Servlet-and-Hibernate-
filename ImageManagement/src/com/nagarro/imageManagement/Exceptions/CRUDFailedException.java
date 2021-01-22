package com.nagarro.imageManagement.Exceptions;

public class CRUDFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CRUDFailedException(String reason) {
		super(reason);

	}

}
