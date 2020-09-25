package com.gamestore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UpdateCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String arrayGameIds[]=request.getParameterValues("gameId");
		String arrayQuantities[]=new String[arrayGameIds.length];
	
		for(int i=1; i<= arrayQuantities.length;i++) {
			String aQuantity=request.getParameter("quantity"+i);  //as in form we pushed using status
			arrayQuantities[i-1]=aQuantity;
		}
		
		int[] gameIds = Arrays.stream(arrayGameIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
		
		ShoppingCart cart=(ShoppingCart) request.getSession().getAttribute("cart");
		cart.updateCart(gameIds, quantities);
		
		String cartPage=request.getContextPath().concat("/view_cart"); //carts url
		response.sendRedirect(cartPage);
	}

}
