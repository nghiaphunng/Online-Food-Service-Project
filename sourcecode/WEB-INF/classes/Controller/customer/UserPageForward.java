package Controller.customer;

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
import javax.servlet.http.HttpSession;

import BEAN.Notification;
import BEAN.Reservation;
import BEAN.Restaurant;
import BEAN.User;
import DAO.ErrorDAO;
import DAO.NotificationDAO;
import DAO.ReservationDAO;
import DB.DBConnection;

@WebServlet("/UserPageForward")
public class UserPageForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserPageForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		//gọi session user
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("sessionUser");
		Restaurant restaurant = (Restaurant) session.getAttribute("sessionRestaurant");
		
		List<Notification> listNotiFromUser = NotificationDAO.DisplayNotiFromAdmin(request, conn, user.getUserId());
		request.setAttribute("listNotiFromUser", listNotiFromUser);
		
		if(user.getTypeUser() == 1) { //khách hàng
			List<Reservation> reservationList = ReservationDAO.listReservationOfCustomerId(conn, user.getUserId());
			request.setAttribute("reservationList", reservationList);
		}
		else if(user.getTypeUser() == 2) { //nhà hàng
			List<Reservation> reservationList = ReservationDAO.listReservationOfRestaurantId(conn, restaurant.getRestaurantId());
			request.setAttribute("reservationList", reservationList);
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(UserPageForward)", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("User/UserPage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
