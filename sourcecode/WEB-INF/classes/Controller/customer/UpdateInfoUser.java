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

@WebServlet("/UpdateInfoUser")
public class UpdateInfoUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateInfoUser() {
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
		
		//gọi session user
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("sessionUser");

		//xử lý cập nhật từ đầu vào 
		String fullNameUser = request.getParameter("fullNameUser");
		String addressUser = request.getParameter("addressUser");
		String phoneUser = request.getParameter("phoneUser");
		
		int genderUser;
		String genderUser_str = request.getParameter("genderUser");
		if(genderUser_str != null && !genderUser_str.isEmpty()) genderUser = Integer.parseInt(genderUser_str);
		else genderUser = user.getGender();
		
		String passwordUser = request.getParameter("passwordUser");
		
		String checkUpdateInfoUser = UserDAO.UpdateInfoUser(conn, user, passwordUser, fullNameUser, genderUser, phoneUser, addressUser);
		if(checkUpdateInfoUser.equals("Cập nhật thành công")) {
			request.setAttribute("messageUpdateInfoUser", "Cập nhật thông tin thành công.");
		}
		else if(checkUpdateInfoUser.equals("Nhập sai mật khẩu")) {
			request.setAttribute("messageUpdateInfoUser", "Nhập sai mật khẩu.");
		}
		else {
			request.setAttribute("messageUpdateInfoUser", "Xin lỗi, đã xảy ra lỗi hệ thống.");
		}
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(UpdateInfoUser)", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("UserPageForward");
		rd.forward(request, response);
	}

}
