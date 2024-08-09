package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BEAN.User;

public class UserDAO {
	//cập nhật thông tin người dùng 
    public static String UpdateInfoUser(Connection conn, User user, String password, String newFullName, int newGender, String newPhone, String newAddress) {
    	CallableStatement cstmt = null;
        try {
        	cstmt = conn.prepareCall("{call UpdateUserInfo(?, ?, ?, ?, ?, ?, ?)}");

        	cstmt.setInt(1, user.getUserId());
        	cstmt.setString(2, password);
        	cstmt.setString(3, newFullName);
        	cstmt.setInt(4, newGender);
        	cstmt.setString(5, newPhone);
        	cstmt.setString(6, newAddress);

        	cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);

            cstmt.executeUpdate();

            String status = cstmt.getString(7);
            if(status.equals("Cập nhật thành công")) {
            	user.setNameUser(newFullName);
            	user.setGender(newGender);
            	user.setAddress(newAddress);
            	user.setPhoneUser(newPhone);
            }
            return status;
            
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorDAO.insertError(conn, "Error(UpdateInfoUser)", e.getMessage());
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Lỗi không xác định";
    }
    
    //kiểm tra bản ghi của user có tồn tại userId và mật khẩu tương ứng
    public static boolean CheckUserExist(Connection conn, int userId, String password) {
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            String sql = "SELECT * FROM user WHERE userId = ? AND passwordUser = ?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, userId);
            ptmt.setString(2, password);
            rs = ptmt.executeQuery();

            result = rs.next();
        } catch (SQLException e) {
            ErrorDAO.insertError(conn, "ErrorReturnCheckUserExist)", e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ptmt != null) ptmt.close();
            } catch (SQLException e) {
                ErrorDAO.insertError(conn, "Error closing PreparedStatement/ResultSet(CheckUserExist)", e.getMessage());
            }
        }        
        return result;
    }
    
    //cập nhật mật khẩu cho userId
    public static boolean UpdatePassword(Connection conn, int userId, String newPassword) {
        PreparedStatement ptmt = null;
        boolean success = false;

        try {
            String sql = "UPDATE user SET passwordUser = ? WHERE userId = ?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, newPassword);
            ptmt.setInt(2, userId);

            success = ptmt.executeUpdate() > 0;
        } catch (SQLException e) {
            ErrorDAO.insertError(conn, "Error(updatePassword)", e.getMessage());
        } finally {
            try {
                if (ptmt != null) ptmt.close();
            } catch (SQLException e) {
                ErrorDAO.insertError(conn, "Error closing PreparedStatement(updatePassword)", e.getMessage());
            }
        }
        
        return success;
    }
}
