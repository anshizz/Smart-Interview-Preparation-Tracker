package com.interviewtracker.controller;

import com.interviewtracker.dao.UserDAO;
import com.interviewtracker.daoimpl.UserDAOImpl;
import com.interviewtracker.model.User;
import com.interviewtracker.utility.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        User user = userDAO.getUserByEmail(email);

        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if ("on".equals(rememberMe)) {
                Cookie emailCookie = new Cookie("rememberedEmail", email);
                emailCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
                response.addCookie(emailCookie);
            } else {
                Cookie emailCookie = new Cookie("rememberedEmail", "");
                emailCookie.setMaxAge(0);
                response.addCookie(emailCookie);
            }

            response.sendRedirect(request.getContextPath() + "/dashboard/dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }
}
