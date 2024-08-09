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

import BEAN.Notification;
import DAO.ErrorDAO;
import DAO.NotificationDAO;
import DB.DBConnection;

@WebServlet("/UpdateStatusNotiFromUserController")
public class UpdateStatusNotiFromUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateStatusNotiFromUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		int notiId = Integer.parseInt(request.getParameter("notiId"));
		int notiStatus = Integer.parseInt(request.getParameter("notiStatus"));
		NotificationDAO.UpdateStatusNoti(conn, notiId, notiStatus);
		List<Notification> listNotiFromUser = NotificationDAO.DisplayNotiFromUser(request, conn);
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.println("<table>\r\n"
	    		+ "					<thead>\r\n"
	    		+ "						<tr>\r\n"
	    		+ "							<th>Tên tài khoản người dùng</th>\r\n"
	    		+ "							<th>Chủ đề</th>\r\n"
	    		+ "							<th>Nội dung</th>\r\n"
	    		+ "							<th>Thời gian gửi</th>\r\n"
	    		+ "							<th>Trạng thái xử lý</th>\r\n"
	    		+ "						</tr>");
	    
	    for(Notification noti : listNotiFromUser) {
	    	out.println("<tr>\r\n"
	    			+ "								<td title=\"" + noti.getFullNameSender()+ "-[" + noti.getSenderId() + "]\">" + noti.getNameLoginSender() + "</td>\r\n"
	    			+ "								<td>" + noti.getTitle() + "</td>\r\n"
	    			+ "								<td>" + noti.getContent() + "</td>\r\n"
	    			+ "								<td>" + noti.getSendingTime() + "</td>\r\n"
	    			+ "								<td>\r\n"
	    			+ "									<select onchange=\"UpdateStatusNotiFromUser(" + noti.getNotificationId() + ",this.value)\" style=\"padding: 5px; font-size: 15px; font-weight: bold; border: 3px solid chocolate;\">\r\n"
	    			+ "										<option value=\"0\"" + (noti.getStatus() == 0 ? " selected" : "") + ">Chưa xử lý</option>\r\n"
	    			+ "										<option value=\"1\"" + (noti.getStatus() == 1 ? " selected" : "") + ">Đang xử lý</option>\r\n"
	    			+ "										<option value=\"2\"" + (noti.getStatus() == 2 ? " selected" : "") + ">Đã xử lý</option>\r\n"
	    			+ "									</select>\r\n"
	    			+ "								</td>\r\n"
	    			+ "							</tr>");
	    }
	    
	    out.println("</thead>\r\n"
	    		+ "				</table>");
	    
	  //đóng cổng kết nối
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(UpdateStatusNotiFromUserController)", e.getMessage());
		}
	}

}
