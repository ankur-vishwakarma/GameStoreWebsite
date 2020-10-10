package com.gamestore.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class CustomerLoginFilter implements Filter {

	private static final String[] loginRequiredURLs = {
			"/view_profile", "/edit_profile", "/update_profile", "/write_review",
			"/checkout", "place_order"
	};
    public CustomerLoginFilter() {
    }
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		if(path.startsWith("/admin/")) {  //to avoid touching admin
			chain.doFilter(request, response);
			return;
		}
		
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") !=null;
		
		//System.out.println(loggedIn + " " + path);
		String requestURL = httpRequest.getRequestURI().toString();
		
		if(!loggedIn && isLoginRequired(requestURL)) {  //not logged in ptries to view profile
			
			String queryString =httpRequest.getQueryString();
			String redirectURL = requestURL;
			
			if(queryString != null) {
				redirectURL= redirectURL.concat("?").concat(queryString);
			}
			
			session.setAttribute("redirectURL", redirectURL);  //so tht we dont send to profile page 
			//whn customer ws doing something specific we need to send him there
			
			String loginPage = "frontend/login.jsp";
			RequestDispatcher dispatcher= httpRequest.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}

	}
	
	private boolean isLoginRequired(String requestURL) {
		for(String loginRequiredURL : loginRequiredURLs) {
			if(requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
