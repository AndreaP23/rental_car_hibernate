package com.jwt.hibernate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.dao.UserDAO;

import org.mindrot.jbcrypt.BCrypt;

public class UserControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String telefono = request.getParameter("telefono");
		String dataNascita = request.getParameter("dataNascita");

		HttpSession session = request.getSession(true);
		try {
			UserDAO userDAO = new UserDAO();
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			userDAO.addUserDetails(nome,cognome,hashedPassword,email,telefono,dataNascita);
			response.sendRedirect("register.jsp?success=true");
		} catch (Exception e) {
			response.sendRedirect("register.jsp?success=false");
		}

	}
}