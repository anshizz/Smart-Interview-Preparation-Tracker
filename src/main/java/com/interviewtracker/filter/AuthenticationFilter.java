package com.interviewtracker.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;

        // Allow static resources and auth pages
        boolean isStaticResource = uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/") || uri.contains("/assets/");
        boolean isAuthPage = uri.endsWith("login.jsp") || uri.endsWith("register.jsp") || uri.endsWith("LoginServlet") || uri.endsWith("RegisterServlet") || uri.endsWith("/");

        if (loggedIn && (uri.endsWith("login.jsp") || uri.endsWith("register.jsp"))) {
            // Prevent logged-in users from accessing login/register
            res.sendRedirect(req.getContextPath() + "/dashboard/dashboard.jsp");
        } else if (loggedIn || isStaticResource || isAuthPage) {
            chain.doFilter(request, response);
        } else {
            // Redirect unauthenticated users to login
            res.sendRedirect(req.getContextPath() + "/auth/login.jsp");
        }
    }

    @Override
    public void destroy() {}
}
