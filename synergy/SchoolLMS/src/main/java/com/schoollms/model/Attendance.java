package com.schoollms.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Attendance Model Class
 * Represents student attendance for a course
 */
public class Attendance {
    private int attendanceId;
    private int courseId;
    private int studentId;
    private Date attendanceDate;
    private String status; // PRESENT, ABSENT, LATE
    private int markedBy;
    private String remarks;
    private Timestamp createdAt;
    
    // For display purposes
    private String studentName;
    private String courseName;
    private String markerName;
    
    // Constructors
    public Attendance() {}
    
    public Attendance(int courseId, int studentId, Date attendanceDate, String status, int markedBy) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.attendanceDate = attendanceDate;
        this.status = status;
        this.markedBy = markedBy;
    }
    
    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }
    
    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public Date getAttendanceDate() {
        return attendanceDate;
    }
    
    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getMarkedBy() {
        return markedBy;
    }
    
    public void setMarkedBy(int markedBy) {
        this.markedBy = markedBy;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getMarkerName() {
        return markerName;
    }
    
    public void setMarkerName(String markerName) {
        this.markerName = markerName;
    }
    
    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                ", attendanceDate=" + attendanceDate +
                ", status='" + status + '\'' +
                '}';
    }
}
