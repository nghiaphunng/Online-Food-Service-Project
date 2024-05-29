package Controller.course;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Comment;
import BEAN.Course;
import DAO.CommentDAO;
import DAO.CourseDAO;
import DB.DBConnection;

@WebServlet("/CourseForward")
public class CourseForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CourseForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		Connection conn = DBConnection.CreateConnection();
		Course course = CourseDAO.FindCourseId(request, conn, courseId);
		String url = "/View/mon-hoc/" + course.getCourseCategoryFolder() + "/" + course.getCourseURL() + ".jsp";

		List<Comment> listCmt = CommentDAO.DisplayCommentCourse(request, conn, courseId);
		request.setAttribute("listCmt", listCmt);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()
//					+ "/View/mon-hoc/" + courseTypeFolder + "/" + courseURL;
		
		request.setAttribute("courseId", course.getCourseId());
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
