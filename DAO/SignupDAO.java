package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import BEAN.User;

public class SignupDAO {
	public static int InsertUser(HttpServletRequest request, Connection conn, User user) {
	    PreparedStatement ptmt = null;
	    ResultSet rs = null;
	    int userId = -1; // Giá trị mặc định của userId nếu không thành công

	    String sql = "insert into user(userName,userEmail,userPassWord,userCategoryId,userMoney) values (?,?,?,?,?)";
	    try {
	        ptmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        String userName = user.getUserName();
	        String userEmail = user.getUserEmail();
	        String userPassWord = user.getUserPassWord();
	        int userCategoryId = user.getUserCategoryId();
	        int userMoney = user.getUserMoney();

	        ptmt.setString(1, userName);
	        ptmt.setString(2, userEmail);
	        ptmt.setString(3, userPassWord);
	        ptmt.setInt(4, userCategoryId);
	        ptmt.setInt(5, userMoney);

	        int check = ptmt.executeUpdate();
	        if (check != 0) {
	            rs = ptmt.getGeneratedKeys();
	            if (rs.next()) {
	                userId = rs.getInt(1); // Lấy giá trị của cột userId từ ResultSet
	            }
	        }
	    } catch (SQLException e) {
	        request.setAttribute("messageInsertUser", e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ptmt != null) ptmt.close();
	        } catch (SQLException e) {
	        	request.setAttribute("messageCloseConnec", e.getMessage());
	        }
	    }
	    
	    return userId;
	}

	
	public static boolean InsertProfile(HttpServletRequest request, Connection conn, User user) {
	    PreparedStatement ptmt = null;
	    String sql = "INSERT INTO profile (profileId, fullName, birthday, gender, avatarURL, createdAt) VALUES (?,?,?,?,?,?)";
	    
	    try {
	        ptmt = conn.prepareStatement(sql);
	        int userId = user.getUserId();
	        String fullName = user.getFullName();
	        String birthdayStr = user.getBirthday();
	        int gender = user.getGender();
	        String avatarURL = user.getUserAvatarURL();
	        
	        // Chuyển đổi chuỗi ngày sinh thành kiểu Date của MySQL
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsedDate = dateFormat.parse(birthdayStr);
	        java.sql.Date birthday = new java.sql.Date(parsedDate.getTime());

	        // Lấy thời gian hiện tại và chuyển đổi thành Timestamp
	        Timestamp createAt = Timestamp.from(Instant.now());
 
	        // Thiết lập giá trị cho các tham số
	        ptmt.setInt(1, userId);
	        ptmt.setString(2, fullName);
	        ptmt.setDate(3, birthday);
	        ptmt.setInt(4, gender);
	        ptmt.setString(5, avatarURL);
	        ptmt.setTimestamp(6, createAt);

	        int check = ptmt.executeUpdate();
	        if (check != 0) {
	            return true;
	        }

	        ptmt.close();
	    } catch (SQLException | ParseException e) {
	        request.setAttribute("messageInsertProfile", e.getMessage());
	    }

	    return false;
	}

	
	public static int checkNameAccExist(HttpServletRequest request, Connection conn, User user) { //trả về userId nếu tồn tại
		PreparedStatement ptmt = null;
		String sql = "select * from user";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()) {
				String userName = rs.getString("userName");
				String userEmail = rs.getString("userEmail");
				
				if(user.getUserName().equals(userName)) {
					request.setAttribute("messageNameExist", "Tên tài khoản đã tồn tại");
					return rs.getInt("userId");
				}
				if(user.getUserEmail().equals(userEmail)) {
					request.setAttribute("messageEmailExist", "Email đã được sử dụng");
					return rs.getInt("userId");
				}
			}
			
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("messageErrorCheckNameAccExist", e.getMessage());
		}
		return -1;
	}
}
