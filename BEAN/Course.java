package BEAN;

public class Course {
	private int courseId;
	private String courseName;
	private String courseCategoryFolder;
	private String courseURL;
	private String describe;
	private String createdAt;
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCourseURL() {
		return courseURL;
	}

	public void setCourseURL(String courseURL) {
		this.courseURL = courseURL;
	}
	
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Course(int courseId, String courseName, String courseCategoryFolder, String courseURL, String describe,
			String createdAt) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCategoryFolder = courseCategoryFolder;
		this.courseURL = courseURL;
		this.describe = describe;
		this.createdAt = createdAt;
	}

	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCategoryFolder() {
		return courseCategoryFolder;
	}
	public void setCourseCategoryFolder(String courseCategoryFolder) {
		this.courseCategoryFolder = courseCategoryFolder;
	}
	
}
