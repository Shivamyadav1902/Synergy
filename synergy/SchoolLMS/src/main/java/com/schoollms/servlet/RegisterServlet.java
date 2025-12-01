package com.schoollms.servlet;

import com.schoollms.dao.UserDAO;
import com.schoollms.model.User;
import com.schoollms.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Registration Servlet
 * Handles new user registration
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // Validate inputs
        if (!ValidationUtil.isValidUsername(username)) {
            out.print("{\"success\": false, \"message\": \"Invalid username. Use 3-20 alphanumeric characters.\"}");
            return;
        }
        
        if (!ValidationUtil.isValidEmail(email)) {
            out.print("{\"success\": false, \"message\": \"Invalid email format.\"}");
            return;
        }
        
        if (ValidationUtil.isEmpty(fullName) || ValidationUtil.isEmpty(role)) {
            out.print("{\"success\": false, \"message\": \"All fields are required.\"}");
            return;
        }
        
        // Check if username or email already exists
        if (userDAO.usernameExists(username)) {
            out.print("{\"success\": false, \"message\": \"Username already exists.\"}");
            return;
        }
        
        if (userDAO.emailExists(email)) {
            out.print("{\"success\": false, \"message\": \"Email already registered.\"}");
            return;
        }
        
        // Create new user
        User user = new User(username, password, email, fullName, role);
        user.setPhone(phone);
        
        if (userDAO.createUser(user)) {
            out.print("{\"success\": true, \"message\": \"Registration successful! Please login.\"}");
        } else {
            out.print("{\"success\": false, \"message\": \"Registration failed. Please try again.\"}");
        }
        
        out.flush();
    }
}
