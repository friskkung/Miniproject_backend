package com.Watcharakorn.timeTableSheduler.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Watcharakorn.timeTableSheduler.model.Course;
import com.Watcharakorn.timeTableSheduler.repository.ClassesRepository;
import com.Watcharakorn.timeTableSheduler.repository.CourseRepository;
import com.Watcharakorn.timeTableSheduler.repository.DayRepository;
import com.Watcharakorn.timeTableSheduler.repository.SectionRepository;

@RestController
public class CourseController {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	DayRepository dayRepository;
	@Autowired
	ClassesRepository classesRepository;
	@GetMapping("/course")
	public ResponseEntity<Object> getAllCourse(){
		System.out.println("1");
		try {
			System.out.println("2");
			List<Course> courses = courseRepository.findAll();
			System.out.println("3");
			return new ResponseEntity<>(courses,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("4");
			return new ResponseEntity<>("Internal Sever Error",HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	@GetMapping("/course/{id}")
	public ResponseEntity<Object> getCourseById(@PathVariable Integer id){
		System.out.println("1");
		Optional<Course> course = courseRepository.findById(id);
		try {
			System.out.println("2");
			if (course.isPresent()) {
				System.out.println("3");
				return new ResponseEntity<>(course,HttpStatus.OK);
						
			} else {
				System.out.println("4");
				return new ResponseEntity<Object>("Id not found",HttpStatus.BAD_REQUEST);
				
			}
			
		} catch (Exception e) {
			System.out.println("5");
			return new ResponseEntity<>("Internal Sever Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/course/{name}")
	public ResponseEntity<Object> getCourseByName(@PathVariable String name){
		List<Course> courses = courseRepository.findByCourseName(name);
		try {
			if (!courses.isEmpty()) {
				return new ResponseEntity<>(courses,HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Id not found",HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Sever Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/course/{code}")
	public ResponseEntity<Object> getCourseByCode(@PathVariable String code){
		List<Course> courses = courseRepository.findByCode(code);
		try {
			if (!courses.isEmpty()) {
				return new ResponseEntity<>(courses,HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Id not found",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>("Name not found",HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/course")
	public ResponseEntity<Object> addCourse(@RequestBody Course body){
		try {
			
			for(int i =0;i< body.getSections().size();i++) {
				for(int j =0;j<body.getSections().get(i).getDays().size();j++) {
					for(int k =0;k<body.getSections().get(i).getDays().get(j).getClasses().size();k++) {
						classesRepository.save(body.getSections().get(i).getDays().get(j).getClasses().get(k));
					}
					dayRepository.save(body.getSections().get(i).getDays().get(j));
				}
				sectionRepository.save(body.getSections().get(i));
			}
			Course course = courseRepository.save(body);
			return new ResponseEntity<>(course,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Wrong Input",HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/course/{id}")
	public ResponseEntity<Object> editCourse(@PathVariable Integer id,@RequestBody Course body){
		Optional<Course> course = courseRepository.findById(id);
		try {
			if (course.isPresent()) {
				for(int i =0;i< body.getSections().size();i++) {
					for(int j =0;j<body.getSections().get(i).getDays().size();j++) {
						for(int k =0;k<body.getSections().get(i).getDays().get(j).getClasses().size();k++) {
							course.get().getSections().get(i).getDays().get(j).getClasses().get(k).setClassTime(body.getSections().get(i).getDays().get(j).getClasses().get(k).getClassTime());
						}
						course.get().getSections().get(i).getDays().get(j).setDayName(body.getSections().get(i).getDays().get(j).getDayName());
					}
					course.get().getSections().get(i).setSectionNumber(body.getSections().get(i).getSectionNumber());
				}
				course.get().setCode(body.getCode());
				course.get().setCourseName(body.getCourseName());
				course.get().setCredit(body.getCredit());
				courseRepository.save(course.get());
				return new ResponseEntity<>(course.get(),HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Id not found",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Sever Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/course/{id}")
	public ResponseEntity<Object> removeCourse(@PathVariable Integer id){
		Optional<Course> course = courseRepository.findById(id);
		try {
			if (course.isPresent()) {
				courseRepository.deleteById(id);
				return new ResponseEntity<>("Deleted!",HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Id not found",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Sever Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
