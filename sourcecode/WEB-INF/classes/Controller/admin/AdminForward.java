package Controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.ErrorSystem;
import BEAN.Notification;
import BEAN.Restaurant;
import BEAN.User;
import DAO.AdminDAO;
import DAO.ErrorDAO;
import DAO.NotificationDAO;
import DB.DBConnection;

@WebServlet("/AdminForward")
public class AdminForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		Connection conn = DBConnection.CreateConnection();
		List<ErrorSystem> errorList = ErrorDAO.DisplayErrorList(request, conn);
		long cntErrorStatus_0 = errorList.stream().filter(item -> item.getErrorStatus() == 0).count(); //chưa xử lý
		long cntErrorStatus_1 = errorList.stream().filter(item -> item.getErrorStatus() == 1).count(); //đang xử lý
		request.setAttribute("errorList", errorList);
		request.setAttribute("cntErrorStatus0", cntErrorStatus_0);
		request.setAttribute("cntErrorStatus1", cntErrorStatus_1);
		
		//tài khoản người dùng: cả khách hàng và nhà hàng 
		List<User> listUser = AdminDAO.DisplayUserList(request, conn);
		long cntCustomer = listUser.stream().filter(item -> item.getTypeUser() == 1).count(); //số lượng khách hàng
		long cntRestaurant = listUser.stream().filter(item -> item.getTypeUser() == 2).count(); //số lượng nhà hàng
		request.setAttribute("listUser", listUser);
		request.setAttribute("cntCustomer", cntCustomer);
		request.setAttribute("cntRestaurant", cntRestaurant);
		
		//tài khoản nhà hàng 
		List<Restaurant> listRestaurant = AdminDAO.DisplayRestaurantList(request, conn);
		request.setAttribute("listRestaurant", listRestaurant);
		
		//thông báo từ người dùng
		List<Notification> listNotiFromUser = NotificationDAO.DisplayNotiFromUser(request, conn);
		long cntNotiFromUserStatus_0 = listNotiFromUser.stream().filter(item -> item.getStatus() == 0).count(); //chưa xử lý
		long cntNotiFromUserStatus_1 = listNotiFromUser.stream().filter(item -> item.getStatus() == 1).count(); //chưa xử lý
		request.setAttribute("listNotiFromUser", listNotiFromUser);
		request.setAttribute("cntNotiFromUserStatus0", cntNotiFromUserStatus_0);
		request.setAttribute("cntNotiFromUserStatus1", cntNotiFromUserStatus_1);
		
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(AdminForward)", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("Admin/admin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
