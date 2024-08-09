package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import BEAN.User;

public class SignupDAO {
	public static int InsertCustomer(HttpServletRequest request, Connection conn, User user) {
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    int userId = -1; // Giá trị mặc định của userId nếu không thành công

	    String sql = "insert into user(typeOfUser,nameUser,nameLogin,emailUser,phoneUser,addressUser,passwordUser,gender, urlAvatar) values (?,?,?,?,?,?,?,?,?)";
	    try {
	        ptmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        int typeOfUser = user.getTypeUser();
	        String nameUser = user.getNameUser();
			String nameLogin = user.getNameLogin();
			String emailUser = user.getEmailUser();
			String phoneUser = user.getPhoneUser();
			String addressUser = user.getAddress();
			String passwordUser = user.getPasswordUser();
			int gender = user.getGender();
			String img = user.getUrlAvatar();
			
			ptmt.setInt(1, typeOfUser);
	        ptmt.setString(2, nameUser);
	        ptmt.setString(3, nameLogin);
	        ptmt.setString(4, emailUser);
	        ptmt.setString(5, phoneUser);
	        ptmt.setString(6, addressUser);
	        ptmt.setString(7,passwordUser);
	        ptmt.setInt(8, gender);
	        ptmt.setString(9, img);
	        
	        int check = ptmt.executeUpdate();
	        if (check != 0) {
	            rs = ptmt.getGeneratedKeys();
	            if (rs.next()) {
	                userId = rs.getInt(1); // Lấy giá trị của cột userId từ ResultSet
	            }
	        }
	    } catch (SQLException e) {
	        ErrorDAO.insertError(conn, "messageInsertUser", e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ptmt != null) ptmt.close();
	        } catch (SQLException e) {
	        	ErrorDAO.insertError(conn, "messageCloseConnec", e.getMessage());
	        }
	    }
	    
	    return userId;
	}
	
	public static int checkNameAccExist(HttpServletRequest request, Connection conn, User user) { //trả về userId nếu tồn tại
		PreparedStatement ptmt = null;
		String sql = "select * from user";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()) {
				String nameLogin = rs.getString("nameLogin");
				String emailUser = rs.getString("emailUser");
				
				if(user.getNameLogin().equals(nameLogin)) {
					request.setAttribute("messageSignup", "Tên đăng nhập đã tồn tại");
					return rs.getInt(1);
				}
				if(user.getEmailUser().equals(emailUser)) {
					request.setAttribute("messageSignup", "Email đã được sử dụng");
					return rs.getInt(1);
				}
			}
			
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorCheckNameAccExist", e.getMessage());
		}
		return -1;
	}
}
