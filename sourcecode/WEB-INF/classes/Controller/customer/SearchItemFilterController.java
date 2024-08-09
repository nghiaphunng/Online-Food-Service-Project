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

import BEAN.Item;
import DAO.ErrorDAO;
import DAO.ItemDAO;
import DB.DBConnection;

@WebServlet("/SearchItemFilterController")
public class SearchItemFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchItemFilterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String location = request.getParameter("location");
        int priceType = Integer.parseInt(request.getParameter("price"));
        int typeItem = Integer.parseInt(request.getParameter("type"));
        String nameInput = request.getParameter("nameInput");
        
        Connection conn = DBConnection.CreateConnection();
        List<Item> itemList = null;
        if(priceType == 0) {
        	itemList = ItemDAO.SearchItemFilterByLowerBoundPrice(request, conn, location, nameInput, typeItem, 30000);
        }
        else if(priceType == 1) {
        	itemList = ItemDAO.SearchItemFilter(request, conn, location, nameInput, typeItem, 30000, 50000);
        }
        else if(priceType == 2) {
        	itemList = ItemDAO.SearchItemFilter(request, conn, location, nameInput, typeItem, 50000, 100000);
        }
        else if(priceType == 3) {
        	itemList = ItemDAO.SearchItemFilterByUpperBoundPrice(request, conn, location, nameInput, typeItem, 100000);
        }
        //đóng cổng kết nối
        try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(SearchItemController)", e.getMessage());
		}
        
        //trả về kết quả tìm kiếm
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        for(Item item : itemList) {
        	out.println("<div class=\"item-restaurant\">\r\n"
        			+ "	                <div class=\"item-content\">\r\n"
        			+ "	                    <div class=\"img-restaurant\">\r\n"
        			+ "							<img class=\"cover\" src=\"img/item/" + item.getImage() + "\" alt=\"\" style=\"width:230px;height:200px; border-radius: 6px 6px 0 0; border: 1px solid #524f4f;\">\r\n"
        			+ "						</div>\r\n"
        			+ "	                    <div class=\"info-restaurant\">\r\n"
        			+ "	                        <div class=\"info-basic-res\">  \r\n"
        			+ "	                            <a href=\"\" class=\"name-res\" title=\"" + item.getName() + "\">" + item.getName() + "</a>\r\n"
        			+ "	                            <div class=\"address-res\" title=\"" + item.getLocationRestaurant() + "\" >" + item.getLocationRestaurant() + "</div>\r\n"
        			+ "	                        </div>\r\n"
        			+ "	                        <div class=\"inf\">\r\n"
        			+ "		                        <button class=\"price-button\">$" + item.getPrice() + " VNĐ</button>\r\n"
        			+ "		                        <button class=\"add-button\">Thêm vào giỏ hàng</button>\r\n"
        			+ "	                        </div>\r\n"
        			+ "	                    </div>\r\n"
        			+ "	                </div>\r\n"
        			+ "	            </div>");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
