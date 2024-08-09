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
import DAO.UserDAO;
import DB.DBConnection;

@WebServlet("/UpdatePasswordUser")
public class UpdatePasswordUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdatePasswordUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
		Connection conn = DBConnection.CreateConnection();
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		//gọi session user
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("sessionUser");
				
		boolean checkUserExist = UserDAO.CheckUserExist(conn, user.getUserId(), oldPassword);
		if(checkUserExist) {
			boolean passwordUpdated = UserDAO.UpdatePassword(conn, user.getUserId(), newPassword);
			if(passwordUpdated) {
				request.setAttribute("messageUpdatePasswordUser", "Cập nhật mật khẩu thành công");
				user.setPasswordUser(newPassword);
			}
			else {
				request.setAttribute("messageUpdatePasswordUser", "Xin lỗi, đã xảy ra lỗi hệ thống chúng tôi đang cố khắc phục");
			}
		}
		else {
			request.setAttribute("messageUpdatePasswordUser", "Mật khẩu hiện tại không đúng");
		}
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(UpdatePasswordUser)", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("UserPageForward");
		rd.forward(request, response);
	}

}
