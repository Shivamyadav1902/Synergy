package com.schoollms.model;

import java.sql.Timestamp;

/**
 * Assignment Model Class
 * Represents an assignment in a course
 */
public class Assignment {
    private int assignmentId;
    private int courseId;
    private String title;
    private String description;
    private Timestamp dueDate;
    private int totalMarks;
    private int createdBy;
    private Timestamp createdAt;
    
    // For display purposes
    private String courseName;
    private String creatorName;
    
    // Constructors
    public Assignment() {}
    
    public Assignment(int courseId, String title, String description, Timestamp dueDate, int totalMarks, int createdBy) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.totalMarks = totalMarks;
        this.createdBy = createdBy;
    }
    
    // Getters and Setters
    public int getAssignmentId() {
        return assignmentId;
    }
    
    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Timestamp getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }
    
    public int getTotalMarks() {
        return totalMarks;
    }
    
    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
    
    public int getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCreatorName() {
        return creatorName;
    }
    
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    
    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId=" + assignmentId +
                ", courseId=" + courseId +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", totalMarks=" + totalMarks +
                '}';
    }
}
