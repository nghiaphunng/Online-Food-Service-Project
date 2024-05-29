package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Course;
import DAO.CourseDAO;
import DB.DBConnection;

@WebServlet("/HomeForward")
public class HomeForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("displaystatus", "Home");
		
		//tạo kết nối tới csdl
		Connection conn = DBConnection.CreateConnection();
		List<Course> courseList1 = CourseDAO.listCourse(request, conn, 1);
		List<Course> courseList2 = CourseDAO.listCourse(request, conn, 2);
		List<Course> courseList3 = CourseDAO.listCourse(request, conn, 3);
		List<Course> courseList4 = CourseDAO.listCourse(request, conn, 4);
		List<Course> courseList5 = CourseDAO.listCourse(request, conn, 5);
		
		List<Course> latestCreatedCourseList = CourseDAO.latestCreatedCourseList(request, conn, 15);
		
		//tạo linkedhashmap để lưu tất cả các bộ khóa học
		LinkedHashMap<String, List<Course>> courseMap = new LinkedHashMap<String, List<Course>>();
		courseMap.put("Toán và Khoa học cơ bản", courseList1);
		courseMap.put("Lý luận chính trị", courseList2);
		courseMap.put("Cơ sở và cốt lõi ngành", courseList3);
		courseMap.put("Kiến thức bổ trợ", courseList4);
		courseMap.put("Tài liệu tham khảo khác", courseList5);
		
		request.setAttribute("listCourse", courseMap);
		request.setAttribute("latestCreatedCourseList", latestCreatedCourseList);
		RequestDispatcher rd = request.getRequestDispatcher("View/Home/Home.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
