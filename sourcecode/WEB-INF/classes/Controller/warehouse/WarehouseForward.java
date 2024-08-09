package Controller.warehouse;

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

import BEAN.Item;
import BEAN.Restaurant;
import DAO.ErrorDAO;
import DAO.ItemDAO;
import DB.DBConnection;

@WebServlet("/WarehouseForward")
public class WarehouseForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WarehouseForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		//gọi session restaurant
		HttpSession session = request.getSession(false);
		Restaurant restaurant = (Restaurant) session.getAttribute("sessionRestaurant");
		
		List<Item> listItemRestaurant = ItemDAO.listTotalItemRestaurantId(request, conn, restaurant.getRestaurantId());
		request.setAttribute("listItemRestaurant", listItemRestaurant);
		
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(WarehouseForward)", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("warehouse/warehouse.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
