package com.mohit.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.security.dao.CourseDao;
import com.mohit.security.model.Course;

@Service
public class CourseService {

	@Autowired
	CourseDao courseDao;
	
	public Boolean addCourse(Course course) {

		return courseDao.addCourse(course);
	}
	
	public List<Course> getCourses(Integer start,Integer size){
		return courseDao.getCourses(start, size);
	}
}
