package Controller.course;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Course;
import DAO.SearchDAO;
import DB.DBConnection;

@WebServlet("/NameCourseSearch")
public class NameCourseSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NameCourseSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding() != null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		try {
			Connection conn = DBConnection.CreateConnection();
			String key = request.getParameter("search");
			
			//tìm kiếm tên khóa học từ database
			List<Course> searchNameCourseList = SearchDAO.searchNameCourseList(request, conn, key);
			
			
			 request.setAttribute("searchNameCourseList", searchNameCourseList);
			 RequestDispatcher rd = request.getRequestDispatcher("View/Search/SearchResult.jsp");
			 rd.forward(request, response);
			
			//đóng cổng kết nối với database
			conn.close();
		}catch(Exception e) {
			request.setAttribute("messageErrorSearchNameCourse", e.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
