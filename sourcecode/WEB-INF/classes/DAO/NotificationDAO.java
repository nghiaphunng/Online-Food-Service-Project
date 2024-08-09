package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Notification;

public class NotificationDAO {
	
	//cập nhật trạng thái xử lý thông báo từ người dùng hoặc admin
	public static boolean UpdateStatusNoti(Connection conn, int notiId, int notiStatus) {
	    PreparedStatement ptmt = null;
	    boolean success = false;

	    String sql = "UPDATE notification SET Status = ? WHERE UserNotificationId = ?";
	    try {
	        ptmt = conn.prepareStatement(sql);

	        ptmt.setInt(1, notiStatus);
	        ptmt.setInt(2, notiId);

	        int rowsAffected = ptmt.executeUpdate();
	        if (rowsAffected > 0) {
	            success = true;
	        } else {
	            success = false;
	        }

	    } catch (SQLException e) {
	        System.err.println("UpdateStatusNoti update status: " + e.getMessage());
	    } finally {
	        // Đóng PreparedStatement trong block finally để đảm bảo luôn được giải phóng tài nguyên
	        if (ptmt != null) {
	            try {
	                ptmt.close();
	            } catch (SQLException e) {
	                System.err.println("UpdateStatusNoti closing PreparedStatement: " + e.getMessage());
	            }
	        }
	    }

	    return success;
	}
	
	//danh sách thông báo từ người dùng
	public static List<Notification> DisplayNotiFromUser(HttpServletRequest request, Connection conn){
		List<Notification> listNotiFromUser = new ArrayList<Notification>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select n.UserNotificationId, n.SenderId, u.nameLogin, u.nameUser, n.RecipientId, n.Title, n.Content, n.SendingTime, n.Status "
				+ "from notification n "
				+ "join user u on u.userId = n.SenderId "
				+ "where n.RecipientId = 5 "
				+ "order by n.Status asc, n.SendingTime asc";
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Notification noti = new Notification();
				noti.setNotificationId(rs.getInt("n.UserNotificationId"));
				noti.setSenderId(rs.getInt("n.SenderId"));
				noti.setNameLoginSender(rs.getString("u.nameLogin"));
				noti.setFullNameSender(rs.getString("u.nameUser"));
				noti.setRecipientId(rs.getInt("n.RecipientId"));
				noti.setTitle(rs.getString("n.Title"));
				noti.setContent(rs.getString("n.Content"));
				noti.setSendingTime(rs.getString("n.SendingTime"));
				noti.setStatus(rs.getInt("n.Status"));
				
				listNotiFromUser.add(noti);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorDisplayNotiFromUser", e.getMessage());
		} finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(DisplayNotiFromUser)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(DisplayNotiFromUser)", e.getMessage());
                }
            }
        }
		return listNotiFromUser;
	}
	
	//gửi thông báo tới người dùng : không xử lý trường hợp nhập sai
	public static void insertNotiToUser(Connection conn, String objSend, String Title, String Content) {
		PreparedStatement ptmt = null;
		String sql = "";
		if(objSend.equals("@all")) {
			sql = "INSERT INTO notification(Title, Content, SenderId, RecipientId, SendingTime) "
				+ "SELECT ?, ?, 5, userId, NOW() FROM user WHERE typeOfUser = 1 or typeOfUser = 2";
		}
		else {
			sql = "INSERT INTO notification(Title, Content, SenderId, RecipientId, SendingTime) "
				+ "SELECT ?, ?, 5, userId, NOW() FROM user WHERE nameLogin = ?";
		}
		try {
			ptmt = conn.prepareStatement(sql);
			
			ptmt.setString(1, Title);
			ptmt.setString(2,  Content);
			if(!objSend.equals("@all")) {
				ptmt.setString(3, objSend);
			}
			ptmt.executeUpdate();
		}catch(SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorInsertNotiToUser", e.getMessage());
		}finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(insertNotiToUser)", e.getMessage());
                }
            }
        }
	}
	
	//danh sách thông báo của admin gửi tới userId
	public static List<Notification> DisplayNotiFromAdmin(HttpServletRequest request, Connection conn, int userId){
		List<Notification> listNotiFromUser = new ArrayList<Notification>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select n.* from notification n "
				+ "where n.SenderId = 5 and n.RecipientId = ? "
				+ "order by n.Status asc, n.SendingTime asc";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, userId);
			
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Notification noti = new Notification();
				noti.setNotificationId(rs.getInt("n.UserNotificationId"));
				noti.setTitle(rs.getString("n.Title"));
				noti.setContent(rs.getString("n.Content"));
				noti.setSendingTime(rs.getString("n.SendingTime"));
				noti.setStatus(rs.getInt("n.Status"));
				
				listNotiFromUser.add(noti);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorDisplayNotiFromAdmin", e.getMessage());
		} finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(DisplayNotiFromAdmin)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(DisplayNotiFromAdmin)", e.getMessage());
                }
            }
        }
		return listNotiFromUser;
	}
	
	//gửi thông báo tới Admin
	public static void insertNotiToAdmin(Connection conn, String Title, String Content, int userId) {
		PreparedStatement ptmt = null;
		String sql = "INSERT INTO notification(Title, Content, SenderId, RecipientId, SendingTime) "
				+ "value(?, ?, ?, 5, NOW())";
		try {
			ptmt = conn.prepareStatement(sql);
			
			ptmt.setString(1, Title);
			ptmt.setString(2,  Content);
			ptmt.setInt(3,  userId);
			
			ptmt.executeUpdate();
		}catch(SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorInsertNotiToAdmin", e.getMessage());
		}finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(insertNotiToAdmin)", e.getMessage());
                }
            }
        }
	}
	
}
