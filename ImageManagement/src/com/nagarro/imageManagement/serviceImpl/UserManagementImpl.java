package com.nagarro.imageManagement.serviceImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nagarro.imageManagement.Exceptions.UserNotFoundException;
import com.nagarro.imageManagement.model.User;
import com.nagarro.imageManagement.service.UserManagementService;
import com.nagarro.imageManagement.util.HibernateUtil;

public class UserManagementImpl implements UserManagementService {

	@Override
	public User authenticateLogin(String username, String password) throws UserNotFoundException {
		try {

			SessionFactory sessFact = HibernateUtil.getSessionFactory();
			Session session = sessFact.getCurrentSession();
			org.hibernate.Transaction tr = session.beginTransaction();
			Query query = session.createQuery("from User where username=:username AND password=:password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			User user = (User) query.uniqueResult();
			tr.commit();
			if (user == null) {
				throw new UserNotFoundException("Credentials does not match from the database .Please try again");
			}
			return user;
		} catch (Exception e) {
			throw new UserNotFoundException("Unexpected error has occured... .Please try again");
		}

	}

}
