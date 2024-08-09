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

import BEAN.ErrorSystem;
import DAO.ErrorDAO;
import DB.DBConnection;

@WebServlet("/UpdateStatusErrorController")
public class UpdateStatusErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateStatusErrorController() {
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
		
		int errorId = Integer.parseInt(request.getParameter("errorId"));
		int errorStatus = Integer.parseInt(request.getParameter("errorStatus"));
		ErrorDAO.UpdateErrorStatus(conn, errorId, errorStatus);
		List<ErrorSystem> errorList = ErrorDAO.DisplayErrorList(request, conn);
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    out.println("<table>\r\n"
	    		+ "					<thead>\r\n"
	    		+ "						<tr>\r\n"
	    		+ "							<th>Loại lỗi</th>\r\n"
	    		+ "							<th>Mô tả</th>\r\n"
	    		+ "							<th>Thời điểm xảy ra lỗi</th>\r\n"
	    		+ "							<th>Trạng thái xử lý</th>\r\n"
	    		+ "						</tr>");
	    
		for(ErrorSystem error : errorList) {
			out.println("<tr>\r\n"
					+ "								<td>" + error.getErrorType() + "</td>\r\n"
					+ "								<td>" + error.getErrorDesc() + "</td>\r\n"
					+ "								<td>" + error.getErrorOccurrenceTime() + "</td>\r\n"
					+ "								<td>\r\n"
					+ "									<select onchange=\"UpdateStatusError(" + error.getErrorId() + ", this.value)\" style=\"padding: 5px; font-size: 15px; font-weight: bold; border: 3px solid chocolate;\">\r\n"
					+ "										<option value=\"0\"" + (error.getErrorStatus() == 0 ? " selected" : "") + ">Chưa xử lý</option>\r\n"
					+ "										<option value=\"1\"" + (error.getErrorStatus() == 1 ? " selected" : "") + ">Đang xử lý</option>\r\n"
					+ "										<option value=\"2\"" + (error.getErrorStatus() == 2 ? " selected" : "") + ">Đã xử lý</option>\r\n"
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
			ErrorDAO.insertError(conn, "Error Closing Connection(UpdateStatusErrorController)", e.getMessage());
		}
	}

}
