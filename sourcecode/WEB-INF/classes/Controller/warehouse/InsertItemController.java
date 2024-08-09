package Controller.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ErrorDAO;
import DAO.WarehouseDAO;
import DB.DBConnection;

@WebServlet("/InsertItemController")
public class InsertItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Connection conn = DBConnection.CreateConnection();
		
		//lấy thông tin từ form
		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		String nameItem = request.getParameter("nameItem");
		String desItem = request.getParameter("desItem");
		String desSearchItem = request.getParameter("desSearchItem");
		int priceItem = Integer.parseInt(request.getParameter("priceItem"));
		int typeItem = Integer.parseInt(request.getParameter("typeItem"));
		int quantityItem = Integer.parseInt(request.getParameter("quantityItem"));
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
		int checkInsertItem = WarehouseDAO.InsertItem(conn, restaurantId, nameItem, desItem, desSearchItem, priceItem, typeItem, quantityItem);
		
		if(checkInsertItem != -1) {
			out.println("Thêm sản phẩm thành công");
		}
		else {
			out.println("Thêm sản phẩm thất bại");
		}
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(InsertItemController)", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
