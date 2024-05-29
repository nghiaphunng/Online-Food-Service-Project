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

import BEAN.Comment;
import DAO.CommentDAO;
import DB.DBConnection;

@WebServlet("/DeleteCommentCourse")
public class DeleteCommentCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteCommentCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		try {
			Connection conn = DBConnection.CreateConnection();
			
			//xóa bình luận theo commentId
			CommentDAO.DeleteCommentCourse(request, conn, commentId);
			
			//trả về danh sách bình luận
			List<Comment> listCmt = CommentDAO.DisplayCommentCourse(request, conn, courseId);			
			request.setAttribute("listCmt", listCmt);
			RequestDispatcher rd = request.getRequestDispatcher("View/Comment/displaycomment.jsp");
			rd.forward(request, response);
			conn.close();
		}catch(Exception e) {
			request.setAttribute("messageErrorComment", e.getMessage());
		}
	}

}
