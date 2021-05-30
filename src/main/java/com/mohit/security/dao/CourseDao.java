package com.mohit.security.dao;

import java.util.List;

import com.mohit.security.model.Course;

public interface CourseDao {
	
	Boolean addCourse(Course course);
	List<Course> getCourses(Integer start,Integer size);
}
