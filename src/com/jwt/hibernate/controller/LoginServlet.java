package com.jwt.hibernate.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.dao.UserDAO;
import com.jwt.hibernate.bean.User;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(email, password);
        //System.out.println("EMAIL: " + email + " Password: " + password);

        if (user != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            session = request.getSession(true);
            session.setAttribute("user", user);

            if ("Customer".equals(user.getRuolo())) {
                response.sendRedirect("CustomerServlet"); 
            } else if ("SuperUser".equals(user.getRuolo())) {
                response.sendRedirect("SuperUserServlet"); 
            }
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
