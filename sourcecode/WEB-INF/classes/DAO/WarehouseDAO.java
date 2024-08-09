package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WarehouseDAO {
	//cập nhật sản phẩm theo itemId
	public static int UpdateItemId(Connection conn, int itemId, String nameItem, int priceItem, int quantityItem, String desItem, String desSearchItem) {
		PreparedStatement ptmt = null;
		 String sql = "UPDATE item SET Name = ?, Price = ?, NumberOfItem = ?, Description = ?, SearchSuggestion = ? WHERE ItemId = ?";
        try {        	
        	ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, nameItem);
            ptmt.setInt(2, priceItem);
            ptmt.setInt(3, quantityItem);           
            if (desItem != null && !desItem.trim().isEmpty()) {
                ptmt.setString(4, desItem);
            } else {
                ptmt.setNull(4, java.sql.Types.VARCHAR); //null
            }
            
            if (desSearchItem != null && !desSearchItem.trim().isEmpty()) {
                ptmt.setString(5, desSearchItem);
            } else {
                ptmt.setNull(5, java.sql.Types.VARCHAR); //null
            }            
            ptmt.setInt(6, itemId);      	
            return ptmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorDAO.insertError(conn, "Error(UpdateItemId)", e.getMessage());
            return -1;
        } finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                	ErrorDAO.insertError(conn, "Error closing PreparedStatement(UpdateItemId)", e.getMessage());
                }
            }
        }
    }
	
	//xóa item theo itemId
	public static void DeleteItemId(Connection conn, int itemId) {
		String sql = "DELETE FROM item WHERE ItemId = ?";
		PreparedStatement ptmt = null;
		
		try {
			conn.setAutoCommit(false);
			
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, itemId);
			ptmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				ErrorDAO.insertError(conn, "Error rollback(DeleteItemId)", e.getMessage());
			}
			ErrorDAO.insertError(conn, "messageErrorDeleteItemId", e.getMessage());
		} finally {
		    if (ptmt != null) {
		        try {
					ptmt.close();
				} catch (SQLException e) {
					ErrorDAO.insertError(conn, "Error closing PreparedStatement(DeleteItemId)", e.getMessage());
				}
		    }
		    try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				ErrorDAO.insertError(conn, "Error commit(DeleteItemId)", e.getMessage());
			} 
		}
	}
	
	//thêm sản phẩm
	public static int InsertItem(Connection conn, int RestaurantId, String nameItem, String desItem, String desSearchItem, int priceItem, int typeItem, int quantityItem) {
		PreparedStatement ptmt = null;
		 String sql = "insert into item(RestaurantId,Name,Price,NumberOfItem,ItemType,Description,SearchSuggestion) values (?,?,?,?,?,?,?)";
        try {        	
        	ptmt = conn.prepareStatement(sql);
        	ptmt.setInt(1,  RestaurantId);
            ptmt.setString(2, nameItem);
            ptmt.setInt(3, priceItem);
            ptmt.setInt(4, quantityItem);      
            ptmt.setInt(5,  typeItem);
            if (desItem != null && !desItem.trim().isEmpty()) {
                ptmt.setString(6, desItem);
            } else {
                ptmt.setNull(6, java.sql.Types.VARCHAR); //null
            }
            
            if (desSearchItem != null && !desSearchItem.trim().isEmpty()) {
                ptmt.setString(7, desSearchItem);
            } else {
                ptmt.setNull(7, java.sql.Types.VARCHAR); //null
            }               	
            return ptmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorDAO.insertError(conn, "Error(InsertItem)", e.getMessage());
            return -1;
        } finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                	ErrorDAO.insertError(conn, "Error closing PreparedStatement(InsertItem)", e.getMessage());
                }
            }
        }
    }
}
