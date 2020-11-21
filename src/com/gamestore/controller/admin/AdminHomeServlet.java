package com.gamestore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.dao.CustomerDAO;
import com.gamestore.dao.GameDAO;
import com.gamestore.dao.OrderDAO;
import com.gamestore.dao.ReviewDAO;
import com.gamestore.dao.UserDAO;
import com.gamestore.entity.GameOrder;
import com.gamestore.entity.Review;

@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public AdminHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrderDAO orderDAO=new OrderDAO();
		ReviewDAO reviewDAO=new ReviewDAO();
		UserDAO userDAO=new UserDAO();
		GameDAO gameDAO=new GameDAO();
		CustomerDAO customerDAO=new CustomerDAO();
		
		List<GameOrder> listMostRecentSales = orderDAO.listMostRecentSales();
		List<Review> listMostRecentReviews = reviewDAO.listMostRecentReview();
		
		long totalUsers=userDAO.count();
		long totalGames=gameDAO.count();
		long totalCustomers=customerDAO.count();
		long totalReviews=reviewDAO.count();
		long totalOrders=orderDAO.count();

		
		request.setAttribute("listMostRecentSales", listMostRecentSales);
		request.setAttribute("listMostRecentReviews", listMostRecentReviews);
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalGames", totalGames);
		request.setAttribute("totalCustomers", totalCustomers);
		request.setAttribute("totalReviews", totalReviews);
		request.setAttribute("totalOrders", totalOrders);

		String homepage= "index.jsp";
		//System.out.println("adminservlet");
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
