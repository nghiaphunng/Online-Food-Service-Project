package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import BEAN.User;

public class RestaurantDAO {
	public static int InsertRestaurant(HttpServletRequest request, Connection conn, User user) {
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    int RestaurantId = -1; // Giá trị mặc định của RestaurantId nếu không thành công

	    String sql = "INSERT INTO restaurant(userId, Name, Location, img) VALUES (?,?,?,?)";
	    try {
	        ptmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        int userId = user.getUserId();
	        String nameUser = user.getNameUser();
			String addressUser = user.getAddress();
			String img = user.getUrlAvatar();
			
			ptmt.setInt(1, userId);
			ptmt.setString(2, nameUser);
			ptmt.setString(3, addressUser);
			ptmt.setString(4, img);
	        
	        int check = ptmt.executeUpdate();
	        if (check != 0) {
	            rs = ptmt.getGeneratedKeys();
	            if (rs.next()) {
	            	RestaurantId = rs.getInt(1); // Lấy giá trị của cột RestaurantId từ ResultSet
	            }
	        }
	    } catch (SQLException e) {
	        ErrorDAO.insertError(conn, "messageInsertRestaurant", e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ptmt != null) ptmt.close();
	        } catch (SQLException e) {
	        	ErrorDAO.insertError(conn, "messageCloseConnec(InsertRestaurant)", e.getMessage());
	        }
	    }
	    
	    return RestaurantId;
	}
	
	public static int UpdateInfoRestaurant(Connection conn, int RestaurantId, String name, String location, String type) {
		PreparedStatement ptmt = null;
		String sql = "UPDATE restaurant SET Name = ?, Location = ?, CuisineTypeDESC = ? WHERE RestaurantId = ?";
        try {
        	ptmt = conn.prepareStatement(sql);
        	ptmt.setString(1, name);
        	ptmt.setString(2, location);
        	ptmt.setString(3, type);
        	ptmt.setInt(4,  RestaurantId);
            return ptmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorDAO.insertError(conn, "Error(UpdateInfoRestaurant)", e.getMessage());
        } finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                	ErrorDAO.insertError(conn, "Error closing PreparedStatement(UpdateInfoRestaurant)", e.getMessage());
                }
            }
        }
        return -1;
    }
}
