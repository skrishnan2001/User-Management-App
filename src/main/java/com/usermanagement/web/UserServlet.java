package com.usermanagement.web;

import java.io.*;
import java.util.*;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.usermanagement.dao.UserDAO;
import com.usermanagement.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public UserServlet() {
        this.userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String action = req.getServletPath();

        switch (action) {
            case "/new":
                showNewForm(req, res);
                break;
            case "/insert":
                try {
                    insertUser(req, res);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/delete":
                try {
                    deleteUser(req, res);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/edit":
                try {
                    showEditForm(req, res);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/update":
                try {
                    updateUser(req, res);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                // Handles /list
                try {
                    listUser(req, res);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void listUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, ServletException, IOException {
        List<User> listUser = userDAO.selectAllUsers();
        req.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(req, res);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(req, res);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        User newUser = new User(name, email, phone);
        userDAO.insertUser(newUser);
        res.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);
        res.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user", existingUser);
        dispatcher.forward(req, res);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        User book = new User(id, name, email, phone);
        userDAO.updateUser(book);
        res.sendRedirect("list");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        this.doGet(req, res);
    }
}
