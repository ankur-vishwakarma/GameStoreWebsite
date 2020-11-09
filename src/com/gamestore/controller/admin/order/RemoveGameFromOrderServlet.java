package com.gamestore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.entity.GameOrder;
import com.gamestore.entity.OrderDetail;

@WebServlet("/admin/remove_game_from_order")
public class RemoveGameFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RemoveGameFromOrderServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gameId=Integer.parseInt(request.getParameter("id"));
		HttpSession session=request.getSession();
		GameOrder order=(GameOrder) session.getAttribute("order");
		
		Set<OrderDetail> orderDetails=order.getOrderDetails();
		Iterator<OrderDetail> iterator=orderDetails.iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail=iterator.next();
			
			if(orderDetail.getGame().getGameId() == gameId) {
				float newTotal=order.getTotal() - orderDetail.getSubtotal();
				order.setTotal(newTotal);
				iterator.remove();
			}
		}
		
		String editOrderFormPage ="order_form.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(editOrderFormPage);
		dispatcher.forward(request, response);
	}

}
