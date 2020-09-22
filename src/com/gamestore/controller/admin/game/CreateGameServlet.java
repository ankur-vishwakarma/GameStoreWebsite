package com.gamestore.controller.admin.game;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.service.GameServices;


@WebServlet("/admin/create_game")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10, // 10 KB
		maxFileSize = 1024 * 30, // 30 KB
		maxRequestSize = 1024 * 1024 // 1 MB
)
public class CreateGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateGameServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameServices gameServices=new GameServices(request, response);
		gameServices.createGame();
	}

}
