package com.nagarro.imageManagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.imageManagement.model.User;
import com.nagarro.imageManagement.service.UserManagementService;
import com.nagarro.imageManagement.serviceImpl.UserManagementImpl;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect(request.getContextPath() + "/ImageManagement.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserManagementService service = new UserManagementImpl();
			User user = service.authenticateLogin(username, password);
			if (user != null) {
				response.addCookie(new Cookie("username", username));
				response.addCookie(new Cookie("pass", password));
				response.sendRedirect(request.getContextPath() + "/ImageManagement.jsp");
				response.addCookie(new Cookie("errorMsg", ""));
			} else {
				response.sendRedirect(request.getContextPath() + "/LoginPage.jsp");
			}
		}

		catch (Exception e) {
			response.addCookie(new Cookie("errorMsg","error"));
			response.sendRedirect(request.getContextPath() + "/LoginPage.jsp");

		}
	}

}
