package com.gamestore.controller.frontend.game;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.service.GameServices;


@WebServlet("/view_category")
public class ViewGamesByCategoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

    public ViewGamesByCategoryServlet() {
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameServices gameServices=new GameServices( request, response);
		gameServices.listGamesByCategory();
	}

}
