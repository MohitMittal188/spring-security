package com.mohit.security.model;

public class Course {
	
	String name;
	String description;
	Integer fees;
	Integer minExp;
	Integer maxExp;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getFees() {
		return fees;
	}
	public void setFees(Integer fees) {
		this.fees = fees;
	}
	public Integer getMinExp() {
		return minExp;
	}
	public void setMinExp(Integer minExp) {
		this.minExp = minExp;
	}
	public Integer getMaxExp() {
		return maxExp;
	}
	public void setMaxExp(Integer maxExp) {
		this.maxExp = maxExp;
	}
}
