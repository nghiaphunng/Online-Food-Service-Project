package Controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Restaurant;
import DAO.AdminDAO;
import DAO.ErrorDAO;
import DB.DBConnection;

@WebServlet("/DisplayRestaurantFilterController")
public class DisplayRestaurantFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DisplayRestaurantFilterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		String name = request.getParameter("name");
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<Restaurant> listRestaurant = AdminDAO.DisplayRestaurantFilter(request, conn, name, limit);
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    out.println("<table>\r\n"
	    		+ "					<thead>\r\n"
	    		+ "						<tr>\r\n"
	    		+ "							<th>Tên nhà hàng</th>\r\n"
	    		+ "							<th>Chủ nhà hàng</th>\r\n"
	    		+ "							<th>Địa chỉ</th>\r\n"
	    		+ "							<th>Hình thức bán hàng</th>\r\n"
	    		+ "							<th>Xóa tài khoản</th>\r\n"
	    		+ "						</tr>");
	    
	    for(Restaurant restaurant : listRestaurant) {
	    	out.println("<tr>\r\n"
	    			+ "		<td title=\"[" + restaurant.getRestaurantId() + "]\">" + restaurant.getRestaurantName() + "</td>\r\n"
	    			+ "		<td title=\"" + restaurant.getRestaurantOwnerFullName() + "-[" + restaurant.getRestaurantOwnerId() + "]\">" + restaurant.getRestaurantOwnerLoginName() + "</td>\r\n"
	    			+ "		<td>" + restaurant.getRestaurantLocation() + "</td>\r\n"
	    			+ "		<td>" + restaurant.getCuisineTypeDESC() + "</td>\r\n"
	    			+ "		<td><button class=\"remove-button\" type=\"button\" onclick=\"DeleteRestaurant(" + restaurant.getRestaurantId() + ")\">Xóa</button></td>\r\n"
	    			+ "</tr>");
	    }
	    
	    out.println("</thead>\r\n"
	    		+ "				</table>");
	    try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(DisplayRestaurantFilterController)", e.getMessage());
		}
	}

}
