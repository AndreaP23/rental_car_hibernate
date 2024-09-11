package com.jwt.hibernate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.bean.Veicolo;
import com.jwt.hibernate.dao.VeicoloDAO;

public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CustomerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && "Customer".equals(user.getRuolo())) {
            String command = request.getParameter("command");

            if ("LOAD".equals(command)) {
                handleLoad(request, response);
            } else if ("BOOK".equals(command)) {
                handleBooking(request, response);
            } else {
                try {
                    listVeicoli(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServletException(e);
                }
            }
        } else {
            response.sendRedirect("accessoNegato.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); 
    }

    private void listVeicoli(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VeicoloDAO veicoloDAO = new VeicoloDAO();
            List<Veicolo> veicoli = veicoloDAO.getVeicoli();
            request.setAttribute("VEICOLI_LIST", veicoli);
            RequestDispatcher dispatcher = request.getRequestDispatcher("SchermataCustomer.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void handleLoad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String veicoloIdStr = request.getParameter("veicolo_id");

        if (veicoloIdStr != null) {
            try {
                int veicoloId = Integer.parseInt(veicoloIdStr);
                VeicoloDAO veicoloDAO = new VeicoloDAO();
                Veicolo veicolo = veicoloDAO.getVeicoloById(veicoloId);

                if (veicolo != null) {
                    request.setAttribute("VEICOLO_DETAIL", veicolo);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DettaglioVeicolo.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("error.jsp");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void handleBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && "Customer".equals(user.getRuolo())) {
            int userId = user.getId();
            int veicoloId = Integer.parseInt(request.getParameter("veicolo_id"));
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");

            try {
                VeicoloDAO veicoloDAO = new VeicoloDAO();
                veicoloDAO.bookVeicolo(userId, veicoloId, startDate, endDate);
                request.setAttribute("bookingStatus", "success");
                listVeicoli(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("bookingStatus", "error");
                listVeicoli(request, response); 
            }
        } else {
            response.sendRedirect("accessoNegato.jsp");
        }
    }
}
