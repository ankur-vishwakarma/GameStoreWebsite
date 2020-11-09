package com.gamestore.controller.admin.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.dao.GameDAO;
import com.gamestore.entity.Game;
import com.gamestore.entity.GameOrder;
import com.gamestore.entity.OrderDetail;


@WebServlet("/admin/add_game_to_order")
public class AddGameToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddGameToOrderServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gameId=Integer.parseInt(request.getParameter("gameId"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		GameDAO gameDAO=new GameDAO();
		Game game=gameDAO.get(gameId);
		
		HttpSession session=request.getSession();
		GameOrder order=(GameOrder) session.getAttribute("order");
		
		float subtotal=quantity * game.getPrice();
		
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setGame(game);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubtotal(subtotal);
		
		float newTotal=order.getTotal() + subtotal;
		order.setTotal(newTotal);
		
		order.getOrderDetails().add(orderDetail);
		
		request.setAttribute("game", game);
		session.setAttribute("NewGamePendingToAddToOrder",true);
		
		String resultPage="add_game_result.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
