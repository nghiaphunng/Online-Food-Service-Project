package Controller;

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
import DAO.ErrorDAO;
import DAO.ItemDAO;
import DB.DBConnection;

@WebServlet("/FoodForward")
public class FoodForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FoodForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		List<Item> itemList = ItemDAO.listItemDefaultHome(request, conn, 1);
		request.setAttribute("itemList", itemList);
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(FoodForward)", e.getMessage());
		}
		request.setAttribute("urlLocation", "FoodForward");
		RequestDispatcher rd = request.getRequestDispatcher("Home/home.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
