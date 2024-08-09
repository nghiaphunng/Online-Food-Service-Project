package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.User;

public class LoginDAO {
	//kiểm tra tk+mk có trong csdl và là user/admin
	public static User authenticationUser(HttpServletRequest request, Connection conn, User user) {
	    PreparedStatement ptmt = null;
	    String sql = "SELECT * FROM user WHERE nameLogin = ? AND passwordUser = ?";
	    try {
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, user.getNameLogin());
	        ptmt.setString(2, user.getPasswordUser());
	        ResultSet rs = ptmt.executeQuery();
	        
	        if (rs.next()) {
            user.setUserId(rs.getInt("userId"));
            user.setNameUser(rs.getString("nameUser"));
            user.setNameLogin(rs.getString("nameLogin"));
            user.setEmailUser(rs.getString("emailUser"));
            user.setPhoneUser(rs.getString("phoneUser"));
            user.setAddress(rs.getString("addressUser"));
            user.setPasswordUser(rs.getString("passwordUser"));
            user.setGender(rs.getInt("gender"));
            user.setTypeUser(rs.getInt("typeOfUser")); 
            user.setUrlAvatar(rs.getString("urlAvatar"));
            return user;
	        }
	        
	        ptmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        ErrorDAO.insertError(conn, "messageErrorLogin", e.getMessage());
	    }
	    return null;
	}
}
