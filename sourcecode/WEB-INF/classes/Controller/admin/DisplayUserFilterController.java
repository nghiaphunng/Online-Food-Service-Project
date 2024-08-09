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

import BEAN.User;
import DAO.AdminDAO;
import DAO.ErrorDAO;
import DB.DBConnection;

@WebServlet("/DisplayUserFilterController")
public class DisplayUserFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DisplayUserFilterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		String name = request.getParameter("name");
		int type = Integer.parseInt(request.getParameter("type"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<User> listUser = AdminDAO.DisplayUserListFilter(request, conn, limit, name, type);
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    out.println("<table>\r\n"
	    		+ "					<thead>\r\n"
	    		+ "						<tr>\r\n"
	    		+ "							<th>Tên đăng nhập</th>\r\n"
	    		+ "							<th>Email</th>\r\n"
	    		+ "							<th>Số điện thoại</th>\r\n"
	    		+ "							<th>Giới tính</th>\r\n"
	    		+ "							<th>Xóa tài khoản</th>\r\n"
	    		+ "							<th>Xóa lịch sử đặt hàng</th>\r\n"
	    		+ "						</tr>");
	    
	    for(User user : listUser) {
	    	out.println("<tr>\r\n"
	    			+ "		<td title=\"" + user.getNameUser() + "-[" + user.getUserId() + "]\">" + user.getNameLogin() + "</td>\r\n"
	    			+ "		<td>" + user.getEmailUser() + "</td>\r\n"
	    			+ "		<td>" + user.getPhoneUser() + "</td>\r\n"
	    			+ "		<td>" + (user.getGender() == 1 ? "Nam" : "Nữ") + "</td>\r\n"
	    			+ "		<td><button class=\"remove-button\" onclick=\"DeleteUser(" + user.getUserId() + ")\">Xóa</button></td>\r\n"
	    			+ "		<td><button class=\"remove-button\" onclick=\"DeleteCartStatus3(" + user.getUserId() + ")\">Xóa</button></td>\r\n"
	    			+ "</tr>");
	    }
	    
	    out.println("</thead>\r\n"
	    		+ "				</table>");
	    try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(DisplayUserFilterController)", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
