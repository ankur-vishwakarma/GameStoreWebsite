package com.gamestore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.dao.UserDAO;
import com.gamestore.entity.users;

public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	//private RequestDispatcher requestDispatcher;
	
	public UserServices(HttpServletRequest request,HttpServletResponse response) {
		this.request=request;
		this.response=response;
		
		userDAO=new UserDAO();
	}
	public void listUser() throws ServletException, IOException{
		listUser(null);
	}
	
	public void listUser(String message) throws ServletException, IOException {
		List<users> listUsers=userDAO.listAll();
		
		request.setAttribute("listUsers", listUsers);
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
		
		String listPage="user_list.jsp";
		RequestDispatcher requestDispatcher= request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void createUser() throws ServletException, IOException {
		String email= request.getParameter("email");
		String fullName= request.getParameter("fullname");
		String password= request.getParameter("password");
		
		users existUser = userDAO.findByEmail(email);
		
		if(existUser!=null) {
			String message ="Could not create user. A user with email " 
										+ email + " already exists";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		else {
		users newUser = new users(email, fullName, password);
		userDAO.create(newUser);
		listUser("New user created succesfully!");
		}
	}
	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		users user = userDAO.get(userId);
		
		String editPage="user_form.jsp";
		request.setAttribute("user", user);
		
		RequestDispatcher requestDispatcher= request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}
	public void updateUser() throws ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		users userById = userDAO.get(userId);

		users userByEmail=userDAO.findByEmail(email);
		
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message ="Could not update user. User with email " + email + " already exists";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}
		else {	
			users user =new users(userId, email, fullName, password);
			userDAO.update(user);
		
			String message="User has been updated succesfully!";
			listUser(message);
		}
	}
	public void deleteUser() throws ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("id"));
		userDAO.delete(userId);
		
		String message="User has been deleted succesfully!";
		listUser(message);
	}
	
	public void login() throws ServletException, IOException {
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		boolean loginResult= userDAO.checkLogin(email, password);
		
		if(loginResult) {
			//System.out.println("true");
			
			request.getSession().setAttribute("useremail" , email);
			
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("/admin/");
			requestDispatcher.forward(request, response);
		}
		else {
			//System.out.println("false");
			String message="Login Failed!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
