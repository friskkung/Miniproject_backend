package com.Watcharakorn.timeTableSheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Watcharakorn.timeTableSheduler.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findByCodeContaining(String code);
	List<Course> findByCourseNameContaining(String courseName);
	List<Course> findByCredit(Double credit);
}
