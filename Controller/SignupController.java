package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.User;
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
		//kiểm tra dữ liệu vào định dạng bằng tiếng việt
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		//tạo kết nối với mySQL
		Connection conn = DBConnection.CreateConnection();
		
		//lấy giá trị từ Signup
		String fullName = request.getParameter("fullName");
		String userName = request.getParameter("userName");
		String birthday = request.getParameter("birthday");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String userEmail = request.getParameter("userEmail");
		String userPassWord = request.getParameter("userPassWord");
		int userCategoryId = 2; //mặc định là người dùng/ không phải admin
		int userMoney = 10;
		//khởi tạo đối tượng User
		User user = new User();
		user.setFullName(fullName);
		user.setUserName(userName);
		user.setBirthday(birthday);
		user.setUserEmail(userEmail);
		user.setUserPassWord(userPassWord);
		user.setGender(gender);
		user.setUserCategoryId(userCategoryId);
		user.setUserMoney(userMoney);
		if(user.getGender() == 0) { //nam
			user.setUserAvatarURL("/View/User/avatar/img/img_avatar_0.png");
		}
		else{
			user.setUserAvatarURL("/View/User/avatar/img/img_avatar_1.png");
		}
		;
		int userID = SignupDAO.checkNameAccExist(request, conn, user);
		if(userID == -1) { //chưa có tài khoản nào đăng kí userName
			int userId = SignupDAO.InsertUser(request, conn, user); //kiểm tra có tạo user thành công vào bằng User không
			if(userId != -1) {  // tạo thành công user trong bảng User
				user.setUserId(userId);
				boolean checkInsertProfile = SignupDAO.InsertProfile(request, conn, user);
				if(checkInsertProfile) { //tạo thành công user trong bảng Profile
					request.setAttribute("messageSignup", "Đăng ký tài khoản thành công");
					
					//Tạo sang User Home
					
				}
				else {
					request.setAttribute("messageSignup", "Đăng ký tài khoản không thành công");
				}
			}
			else {
				//in messageSignup ra màn hình Signup
			}
		}
		else {
			//request.setAttribute("messageSignup", "Tên tài khoản đã tồn tại");
			
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Signup/Signup.jsp");
		rd.forward(request, response);
	}
}
