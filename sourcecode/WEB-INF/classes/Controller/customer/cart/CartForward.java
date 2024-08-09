package Controller.customer.cart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Cart;
import BEAN.User;
import DAO.CartDAO;
import DAO.ErrorDAO;
import DB.DBConnection;

@WebServlet("/CartForward")
public class CartForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		//lấy thông tin user
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");
        
        Map<Integer, List<Cart>> cartItemStatus0 = CartDAO.getCartItems(conn, user.getUserId(), 0);
        Map<Integer, List<Cart>> cartItemStatus1 = CartDAO.getCartItems(conn, user.getUserId(), 1);
        Map<Integer, List<Cart>> cartItemStatus2 = CartDAO.getCartItems(conn, user.getUserId(), 2);
        Map<Integer, List<Cart>> cartItemStatus3 = CartDAO.getCartItems(conn, user.getUserId(), 3);
        
        request.setAttribute("cartItemStatus0", cartItemStatus0);
        request.setAttribute("cartItemStatus1", cartItemStatus1);
        request.setAttribute("cartItemStatus2", cartItemStatus2);
        request.setAttribute("cartItemStatus3", cartItemStatus3);
        
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(CartForward)", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("Cart/cart.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
