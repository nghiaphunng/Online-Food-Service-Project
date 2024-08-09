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

@WebServlet("/SearchItemNameInputController")
public class SearchItemNameInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchItemNameInputController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String location = request.getParameter("location");
        String urlLocation = request.getParameter("urlLocation");
        String nameInput = request.getParameter("nameInput");
        
        Connection conn = DBConnection.CreateConnection();
        int typeItem = -1;
        if(urlLocation.equals("HomeForward")) typeItem = 0;
        else if(urlLocation.equals("FoodForward")) typeItem = 1;
        else if(urlLocation.equals("DrinkForward")) typeItem = 2;
        else if(urlLocation.equals("ComboFoodForward")) typeItem = 3;
        
        List<Item> itemList = ItemDAO.SearchItemByNameInput(request, conn, location, nameInput, typeItem);
        //đóng cổng kết nối
        try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(SearchItemLocationController)", e.getMessage());
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
