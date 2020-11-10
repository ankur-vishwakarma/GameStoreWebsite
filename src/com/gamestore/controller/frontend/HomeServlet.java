package com.gamestore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.dao.CategoryDAO;
import com.gamestore.dao.GameDAO;
import com.gamestore.entity.Category;
import com.gamestore.entity.Game;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameDAO gameDAO=new GameDAO();
		
		List<Game> listNewGames=gameDAO.listNewGames();
		List<Game> listBestSellingGames=gameDAO.listBestSellingGames();
		List<Game> listMostFavoredGames=gameDAO.listMostFavoredGames();
		
		request.setAttribute("listNewGames", listNewGames);
		request.setAttribute("listBestSellingGames", listBestSellingGames);
		request.setAttribute("listMostFavoredGames", listMostFavoredGames);

		
		String homepage= "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
