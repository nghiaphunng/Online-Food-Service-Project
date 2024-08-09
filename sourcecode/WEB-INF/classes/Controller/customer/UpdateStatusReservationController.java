package Controller.customer;

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
import javax.servlet.http.HttpSession;

import BEAN.Reservation;
import BEAN.Restaurant;
import BEAN.User;
import DAO.ErrorDAO;
import DAO.ReservationDAO;
import DB.DBConnection;

@WebServlet("/UpdateStatusReservationController")
public class UpdateStatusReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateStatusReservationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		int ReservationId = Integer.parseInt(request.getParameter("ReservationId"));
		int Status = Integer.parseInt(request.getParameter("Status"));
		ReservationDAO.UpdateReservationStatus(conn, ReservationId, Status);
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("sessionUser");
		Restaurant restaurant = (Restaurant) session.getAttribute("sessionRestaurant");
		List<Reservation> reservationList = ReservationDAO.listReservationOfRestaurantId(conn, restaurant.getRestaurantId());
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    out.println("<table>\r\n"
	    		+ "		<thead>\r\n"
	    		+ "			<tr>");
	    
	    if(user.getTypeUser() == 1) { //khách hàng
	    	out.println("<th>Nhà hàng</th>");
	    }
	    else if(user.getTypeUser() == 2 && restaurant != null) {
	    	out.println("<th>Khách hàng</th>");
	    }
	    out.println("	<th>Số khách</th>\r\n"
	    		+ "		<th>Ngày đặt</th>\r\n"
	    		+ "		<th>Ghi chú</th>\r\n"
	    		+ "		<th>Trạng thái</th>\r\n"
	    		+ "	</tr>");
	    
	    for(Reservation res : reservationList) {
	    	out.println("<tr>");
	    	if(user.getTypeUser() == 1) { //khách hàng
	    		out.println("<td title=\"Địa chỉ: "+ res.getLocationRestaurant() + "-Liên hệ: "+ res.getPhoneRestaurant() + "\">" + res.getNameRestaurant() + "</td>");
	    	}
	    	else if(user.getTypeUser() == 2) { //nhà hàng
	    		out.println("<td title=\"Liên hệ: " + res.getPhoneCustomer() + "\">" + res.getFullNameCustomer() + "</td>");
	    	}
	    	out.println("<td>" + res.getNumberOfGuests() + "</td>\r\n"
	    			+ "	 <td>" + res.getReservationDate() + "</td>\r\n"
	    			+ "	 <td>" + res.getAdditionalReminder() + "</td>\r\n"
	    			+ "	 <td>");
	    	if(user.getTypeUser() == 1) { //khách hàng
	    		out.println((res.getStatus() == 1 ? "Đã xác nhận" : "Chưa xác nhận"));
	    	}
	    	else if(user.getTypeUser() == 2) { //nhà hàng
	    		out.println("<select onchange=\"UpdateStatusReservationFromRestaurant(" + res.getReservationId() + ", this.value)\" style=\"padding: 5px; font-size: 15px; font-weight: bold; border: 3px solid chocolate;\">\r\n"
	    				+ "		<option value=\"0\" " + (res.getStatus() == 0 ? "selected" : "") + ">Chưa xác nhận</option>\r\n"
	    				+ "		<option value=\"1\" " + (res.getStatus() == 1 ? "selected" : "") + ">Đã xác nhận</option>\r\n"
	    				+ " </select>");
	    	}
	    	out.println("</td>\r\n"
	    			+ "	</tr>");
	    }
	    out.println("</thead>\r\n"
	    		+ "				</table>");
		
		//đóng cổng kết nối
  		try {
  			conn.close();
  		} catch (SQLException e) {
  			ErrorDAO.insertError(conn, "Error Closing Connection(UpdateStatusReservationController)", e.getMessage());
  		} 		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
