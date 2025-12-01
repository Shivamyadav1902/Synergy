package com.schoollms.dao;

import com.schoollms.model.Course;
import com.schoollms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Course Data Access Object
 */
public class CourseDAO {
    
   public boolean createCourse(Course course) {
        String sql = "INSERT INTO courses (course_code, course_name, description, teacher_id, credits, semester, academic_year) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getCourseName());
            pstmt.setString(3, course.getDescription());
            pstmt.setInt(4, course.getTeacherId());
            pstmt.setInt(5, course.getCredits());
            pstmt.setString(6, course.getSemester());
            pstmt.setString(7, course.getAcademicYear());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Course getCourseById(int courseId) {
        String sql = "SELECT c.*, u.full_name as teacher_name FROM courses c " +
                     "LEFT JOIN users u ON c.teacher_id = u.user_id WHERE c.course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractCourseFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, u.full_name as teacher_name FROM courses c " +
                     "LEFT JOIN users u ON c.teacher_id = u.user_id ORDER BY c.course_name";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(extractCourseFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    public List<Course> getCoursesByTeacher(int teacherId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, u.full_name as teacher_name FROM courses c " +
                     "LEFT JOIN users u ON c.teacher_id = u.user_id WHERE c.teacher_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                courses.add(extractCourseFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    public boolean updateCourse(Course course) {
        String sql = "UPDATE courses SET course_code = ?, course_name = ?, description = ?, " +
                     "teacher_id = ?, credits = ?, semester = ?, academic_year = ? WHERE course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getCourseName());
            pstmt.setString(3, course.getDescription());
            pstmt.setInt(4, course.getTeacherId());
            pstmt.setInt(5, course.getCredits());
            pstmt.setString(6, course.getSemester());
            pstmt.setString(7, course.getAcademicYear());
            pstmt.setInt(8, course.getCourseId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getCourseCount() {
        String sql = "SELECT COUNT(*) FROM courses";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private Course extractCourseFromResultSet(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        course.setCourseCode(rs.getString("course_code"));
        course.setCourseName(rs.getString("course_name"));
        course.setDescription(rs.getString("description"));
        course.setTeacherId(rs.getInt("teacher_id"));
        course.setTeacherName(rs.getString("teacher_name"));
        course.setCredits(rs.getInt("credits"));
        course.setSemester(rs.getString("semester"));
        course.setAcademicYear(rs.getString("academic_year"));
        course.setCreatedAt(rs.getTimestamp("created_at"));
        return course;
    }
}
