package Controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ErrorDAO;
import DAO.NotificationDAO;
import DB.DBConnection;

@WebServlet("/SendNotiToUserController")
public class SendNotiToUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendNotiToUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
		Connection conn = DBConnection.CreateConnection();
		String recipientuser = request.getParameter("recipientuser");
		String topic = request.getParameter("topic");
		String message = request.getParameter("message");
		NotificationDAO.insertNotiToUser(conn, recipientuser, topic, message);
		//đóng cổng kết nối
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(SendNotiToUserontroller)", e.getMessage());
		}
	}

}
