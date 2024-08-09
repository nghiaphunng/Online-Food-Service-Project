package Controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.User;
import DAO.ErrorDAO;
import DAO.FileDAO;
import DB.DBConnection;

@WebServlet("/UpdateAvaUser")
public class UpdateAvaUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateAvaUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DBConnection.CreateConnection();
        HttpSession session = request.getSession(false);
		if(session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("LoginForward");
			rd.forward(request, response);
		}
		User user = (User) session.getAttribute("sessionUser");
		if(user == null) {
			RequestDispatcher rd = request.getRequestDispatcher("LoginForward");
			rd.forward(request, response);
		}
		
		int res = FileDAO.UpdateAvatarUser(request, response, conn, user);
		if(res == 1) {
			request.setAttribute("messageUpdateAvaUser", "Cập nhật ảnh đại diện thành công");
		}
		else {
			request.setAttribute("messageUpdateAvaUser", "Cập nhật ảnh đại diện thất bại");
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(UpdateAvaUser)", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("UserPageForward");
		rd.forward(request, response);
	}
}
