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

import BEAN.User;
import DAO.ErrorDAO;
import DAO.RestaurantDAO;
import DAO.SignupDAO;
import DB.DBConnection;

@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignupController() {
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
		//tạo kết nối với mySQL
		Connection conn = DBConnection.CreateConnection();
		String nameUser = request.getParameter("nameUser");
		String nameLogin = request.getParameter("nameLogin");
		String emailUser = request.getParameter("emailUser");
		String phoneUser = request.getParameter("phoneUser");
		String addressUser = request.getParameter("addressUser");
		String passwordUser = request.getParameter("passwordUser");
		int gender = Integer.parseInt(request.getParameter("genderUser"));
		int typeOfUser = Integer.parseInt(request.getParameter("role"));
		
		//tạo tài khoản khách hàng
		User customer = new User();
		customer.setNameUser(nameUser);
		customer.setNameLogin(nameLogin);
		customer.setEmailUser(emailUser);
		customer.setPhoneUser(phoneUser);
		customer.setAddress(addressUser);
		customer.setPasswordUser(passwordUser);
		customer.setGender(gender);
		customer.setTypeUser(typeOfUser);
		if(gender == 1) {  //nam
			customer.setUrlAvatar("img/avatar/img_avatar_1.png");
		}
		else {  //nữ
			customer.setUrlAvatar("img/avatar/img_avatar_2.png");
		}
		int userId = SignupDAO.checkNameAccExist(request, conn, customer);
		if(userId == -1) { //tên/email chưa được sử dụng
			userId = SignupDAO.InsertCustomer(request, conn, customer);
			if(userId != -1) {
				customer.setUserId(userId);
				request.setAttribute("messageSignup", "Đăng ký tài khoản thành công");
				if(customer.getTypeUser() == 2) { //tạo thông tin nhà hàng
					int RestaurantId = RestaurantDAO.InsertRestaurant(request, conn, customer);
					if(RestaurantId != -1) {
						request.setAttribute("messageSignup", "đăng ký tài khoản nhà hàng thành công");
					}
					else {
						request.setAttribute("messageSignup", "đăng ký tài khoản nhà hàng thất bại");
					}
				}
				RequestDispatcher rd = request.getRequestDispatcher("Login/Login.jsp");
				rd.forward(request, response);
				
			}
			else {
				request.setAttribute("messageSignup", "Đã xảy ra lỗi hệ thống, làm phiền bạn đăng nhập lại nhé");
				RequestDispatcher rd = request.getRequestDispatcher("Signup/Signup.jsp");
				rd.forward(request, response);
			}
			
		}
		else {
			//đăng ký tài khoản thất bại
			RequestDispatcher rd = request.getRequestDispatcher("Signup/Signup.jsp");
			rd.forward(request, response);
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorDAO.insertError(conn, "Error Closing Connection(SignupController)", e.getMessage());
		}
	}

}
