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

import BEAN.Item;
import BEAN.Restaurant;
import DAO.ErrorDAO;
import DAO.ItemDAO;
import DB.DBConnection;

@WebServlet("/RestaurantViewForward")
public class RestaurantViewForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RestaurantViewForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		Restaurant restaurant = ItemDAO.getRestaurant(conn, restaurantId);
		List<Item> listItemRestaurantType1 = ItemDAO.listItemRestaurantId(request, conn, restaurantId, 1);
		List<Item> listItemRestaurantType2 = ItemDAO.listItemRestaurantId(request, conn, restaurantId, 2);
		List<Item> listItemRestaurantType3 = ItemDAO.listItemRestaurantId(request, conn, restaurantId, 3);
		
		request.setAttribute("restaurant", restaurant);
		request.setAttribute("listItemRestaurantType1", listItemRestaurantType1);
		request.setAttribute("listItemRestaurantType2", listItemRestaurantType2);
		request.setAttribute("listItemRestaurantType3", listItemRestaurantType3);
		
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(RestaurantViewForward)", e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("Restaurant/RestaurantView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
