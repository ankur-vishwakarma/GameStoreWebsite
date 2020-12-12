package com.gamestore.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.dao.CustomerDAO;
import com.gamestore.entity.Customer;

public class CustomerServices {
	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDAO=new CustomerDAO();
	}
	
	public void listCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDAO.listAll();
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listCustomer", listCustomer);
		
		String listPage="customer_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}
	
	public void createCustomer() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		
		if(existCustomer!= null) {
			String message="Could not create new customer: the email "
					+ email + " is already registered by another customer.";
			listCustomers(message);
		}else {
			Customer newCustomer=new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.create(newCustomer);
			
			String message="New Customer has been created successfully.";
			listCustomers(message);
			
		}
	}
	
	private void updateCustomerFieldsFromForm(Customer customer) {
		String email=request.getParameter("email");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		String addressLine1=request.getParameter("address1");
		String addressLine2=request.getParameter("address2");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zipCode=request.getParameter("zipCode");
		String country=request.getParameter("country");
		
		if(email!=null && !email.equals("")) {
			customer.setEmail(email);
		}
		
		customer.setFirstname(firstname);
		customer.setLastname(lastname);
		
		if(password!=null && !password.equals("")) {
			customer.setPassword(password);
		}
		
		customer.setPhone(phone);
		customer.setAddressLine1(addressLine1);
		customer.setAddressLine2(addressLine2);
		customer.setCity(city);
		customer.setState(state);
		customer.setCustomercol(zipCode);
		customer.setCountry(country);
	}
	
	public void registerCustomer() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		String message="";
		if(existCustomer!= null) {
			message="Could not register. The email "
					+ email + " is already registered by another customer.";
		}else {
			Customer newCustomer=new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			
			customerDAO.create(newCustomer);
			
			message="You have registered successfully. Thank you!<br/>"
					+ "<a href='login'>Click here</a> to login.";
			
		}
		
		String messagePage="frontend/message.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	public void editCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		Customer customer=customerDAO.get(customerId);
		
		request.setAttribute("customer", customer);
		
		CommonUtility.generateCountryList(request);
		
		String editPage="customer_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		
		Customer customerByEmail=customerDAO.findByEmail(email);
		
		String message=null;
		
		if(customerByEmail!=null && customerByEmail.getCustomerId() != customerId) {
			message="Could not update the customer ID " + customerId
					+ " because there's an existing customer having the same mail.";
			
		}else {
			
			Customer customerById= customerDAO.get(customerId);
			updateCustomerFieldsFromForm(customerById);
			
			customerDAO.update(customerById);
			
			message="Customer has been updated successfully.";
		}
		listCustomers(message);
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId= Integer.parseInt(request.getParameter("id"));
		customerDAO.delete(customerId);
		
		String message="The customer has been deleted successfully.";
		listCustomers(message);
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage="frontend/login.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(loginPage);
		requestDispatcher.forward(request, response);
	}

	public void doLogin() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		if(customer==null) {
			String message="Login Failed. Please check your credentials!";
			request.setAttribute("message", message);
			showLogin();  // redirect to login page
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("loggedCustomer", customer);
			
			Object objRedirectURL = session.getAttribute("redirectURL");
			
			if(objRedirectURL!=null) {
				String redirectURL=(String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);   //lets send him for what he login
			}else {
				showCustomerProfile();
			}
		}
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage="frontend/customer_profile.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(profilePage);
		requestDispatcher.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		CommonUtility.generateCountryList(request);
		 
		String editPage="frontend/edit_profile.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");  //used session as logged in
		updateCustomerFieldsFromForm(customer); //we already have this method while refractor
		customerDAO.update(customer);
		
		showCustomerProfile();  //redirect to customer profile page
		
	}

	public void newCustomer() throws ServletException, IOException {
		CommonUtility.generateCountryList(request);
		
		String customerForm="customer_form.jsp";
		request.getRequestDispatcher(customerForm).forward(request, response);
	}

	
	public void showCustomerRegistrationForm() throws ServletException, IOException {
		CommonUtility.generateCountryList(request);
		
		String registerForm = "frontend/register_form.jsp";
		RequestDispatcher dispatcher= request.getRequestDispatcher(registerForm);
		dispatcher.forward(request, response);
	}
	
}
