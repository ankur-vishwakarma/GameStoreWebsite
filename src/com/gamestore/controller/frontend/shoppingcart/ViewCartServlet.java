package com.gamestore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.dao.GameDAO;
import com.gamestore.entity.Game;

@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewCartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object cartObject = request.getSession().getAttribute("cart");
		
		if(cartObject==null) {
			ShoppingCart shoppingCart=new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
			
			//testing
			GameDAO gameDAO=new GameDAO();
			Game game1=gameDAO.get(2);
			Game game2=gameDAO.get(12);
			Game game3=gameDAO.get(13);
			
			shoppingCart.addItem(game1);
			
			shoppingCart.addItem(game2);
			shoppingCart.addItem(game2);
		
			shoppingCart.addItem(game3);
		}
		
		
		String cartPage="frontend/shopping_cart.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(cartPage);
		dispatcher.forward(request, response);
	}

}
