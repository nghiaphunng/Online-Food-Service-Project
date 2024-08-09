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
import DAO.ErrorDAO;
import DAO.RestaurantDAO;
import DB.DBConnection;

@WebServlet("/UpdateInfoRestaurant")
public class UpdateInfoRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateInfoRestaurant() {
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
		Restaurant restaurant = (Restaurant) session.getAttribute("sessionRestaurant");
		
		//xử lý cập nhật từ đầu vào 
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		String type = request.getParameter("type");
		
		int checkUpdateInfoUser = RestaurantDAO.UpdateInfoRestaurant(conn, restaurant.getRestaurantId(), name, location, type);
		if(checkUpdateInfoUser > 0) {
			request.setAttribute("messageUpdateInfoRestaurant", "Cập nhật thông tin thành công.");
			restaurant.setRestaurantLocation(location);
			restaurant.setCuisineTypeDESC(type);
			restaurant.setRestaurantName(name);
		}
		else {
			request.setAttribute("messageUpdateInfoRestaurant", "Cập nhật thông tin thất bại.");
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
