package com.schoollms.model;

import java.sql.Timestamp;

/**
 * Announcement Model Class
 * Represents an announcement in the system
 */
public class Announcement {
    private int announcementId;
    private String title;
    private String content;
    private Integer courseId;
    private int postedBy;
    private boolean isGlobal;
    private Timestamp createdAt;
    
    // For display purposes
    private String courseName;
    private String posterName;
    
    // Constructors
    public Announcement() {}
    
    public Announcement(String title, String content, int postedBy, boolean isGlobal) {
        this.title = title;
        this.content = content;
        this.postedBy = postedBy;
        this.isGlobal = isGlobal;
    }
    
    // Getters and Setters
    public int getAnnouncementId() {
        return announcementId;
    }
    
    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Integer getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    public int getPostedBy() {
        return postedBy;
    }
    
    public void setPostedBy(int postedBy) {
        this.postedBy = postedBy;
    }
    
    public boolean isGlobal() {
        return isGlobal;
    }
    
    public void setGlobal(boolean global) {
        isGlobal = global;
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
    
    public String getPosterName() {
        return posterName;
    }
    
    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }
    
    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId=" + announcementId +
                ", title='" + title + '\'' +
                ", isGlobal=" + isGlobal +
                ", createdAt=" + createdAt +
                '}';
    }
}
