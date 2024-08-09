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

@WebServlet("/UpdateCartStatus")
public class UpdateCartStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCartStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		int cartId = Integer.parseInt(request.getParameter("cartId"));
        int newStatus = Integer.parseInt(request.getParameter("newStatus"));
		CartDAO.UpdateCartStatus0123(conn, cartId, newStatus);
		
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(UpdateCartStatus)", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
