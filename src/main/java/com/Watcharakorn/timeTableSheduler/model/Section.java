package com.Watcharakorn.timeTableSheduler.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sectionId;
	private String sectionNumber;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "section_id")
	private List<Day> days;
	public Section(String sectionNumber) {
		super();
		this.sectionNumber = sectionNumber;
	}
	public Section() {
		super();
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(String sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	public List<Day> getDays() {
		return days;
	}
	public void setDays(List<Day> days) {
		this.days = days;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
