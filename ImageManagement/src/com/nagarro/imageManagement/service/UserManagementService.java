package com.nagarro.imageManagement.service;

import com.nagarro.imageManagement.Exceptions.UserNotFoundException;
import com.nagarro.imageManagement.model.User;

public interface UserManagementService {

	/*
	 * Method used to authenticate user from the database
	 * 
	 * @param username
	 * 
	 * @param password
	 * 
	 * @returns User object
	 * 
	 * @throws UserNotFoundExceptionF
	 */
	User authenticateLogin(String username, String password) throws UserNotFoundException;
}
