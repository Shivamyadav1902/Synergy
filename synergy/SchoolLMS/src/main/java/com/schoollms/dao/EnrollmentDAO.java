package com.schoollms.dao;

import com.schoollms.model.Enrollment;
import com.schoollms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Enrollment Data Access Object
 */
public class EnrollmentDAO {
    
    public boolean enrollStudent(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, course_id, enrollment_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getStudentId());
            pstmt.setInt(2, enrollment.getCourseId());
            pstmt.setDate(3, enrollment.getEnrollmentDate());
            pstmt.setString(4, enrollment.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.*, c.course_name, u.full_name as student_name FROM enrollments e " +
                     "JOIN courses c ON e.course_id = c.course_id " +
                     "JOIN users u ON e.student_id = u.user_id WHERE e.student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                enrollments.add(extractEnrollmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
    
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.*, c.course_name, u.full_name as student_name FROM enrollments e " +
                     "JOIN courses c ON e.course_id = c.course_id " +
                     "JOIN users u ON e.student_id = u.user_id WHERE e.course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                enrollments.add(extractEnrollmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
    
    public Enrollment getEnrollment(int studentId, int courseId) {
        String sql = "SELECT e.*, c.course_name, u.full_name as student_name FROM enrollments e " +
                     "JOIN courses c ON e.course_id = c.course_id " +
                     "JOIN users u ON e.student_id = u.user_id " +
                     "WHERE e.student_id = ? AND e.course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractEnrollmentFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateEnrollmentStatus(int enrollmentId, String status) {
        String sql = "UPDATE enrollments SET status = ? WHERE enrollment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, enrollmentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean isStudentEnrolled(int studentId, int courseId) {
        String sql = "SELECT COUNT(*) FROM enrollments WHERE student_id = ? AND course_id = ? AND status = 'ACTIVE'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private Enrollment extractEnrollmentFromResultSet(ResultSet rs) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
        enrollment.setStudentId(rs.getInt("student_id"));
        enrollment.setCourseId(rs.getInt("course_id"));
        enrollment.setEnrollmentDate(rs.getDate("enrollment_date"));
        enrollment.setStatus(rs.getString("status"));
        enrollment.setStudentName(rs.getString("student_name"));
        enrollment.setCourseName(rs.getString("course_name"));
        return enrollment;
    }
}
