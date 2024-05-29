package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Course;

public class CourseDAO {
	public static List<Course> listCourse(HttpServletRequest request, Connection conn, int coursecategoryId){
		List<Course> courseList = new ArrayList<Course>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select course.courseId, course.courseName, course.courseURL,coursecategory.coursecategoryFolder,course.describe,course.createdAt from course "
				+ "inner join coursecategory on course.coursecategoryId = coursecategory.coursecategoryId "
				+ "where coursecategory.coursecategoryId = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, coursecategoryId);
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
			request.setAttribute("messageErrorReturnListCourse", e.getMessage());
		}
		return courseList;
	}
	
	public static Course FindCourseId(HttpServletRequest request, Connection conn, int courseId) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select course.courseId, course.courseName, course.courseURL,coursecategory.coursecategoryFolder,course.describe,course.createdAt from course "
				+ "inner join coursecategory on course.coursecategoryId = coursecategory.coursecategoryId "
				+ "where course.courseId = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, courseId);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course.courseId"));
				course.setCourseName(rs.getString("course.courseName"));
				course.setCourseCategoryFolder(rs.getString("coursecategory.coursecategoryFolder"));
				course.setCourseURL(rs.getString("course.courseURL"));
				course.setDescribe(rs.getString("course.describe"));
				course.setCreatedAt(rs.getString("course.createdAt"));
				return course;
			}
		} catch (SQLException e) {
			request.setAttribute("messageErrorFindCourseId", e.getMessage());
		}
		return null;
	}
	
	public static List<Course> latestCreatedCourseList(HttpServletRequest request, Connection conn, int top) {
		List<Course> courseList = new ArrayList<Course>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT course.courseId, course.courseName, course.courseURL,coursecategory.coursecategoryFolder, course.describe, course.createdAt "
					+"FROM course "
					+"inner join coursecategory on course.coursecategoryId = coursecategory.coursecategoryId "
					+"order by course.createdAt desc "
					+"limit ?";
		
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, top);
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
			request.setAttribute("messageErrorReturnLatestCreatedCourseList", e.getMessage());
		}
		return courseList;
	}
}
