package com.mohit.security.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mohit.security.dao.CourseDao;
import com.mohit.security.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Boolean addCourse(Course course) {
		
		String query = "INSERT INTO course VALUES(:name,:desc,:fees,:minExp,:maxExp)";
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", course.getName());
		param.addValue("desc", course.getDescription());
		param.addValue("fees", course.getFees());
		param.addValue("minExp", course.getMinExp());
		param.addValue("maxExp", course.getMaxExp());
		
		int update = jdbcTemplate.update(query, param);
		
		return update==1?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	public List<Course> getCourses(Integer start, Integer size) {
		
		String query = "select * from course";
		
		return jdbcTemplate.query(query, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course course = new Course();
				course.setName(rs.getString("name"));
				course.setDescription(rs.getString("description"));
				course.setFees(rs.getInt("fees"));
				course.setMinExp(rs.getInt("min_exp"));
				course.setMaxExp(rs.getInt("max_exp"));
				return course;
			}
		});
	}

}
