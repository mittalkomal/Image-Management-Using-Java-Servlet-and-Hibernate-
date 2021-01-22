package com.nagarro.imageManagement.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.imageManagement.service.ImageManagementService;
import com.nagarro.imageManagement.serviceImpl.ImageManagementImpl;

/**
 * Servlet implementation class EditImage
 */
@WebServlet("/EditImage")
public class EditImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
		String newName=request.getParameter("newName");
		String id=request.getParameter("imageId");
		ImageManagementService service=new ImageManagementImpl();
		service.updateImageName(id, newName);
		response.sendRedirect(request.getContextPath() + "/ImageManagement.jsp");
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
			
	}

}
