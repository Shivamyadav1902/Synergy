package com.schoollms.servlet;

import com.schoollms.dao.UserDAO;
import com.schoollms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Login Servlet
 * Handles user authentication
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate login
        User user = userDAO.validateLogin(username, password);
        
        if (user != null) {
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("fullName", user.getFullName());
            session.setAttribute("role", user.getRole());
            
            // Send JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            
            String role = user.getRole();
            String dashboardUrl = "";
            
            if ("ADMIN".equals(role)) {
                dashboardUrl = request.getContextPath() + "/admin/dashboard.html";
            } else if ("TEACHER".equals(role)) {
                dashboardUrl = request.getContextPath() + "/teacher/dashboard.html";
            } else if ("STUDENT".equals(role)) {
                dashboardUrl = request.getContextPath() + "/student/dashboard.html";
            }
            
            out.print("{\"success\": true, \"role\": \"" + role + "\", \"redirectUrl\": \"" + dashboardUrl + "\"}");
            out.flush();
        } else {
            // Login failed
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"success\": false, \"message\": \"Invalid username or password\"}");
            out.flush();
        }
    }
}
