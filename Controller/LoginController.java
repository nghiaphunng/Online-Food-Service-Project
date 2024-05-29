package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.User;
import DAO.LoginDAO;
import DB.DBConnection;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//tạo kết nối tới csdl
		Connection conn = DBConnection.CreateConnection();
		
		//Lấy dữ liệu người đăng nhập
		String userName = request.getParameter("userName");
		String userPassWord = request.getParameter("userPassWord");
		User user = new User();
		user.setUserName(userName);
		user.setUserPassWord(userPassWord);
		User userInput = LoginDAO.authenticationUser(request, conn, user);
		if(userInput != null) {
			if(user.getUserCategoryId() == 1) { //là admin
				HttpSession session = request.getSession(true);
				session.setAttribute("sessionAdmin", userInput);
				RequestDispatcher rd = request.getRequestDispatcher("AdminForward");
				rd.forward(request, response);
			}
			else if(user.getUserCategoryId() == 2) { //là người dùng
				HttpSession session = request.getSession(true);
				session.setAttribute("sessionUser", userInput);
				RequestDispatcher rd = request.getRequestDispatcher("HomeForward");
				rd.forward(request, response);
			}
		}
		else {
			request.setAttribute("messageLogin", "Tài khoản không tồn tại");
			RequestDispatcher rd = request.getRequestDispatcher("LoginForward");
			rd.forward(request, response);
		}
	}
}
