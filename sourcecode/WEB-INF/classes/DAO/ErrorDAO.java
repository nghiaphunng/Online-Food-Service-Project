package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.ErrorSystem;

public class ErrorDAO {
	public static void insertError(Connection conn, String errorType, String errorDesc) {
		PreparedStatement ptmt = null;
		
		String sql = "insert into error(errorType,errorDesc,errorOccurrenceTime) values (?,?,?)";
		try {
			ptmt = conn.prepareStatement(sql);
			// Lấy thời gian hiện tại và chuyển đổi thành Timestamp
	        Timestamp errorOccurrenceTime = Timestamp.from(Instant.now());
			
			ptmt.setString(1, errorType);
			ptmt.setString(2,  errorDesc);
			ptmt.setTimestamp(3, errorOccurrenceTime);
			
			ptmt.executeUpdate();
		}catch(SQLException e) {
			System.err.println("Error inserting error record: " + e.getMessage());
		}finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
	}
	
	public static List<ErrorSystem> DisplayErrorList(HttpServletRequest request, Connection conn){
		List<ErrorSystem> listError = new ArrayList<ErrorSystem>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM error ORDER BY ProcessingStatus ASC, errorOccurrenceTime ASC";
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				ErrorSystem error = new ErrorSystem();
				error.setErrorId(rs.getInt("errorId"));
				error.setErrorType(rs.getString("errorType"));
				error.setErrorDesc(rs.getString("errorDesc"));
				error.setErrorOccurrenceTime(rs.getString("errorOccurrenceTime"));
				error.setErrorStatus(rs.getInt("ProcessingStatus"));
				listError.add(error);
			}
		} catch (SQLException e) {
			insertError(conn, "messageErrorDisplayErrorList", e.getMessage());
		} finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    insertError(conn, "Error closing PreparedStatement(DisplayErrorList)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    insertError(conn, "Error closing ResultSet(DisplayErrorList)", e.getMessage());
                }
            }
        }
		return listError;
	}

	public static boolean UpdateErrorStatus(Connection conn, int errorId, int errorStatus) {
	    PreparedStatement ptmt = null;
	    boolean success = false;

	    String sql = "UPDATE error SET ProcessingStatus = ? WHERE errorId = ?";
	    try {
	        ptmt = conn.prepareStatement(sql);

	        ptmt.setInt(1, errorStatus);
	        ptmt.setInt(2, errorId);

	        int rowsAffected = ptmt.executeUpdate();
	        if (rowsAffected > 0) {
	            success = true;
	        } else {
	            success = false;
	        }

	    } catch (SQLException e) {
	        System.err.println("Error update status: " + e.getMessage());
	    } finally {
	        // Đóng PreparedStatement trong block finally để đảm bảo luôn được giải phóng tài nguyên
	        if (ptmt != null) {
	            try {
	                ptmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error closing PreparedStatement: " + e.getMessage());
	            }
	        }
	    }

	    return success;
	}	
}
