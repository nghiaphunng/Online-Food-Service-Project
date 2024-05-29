package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Course;

public class SearchDAO {
	public static List<Course> searchNameCourseList(HttpServletRequest request, Connection conn, String key) {
		List<Course> courseList = new ArrayList<Course>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT course.courseId, course.courseName, course.courseURL,coursecategory.coursecategoryFolder, course.describe, course.createdAt "
					+"FROM course "
					+"inner join coursecategory on course.coursecategoryId = coursecategory.coursecategoryId "
					+"where course.courseName like ?";
		
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, "%" + key + "%");
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course.courseId"));
				course.setCourseName(rs.getString("course.courseName"));
				course.setCourseCategoryFolder(rs.getString("coursecategory.coursecategoryFolder"));
				course.setCourseURL(rs.getString("course.courseURL"));
				course.setDescribe(rs.getString("course.describe"));
				course.setCreatedAt(rs.getString("course.createdAt"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			request.setAttribute("messageErrorSearchNamCourseList", e.getMessage());
		}
		return courseList;
	}
}
