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
import javax.servlet.http.HttpSession;

import BEAN.Comment;
import BEAN.User;
import DAO.CommentDAO;
import DB.DBConnection;

@WebServlet("/SendCommentCourse")
public class SendCommentCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendCommentCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding() != null) {
			request.setCharacterEncoding("UTF-8");
		}
		try {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("sessionUser");
			Connection conn = DBConnection.CreateConnection();
			String content = request.getParameter("content");
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			
			//thêm bình luận vào csdl
			Comment cmt = new Comment();
			cmt.setContent(content);
			cmt.setPostId(courseId);
			cmt.setUserName(user.getFullName());
			CommentDAO.insertCommentCourse(request, conn, cmt, user.getUserId(), courseId);
			
			List<Comment> listCmt = CommentDAO.DisplayCommentCourse(request, conn, courseId);
			
			request.setAttribute("listCmt", listCmt);
			RequestDispatcher rd = request.getRequestDispatcher("View/Comment/displaycomment.jsp");
			rd.forward(request, response);

			//đóng cổng kết nối với database
			conn.close();
		}catch(Exception e) {
			request.setAttribute("messageErrorComment", e.getMessage());
		}
	}

}
