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
	    String sql = "SELECT u.*, p.* " +
	                 "FROM user u " +
	                 "INNER JOIN profile p ON u.userId = p.profileId " +
	                 "WHERE u.userName = ? AND u.userPassWord = ?";
	    try {
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setString(1, user.getUserName());
	        ptmt.setString(2, user.getUserPassWord());
	        ResultSet rs = ptmt.executeQuery();
	        
	        if (rs.next()) {
	            // Populate user object with data from both tables
	            user.setUserId(rs.getInt("u.userId"));
	            user.setUserName(rs.getString("u.userName"));
	            user.setUserEmail(rs.getString("u.userEmail"));
	            user.setUserPassWord(rs.getString("u.userPassWord"));
	            user.setUserCategoryId(rs.getInt("u.userCategoryId"));
	            user.setUserMoney(rs.getInt("u.userMoney"));
	            user.setFullName(rs.getString("p.fullName"));
	            user.setBirthday(rs.getString("p.birthday"));
	            user.setGender(rs.getInt("p.gender"));
	            user.setUserAvatarURL(rs.getString("p.avatarURL"));
	            user.setCreatedAt(rs.getString("p.createdAt"));
	            
	            return user;
	        }
	        
	        ptmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        request.setAttribute("messageErrorLogin", e.getMessage());
	    }
	    
	    return null;
	}

}
