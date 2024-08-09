package Controller.customer.cart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CartDAO;
import DAO.ErrorDAO;
import DB.DBConnection;

@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddToCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		int userId = Integer.parseInt(request.getParameter("userId"));
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		String checkAddToCart = CartDAO.AddToCart(conn, userId, itemId, restaurantId);
		
		response.setContentType("text/plain; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		response.getWriter().write(checkAddToCart);
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(AddToCartController)", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
