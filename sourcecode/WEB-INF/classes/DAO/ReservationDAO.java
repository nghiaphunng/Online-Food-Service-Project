package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Reservation;

public class ReservationDAO{
	//thông bản ghi đặt bàn từ người dùng
	public static void insertInfoReservation(Connection conn, int customerId, int RestaurantId, String ReservationDate, int NumberOfGuests, String AdditionalReminder) {
		PreparedStatement ptmt = null;		
		String sql = "insert into reservation(CustomerId,RestaurantId,ReservationDate,NumberOfGuests,AdditionalReminder) values (?,?,?,?,?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, customerId);
			ptmt.setInt(2,  RestaurantId);
			ptmt.setString(3, ReservationDate);
			ptmt.setInt(4, NumberOfGuests);
			ptmt.setString(5, AdditionalReminder);
			
			ptmt.executeUpdate();
		}catch(SQLException e) {
			ErrorDAO.insertError(conn, "Error(insertInfoReservation", e.getMessage());
		}finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                	ErrorDAO.insertError(conn, "Error closing PreparedStatement(insertInfoReservation", e.getMessage());
                }
            }
        }
	}
	
	//cập nhật trạng thái xử lý đơn đặt bàn của nhà hàng 
	public static boolean UpdateReservationStatus(Connection conn, int ReservationId, int Status) {
	    PreparedStatement ptmt = null;
	    boolean success = false;
	    String sql = "UPDATE reservation SET Status = ? WHERE ReservationId = ?";
	    try {
	        ptmt = conn.prepareStatement(sql);

	        ptmt.setInt(1, Status);
	        ptmt.setInt(2, ReservationId);

	        int rowsAffected = ptmt.executeUpdate();
	        if (rowsAffected > 0) {
	            success = true;
	        } else {
	            success = false;
	        }

	    } catch (SQLException e) {
	        ErrorDAO.insertError(conn, "Error update status(UpdateReservationStatus)", e.getMessage());
	    } finally {
	        // Đóng PreparedStatement trong block finally để đảm bảo luôn được giải phóng tài nguyên
	        if (ptmt != null) {
	            try {
	                ptmt.close();
	            } catch (SQLException e) {
	            	ErrorDAO.insertError(conn, "Error closing PreparedStatement(UpdateReservationStatus)", e.getMessage());
	            }
	        }
	    }
	    return success;
	}	
	
	//list bản ghi đặt bàn của user
	public static List<Reservation> listReservationOfCustomerId(Connection conn, int customerId){
		List<Reservation> reservationList = new ArrayList<Reservation>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select res.*, u.nameUser, u.phoneUser, r.Name, r.Location, ur.phoneUser from reservation res "
				+ "join user u on u.userId = res.CustomerId "
				+ "join restaurant r on r.RestaurantId = res.RestaurantId "
				+ "JOIN user ur ON ur.userId = r.userId "
				+ "where res.CustomerId = ? "
				+ "order by res.Status asc";
		try {		
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, customerId);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Reservation res = new Reservation();
				res.setFullNameCustomer(rs.getString("u.nameUser"));
				res.setPhoneCustomer(rs.getString("u.phoneUser"));
				res.setNumberOfGuests(rs.getInt("res.NumberOfGuests"));
				res.setReservationDate(rs.getString("res.ReservationDate"));
				res.setAdditionalReminder(rs.getString("res.AdditionalReminder"));
				res.setStatus(rs.getInt("res.Status"));
				res.setNameRestaurant(rs.getString("r.Name"));
				res.setLocationRestaurant(rs.getString("r.Location"));
				res.setPhoneRestaurant(rs.getString("ur.phoneUser"));
				
				reservationList.add(res);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnListItemRestaurantId", e.getMessage());
		}
		return reservationList;
	}
	
	//list bản ghi đặt bàn của nhà hàng
	public static List<Reservation> listReservationOfRestaurantId(Connection conn, int restaurantId){
		List<Reservation> reservationList = new ArrayList<Reservation>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select res.*, u.nameUser, u.phoneUser, r.Name, r.Location, ur.phoneUser from reservation res "
				+ "join user u on u.userId = res.CustomerId "
				+ "join restaurant r on r.RestaurantId = res.RestaurantId "
				+ "JOIN user ur ON ur.userId = r.userId "
				+ "where res.RestaurantId = ? "
				+ "order by res.Status asc";
		try {		
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, restaurantId);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Reservation res = new Reservation();
				res.setFullNameCustomer(rs.getString("u.nameUser"));
				res.setPhoneCustomer(rs.getString("u.phoneUser"));
				res.setNumberOfGuests(rs.getInt("res.NumberOfGuests"));
				res.setReservationDate(rs.getString("res.ReservationDate"));
				res.setAdditionalReminder(rs.getString("res.AdditionalReminder"));
				res.setStatus(rs.getInt("res.Status"));
				res.setNameRestaurant(rs.getString("r.Name"));
				res.setLocationRestaurant(rs.getString("r.Location"));
				res.setPhoneRestaurant(rs.getString("ur.phoneUser"));
				res.setReservationId(rs.getInt("res.ReservationId"));
				reservationList.add(res);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnListItemRestaurantId", e.getMessage());
		}
		return reservationList;
	}
}
