package com.schoollms.model;

import java.sql.Timestamp;

/**
 * Submission Model Class
 * Represents a student's assignment submission
 */
public class Submission {
    private int submissionId;
    private int assignmentId;
    private int studentId;
    private String submissionText;
    private String filePath;
    private Timestamp submittedAt;
    private Integer marksObtained;
    private String feedback;
    private Integer gradedBy;
    private Timestamp gradedAt;
    
    // For display purposes
    private String studentName;
    private String assignmentTitle;
    private String graderName;
    private int totalMarks;
    
    // Constructors
    public Submission() {}
    
    public Submission(int assignmentId, int studentId, String submissionText) {
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.submissionText = submissionText;
    }
    
    // Getters and Setters
    public int getSubmissionId() {
        return submissionId;
    }
    
    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }
    
    public int getAssignmentId() {
        return assignmentId;
    }
    
    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getSubmissionText() {
        return submissionText;
    }
    
    public void setSubmissionText(String submissionText) {
        this.submissionText = submissionText;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public Timestamp getSubmittedAt() {
        return submittedAt;
    }
    
    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
    }
    
    public Integer getMarksObtained() {
        return marksObtained;
    }
    
    public void setMarksObtained(Integer marksObtained) {
        this.marksObtained = marksObtained;
    }
    
    public String getFeedback() {
        return feedback;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public Integer getGradedBy() {
        return gradedBy;
    }
    
    public void setGradedBy(Integer gradedBy) {
        this.gradedBy = gradedBy;
    }
    
    public Timestamp getGradedAt() {
        return gradedAt;
    }
    
    public void setGradedAt(Timestamp gradedAt) {
        this.gradedAt = gradedAt;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getAssignmentTitle() {
        return assignmentTitle;
    }
    
    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }
    
    public String getGraderName() {
        return graderName;
    }
    
    public void setGraderName(String graderName) {
        this.graderName = graderName;
    }
    
    public int getTotalMarks() {
        return totalMarks;
    }
    
    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
    
    @Override
    public String toString() {
        return "Submission{" +
                "submissionId=" + submissionId +
                ", assignmentId=" + assignmentId +
                ", studentId=" + studentId +
                ", marksObtained=" + marksObtained +
                '}';
    }
}
