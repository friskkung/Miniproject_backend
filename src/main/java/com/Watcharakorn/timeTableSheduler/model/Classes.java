package com.Watcharakorn.timeTableSheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Classes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer classId;
	private String classTime;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "day_id")
	private Day day;
	public Classes(String classTime) {
		super();
		this.classTime = classTime;
	}
	public Classes() {
		super();
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	
	
}
