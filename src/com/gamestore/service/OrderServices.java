package com.gamestore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.controller.frontend.shoppingcart.ShoppingCart;
import com.gamestore.dao.OrderDAO;
import com.gamestore.entity.Customer;
import com.gamestore.entity.Game;
import com.gamestore.entity.GameOrder;
import com.gamestore.entity.OrderDetail;

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
	
	public void listAllOrder() throws ServletException, IOException{
		listAllOrder(null);
	}
	public void listAllOrder(String message) throws ServletException, IOException {
		List<GameOrder> listOrder = orderDao.listAll();
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
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

	public void showCheckoutForm() throws ServletException, IOException {
		String checkOutPage="frontend/checkout.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(checkOutPage);
		dispatcher.forward(request, response);
	}

	public void placeOrder() throws ServletException, IOException {
		String recipientName=request.getParameter("recipientName");
		String recipientPhone=request.getParameter("recipientPhone");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String zipcode=request.getParameter("zipcode");
		String country=request.getParameter("country");
		String paymentMethod=request.getParameter("paymentMethod");
		
		String shippingAddress = address + ", " + city + ", " + zipcode + ", " + country;
		
		GameOrder order=new GameOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		
		HttpSession session=request.getSession();
		Customer customer=(Customer) session.getAttribute("loggedCustomer");
		order.setCustomer(customer);
		
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("cart");
		Map<Game, Integer> items=shoppingCart.getItems();
		
		Iterator<Game> iterator=items.keySet().iterator();
		
		Set<OrderDetail> orderDetails=new HashSet<>();
		
		while(iterator.hasNext()) {
			Game game=iterator.next();
			Integer quantity=items.get(game);
			float subtotal=quantity * game.getPrice();
			
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setGame(game);
			orderDetail.setGameOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			
			orderDetails.add(orderDetail);
		}
		
		order.setOrderDetails(orderDetails);
		order.setTotal(shoppingCart.getTotalAmount());
		
		orderDao.create(order);
		
		shoppingCart.clear(); //clear cart as order placed
		
		String message="Your order have been recieved. Thanks for shopping with GameStore.";
		request.setAttribute("message", message);
		
		String messagePage="frontend/message.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}

	public void listOrderByCustomer() throws ServletException, IOException {
		HttpSession session=request.getSession();
		Customer customer=(Customer) session.getAttribute("loggedCustomer");
		List <GameOrder> listOrders = orderDao.listByCustomer(customer.getCustomerId());
		
		request.setAttribute("listOrders", listOrders);
		
		String historyPage="frontend/order_list.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(historyPage);
		dispatcher.forward(request, response);
	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session =request.getSession();
		Customer customer =(Customer) session.getAttribute("loggedCustomer");

		GameOrder order = orderDao.get(orderId,customer.getCustomerId());
		request.setAttribute("order", order);
		
		String detailPage="frontend/order_detail.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(detailPage);
		dispatcher.forward(request, response);
	}

	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		
		
		HttpSession session =request.getSession();
		Object isPendingGame = session.getAttribute("NewGamePendingToAddToOrder");
		
		if(isPendingGame==null) {
			GameOrder order=orderDao.get(orderId);
			session.setAttribute("order", order);
		}else {
			session.removeAttribute("NewGamePendingToAddToOrder");
		}
		
		
		String editPage="order_form.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateOrder() throws ServletException, IOException {
		HttpSession session= request.getSession();
		GameOrder order=(GameOrder) session.getAttribute("order");
		
		//general info
		String recipientName=request.getParameter("recipientName");
		String recipientPhone=request.getParameter("recipientPhone");
		String shippingAddress=request.getParameter("shippingAddress");
		String paymentMethod=request.getParameter("paymentMethod");
		String orderStatus=request.getParameter("orderStatus");
		
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		order.setStatus(orderStatus);
		
		//order details
		String [] arrayGameId=request.getParameterValues("gameId");
		String [] arrayPrice=request.getParameterValues("price");
		String [] arrayQuantity=new String[arrayGameId.length];
		
		for(int i=1;i<=arrayQuantity.length;i++) {
			arrayQuantity[i -1]= request.getParameter("quantity"+i);
		}
		
		Set<OrderDetail> orderDetails=order.getOrderDetails();
		orderDetails.clear();
		
		float totalAmount=0.0f;
		for(int i=0;i<arrayGameId.length;i++) {
			int gameId=Integer.parseInt(arrayGameId[i]);
			int quantity=Integer.parseInt(arrayQuantity[i]);
			float price=Float.parseFloat(arrayPrice[i]);
			
			float subTotal = price*quantity;
			
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setGame(new Game(gameId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subTotal);
			orderDetail.setGameOrder(order);
			
			orderDetails.add(orderDetail);
			
			totalAmount+=subTotal;
		}
		
		order.setTotal(totalAmount);
		
		orderDao.update(order);
		
		String message="The order " + order.getOrderId() + " has been updated sucessfully.";
		
		listAllOrder(message);
	}

	public void deleteOrder() throws ServletException, IOException {
		Integer orderId=Integer.parseInt(request.getParameter("id"));
		orderDao.delete(orderId);
		
		String message="The order ID " + orderId + " has been deleted sucessfully.";
		
		listAllOrder(message);
	}
	
}
