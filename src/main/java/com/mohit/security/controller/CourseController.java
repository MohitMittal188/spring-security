package com.mohit.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.security.model.Course;
import com.mohit.security.model.Response;
import com.mohit.security.service.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@CrossOrigin
	@PostMapping("/course/add")
	ResponseEntity<Response> addCourse(@RequestBody Course course){
		
		boolean addCourse = courseService.addCourse(course);
		
		ResponseEntity<Response> resEntity;
		Response res;
			
		if(addCourse) {
			res = new Response("success",null);
			resEntity = new ResponseEntity<>(res,HttpStatus.OK);
		}else {
			res = new Response("failed",null);
			resEntity = new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		}
		
		return resEntity;
	}
	
	@CrossOrigin
	@GetMapping("/courses")
	ResponseEntity<List<Course>> getCourses(@RequestParam(required=false) Integer start,
											@RequestParam(required=false) Integer size){
		
		List<Course> courses = courseService.getCourses(start, size);
		
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
}
