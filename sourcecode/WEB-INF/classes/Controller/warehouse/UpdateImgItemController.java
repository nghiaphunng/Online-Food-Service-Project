package Controller.warehouse;

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

import BEAN.Item;
import BEAN.Restaurant;
import DAO.ErrorDAO;
import DAO.FileDAO;
import DAO.ItemDAO;
import DB.DBConnection;

@WebServlet("/UpdateImgItemController")
public class UpdateImgItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateImgItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		
		//cập nhật ảnh
		int checkUpdateImgItem = FileDAO.UpdateImgItem(request, response, conn, itemId);
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    if(checkUpdateImgItem == 1) {
	    	//gọi session restaurant
	  		HttpSession session = request.getSession(false);
	  		Restaurant restaurant = (Restaurant) session.getAttribute("sessionRestaurant");
		  	
	  		List<Item> listItemRestaurant = ItemDAO.listTotalItemRestaurantId(request, conn, restaurant.getRestaurantId());
			out.println("<table>\r\n"
					+ "            <thead>\r\n"
					+ "                <tr>\r\n"
					+ "                    <th>Tên sản phẩm</th>\r\n"
					+ "                    <th>Hình ảnh</th>\r\n"
					+ "                    <th>Cập nhật ảnh</th>\r\n"
					+ "                    <th title=\"Đơn vị VNĐ\">Giá</th>\r\n"
					+ "                    <th>Số lượng đã bán</th>\r\n"
					+ "                    <th>Hàng tồn</th>\r\n"
					+ "                    <th>Mô tả</th>\r\n"
					+ "                    <th title=\"Sản phẩm sẽ dễ tiếp cận hơn với khách hàng khi họ tìm kiếm\">Từ khóa</th>\r\n"
					+ "                    <th>Xóa</th>\r\n"
					+ "                    <th>Cập nhật</th>\r\n"
					+ "                </tr>\r\n"
					+ "			</thead>\r\n"
					+ "			\r\n"
					+ "			<tbody>");
			
			for(Item item : listItemRestaurant) {
				out.println("<tr>\r\n"
						+ "	                    <td><input type=\"text\" id=\"nameItem-" + item.getItemId() + "\" name=\"itemName\" value=\"" + item.getName() + "\" title=\"" + item.getName() + "\"><br></td>\r\n"
						+ "	                    <td>\r\n"
						+ "	                        <img src=\"img/item/" + item.getImage() + "\" alt=\"error\" width=\"50\" id=\"display-img\">\r\n"
						+ "	                    </td>\r\n"
						+ "	                    <td>\r\n"
						+ "	                		<form id=\"updateImageItem-" + item.getItemId() + "\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
						+ "	                            <div class=\"file-upload-wrapper\">\r\n"
						+ "	                                <input type=\"file\" class=\"file-upload-input\" id=\"file-upload-input-" + item.getItemId() + "\" name=\"imageItem\">\r\n"
						+ "	                                <label for=\"file-upload-input-" + item.getItemId() + "\" class=\"file-upload-button\" id=\"file-upload-button-" + item.getItemId() + "\">Chọn tệp</label>\r\n"
						+ "	                                <button type=\"button\" class=\"submit-button\" onclick=\"updateImgItemId(" + item.getItemId() + ")\">Cập nhật</button>\r\n"
						+ "	                            </div>\r\n"
						+ "							</form>\r\n"
						+ "	                    </td>                   \r\n"
						+ "	                    <td><input type=\"text\" id=\"priceItem-" + item.getItemId() + "\" name=\"price\" value=\"" + item.getPrice() + "\"></td>\r\n"
						+ "	                    <td><input type=\"number\" value=\"" + item.getSalesCount() + "\" class=\"quantity\" readonly title=\"Đây là số lượng đơn bán không thể thay đổi\"></td>\r\n"
						+ "	                    <td><input type=\"number\" id=\"quantityItem-" + item.getItemId() + "\" value=\"" + item.getNumberOfItem() + "\" min=\"0\" class=\"quantity\" ></td>	                    \r\n"
						+ "	                    <td><input type=\"text\" id=\"desItem-" + item.getItemId() + "\" name=\"des\" value=\"" + (item.getDescription() != null ? item.getDescription() : "") + "\" title=\"" + item.getDescription() + "\"><br></td>\r\n"
						+ "	                    <td><input type=\"text\" id=\"desSearchItem-" + item.getItemId() + "\" name=\"desSearch\" value=\"" + (item.getSearchSuggestion() != null ? item.getSearchSuggestion() : "") + "\" title=\"" + item.getSearchSuggestion() + "\"><br></td>\r\n"
						+ "	                    <td>\r\n"
						+ "	                    	<button type=\"button\" class=\"delete-button\" onclick=\"deleteItemId(" + item.getItemId() + ")\">Xóa</button>\r\n"
						+ "	                    </td>\r\n"
						+ " 	                    <td>\r\n"
						+ "	                    	<button type=\"button\" class=\"submit-button\" onclick=\"updateItemId(" + item.getItemId() + ")\">Cập nhật</button>\r\n"
						+ " 	                    </td>\r\n"
						+ "	                </tr>");
			}
			
			out.println("</tbody>  \r\n"
					+ "        </table>");
	    }
	    else {
	    	out.println("Cập nhật ảnh sản phẩm thất bại");
	    }
		try {
			conn.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "Error Closing Connection(UpdateImgItemController)", e.getMessage());
		}
	}

}
