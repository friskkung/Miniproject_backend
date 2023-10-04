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
public class Day {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer dayId;
	private String dayName;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "day_id")
	private List<Classes> classes;
	public Day(String dayName) {
		super();
		this.dayName = dayName;
	}
	public Day() {
		super();
	}
	public Integer getDayId() {
		return dayId;
	}
	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public List<Classes> getClasses() {
		return classes;
	}
	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	
}
