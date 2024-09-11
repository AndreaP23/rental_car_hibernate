package com.jwt.hibernate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.dao.UserDAO;


public class SuperUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperUserServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if (user != null && "SuperUser".equals(user.getRuolo())) {
            try {
                listUtenti(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e); 
            }
        } else {
            response.sendRedirect("accessoNegato.jsp");
        }
    }

    private void listUtenti(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.getUsers(); 
            request.setAttribute("USER_LIST", users); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("SchermataSuperUser.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e); 
        }
    }
}
