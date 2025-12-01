package com.schoollms.servlet;

import com.schoollms.dao.*;
import com.schoollms.model.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Dashboard Servlet
 * Provides dashboard data for all user roles
 */
@WebServlet("/api/dashboard")
public class DashboardServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        Map<String, Object> dashboardData = new HashMap<>();
        
        if ("ADMIN".equals(role)) {
            // Admin dashboard data
            dashboardData.put("totalStudents", userDAO.getUserCountByRole("STUDENT"));
            dashboardData.put("totalTeachers", userDAO.getUserCountByRole("TEACHER"));
            dashboardData.put("totalCourses", courseDAO.getCourseCount());
            dashboardData.put("recentUsers", userDAO.getAllUsers());
            
        } else if ("TEACHER".equals(role)) {
            // Teacher dashboard data
            dashboardData.put("myCourses", courseDAO.getCoursesByTeacher(user.getUserId()));
            dashboardData.put("totalCourses", courseDAO.getCoursesByTeacher(user.getUserId()).size());
            
        } else if ("STUDENT".equals(role)) {
            // Student dashboard data
            dashboardData.put("enrolledCourses", enrollmentDAO.getEnrollmentsByStudent(user.getUserId()));
            dashboardData.put("totalEnrollments", enrollmentDAO.getEnrollmentsByStudent(user.getUserId()).size());
        }
        
        dashboardData.put("user", user);
        out.print(gson.toJson(dashboardData));
        out.flush();
    }
}
