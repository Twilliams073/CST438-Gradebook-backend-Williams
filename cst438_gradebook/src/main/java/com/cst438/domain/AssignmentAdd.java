package com.cst438.domain;

import java.sql.Date;

public class AssignmentAdd {
	private String assignmentName;
	private Date dueDate;
	private String courseId;
	
	public String getAssignmentName() {
		return assignmentName;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}