package com.gamestore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.dao.OrderDAO;
import com.gamestore.entity.GameOrder;

public class OrderServices {
	private OrderDAO orderDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.orderDao=new OrderDAO();
	}
	
	public void listAllOrder() throws ServletException, IOException {
		List<GameOrder> listOrder = orderDao.listAll();
		
		request.setAttribute("listOrder", listOrder);
		
		String listPage="order_list.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		
		GameOrder order = orderDao.get(orderId);
		request.setAttribute("order", order);
		
		String detailPage="order_detail.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(detailPage);
		dispatcher.forward(request, response);
	}
	
}
