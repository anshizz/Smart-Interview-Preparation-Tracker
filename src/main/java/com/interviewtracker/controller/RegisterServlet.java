package com.interviewtracker.controller;

import com.interviewtracker.dao.UserDAO;
import com.interviewtracker.daoimpl.UserDAOImpl;
import com.interviewtracker.model.User;
import com.interviewtracker.utility.PasswordUtil;
import com.interviewtracker.utility.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!ValidationUtil.isValidName(name)) {
            request.setAttribute("error", "Invalid Name format.");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidEmail(email)) {
            request.setAttribute("error", "Invalid Email format.");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isStrongPassword(password)) {
            request.setAttribute("error", "Password must be at least 8 characters, include uppercase, lowercase, and a number.");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        if (userDAO.getUserByEmail(email) != null) {
            request.setAttribute("error", "Email is already registered.");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(PasswordUtil.hashPassword(password));
        
        if (userDAO.registerUser(user)) {
            // Auto login after registration
            User registeredUser = userDAO.getUserByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("user", registeredUser);
            response.sendRedirect(request.getContextPath() + "/dashboard/dashboard.jsp");
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
        }
    }
}
