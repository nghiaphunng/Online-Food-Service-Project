package Controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ErrorDAO;
import DAO.ReservationDAO;
import DB.DBConnection;

@WebServlet("/SendReservationController")
public class SendReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendReservationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		int RestaurantId = Integer.parseInt(request.getParameter("RestaurantId"));
		int CustomerId = Integer.parseInt(request.getParameter("CustomerId"));
		String ReservationDate = request.getParameter("ReservationDate");
		int NumberOfGuests = Integer.parseInt(request.getParameter("NumberOfGuests"));
		String AdditionalReminder = request.getParameter("AdditionalReminder");
		ReservationDAO.insertInfoReservation(conn, CustomerId, RestaurantId, ReservationDate, NumberOfGuests, AdditionalReminder);
		
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(SendReservationController)", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
