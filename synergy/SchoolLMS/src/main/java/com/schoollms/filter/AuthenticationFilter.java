package com.schoollms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Authentication Filter
 * Protects pages requiring authentication
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        
        // Public resources that don't require authentication
        boolean isPublicResource = uri.endsWith(".css") || 
                                    uri.endsWith(".js") || 
                                    uri.endsWith(".png") || 
                                    uri.endsWith(".jpg") ||
                                    uri.endsWith(".jpeg") ||
                                    uri.endsWith(".svg") ||
                                    uri.equals(contextPath + "/") ||
                                    uri.equals(contextPath + "/index.html") ||
                                    uri.equals(contextPath + "/login") ||
                                    uri.equals(contextPath + "/login.html") ||
                                    uri.equals(contextPath + "/register") ||
                                    uri.equals(contextPath + "/register.html");
        
        if (isPublicResource) {
            chain.doFilter(request, response);
            return;
        }
        
        // Check if user is logged in
        if (session == null || session.getAttribute("user") == null) {
            if (uri.startsWith(contextPath + "/api/")) {
                // API requests should return 401
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                // Redirect to login page
                httpResponse.sendRedirect(contextPath + "/login.html");
            }
            return;
        }
        
        // Role-based access control
        String role = (String) session.getAttribute("role");
        
        if (uri.startsWith(contextPath + "/admin/") && !"ADMIN".equals(role)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        
        if (uri.startsWith(contextPath + "/teacher/") && !"TEACHER".equals(role)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        
        if (uri.startsWith(contextPath + "/student/") && !"STUDENT".equals(role)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
