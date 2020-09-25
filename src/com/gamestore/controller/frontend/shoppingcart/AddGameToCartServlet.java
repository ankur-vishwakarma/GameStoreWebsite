package com.gamestore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.dao.GameDAO;
import com.gamestore.entity.Game;

@WebServlet("/add_to_cart")
public class AddGameToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddGameToCartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer gameId=Integer.parseInt(request.getParameter("game_id"));
		
		Object cartObject =request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart=null;
		
		if(cartObject!=null && cartObject instanceof ShoppingCart) { //when cart already in session just typecast
			shoppingCart=(ShoppingCart) cartObject;
		}else {//we should create a new shopping cart
			shoppingCart=new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
			
		}
		
		//as shown in the diagram we need to get all the details of the game so lets fetch game object by id
		GameDAO gameDAO=new GameDAO();
		Game game=gameDAO.get(gameId);
		
		shoppingCart.addItem(game);
		
		//redirect to cart page
		String cartPage=request.getContextPath().concat("/view_cart"); //carts url
		response.sendRedirect(cartPage);
	}

}
