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

import BEAN.Restaurant;
import BEAN.User;
import DAO.AdminDAO;
import DAO.ErrorDAO;
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
		String nameLogin = request.getParameter("nameLogin");
		String userPassWord = request.getParameter("passwordUser");
		User user = new User();
		user.setNameLogin(nameLogin);
		user.setPasswordUser(userPassWord);
		User userInput = LoginDAO.authenticationUser(request, conn, user);
		if(userInput != null) {
			HttpSession session = request.getSession(true);
			if(userInput.getTypeUser() == 1 || userInput.getTypeUser() == 2) { //khách hàng hoặc nhà hàng
				session.setAttribute("sessionUser", userInput);
				if(userInput.getTypeUser() == 2) {
					//tạo ra nhà hàng tương ứng
					Restaurant restaurant = AdminDAO.getRestaurant(request, conn, userInput.getUserId());
					session.setAttribute("sessionRestaurant", restaurant);
				}
				RequestDispatcher rd = request.getRequestDispatcher("HomeForward");
				rd.forward(request, response);
			}
			else if(userInput.getTypeUser() == 3) { //admin
				session.setAttribute("sessionAdmin", userInput);
				RequestDispatcher rd = request.getRequestDispatcher("AdminForward");
				rd.forward(request, response);
			}
			
		}
		else {
			request.setAttribute("messageLogin", "Tài khoản không tồn tại");
			RequestDispatcher rd = request.getRequestDispatcher("LoginForward");
			rd.forward(request, response);
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(LoginController)", e.getMessage());
		}
	}
}
