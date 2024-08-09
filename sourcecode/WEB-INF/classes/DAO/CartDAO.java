package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BEAN.Cart;

public class CartDAO {
	public static String AddToCart(Connection conn, int userId, int itemId, int restaurantId) {
    	CallableStatement cstmt = null;
        try {
        	cstmt = conn.prepareCall("{call add_to_cart(?,?,?)}");

        	cstmt.setInt(1, userId);
        	cstmt.setInt(2, itemId);
        	cstmt.setInt(3, restaurantId);
        	int rowsAffected = cstmt.executeUpdate();
            if(rowsAffected > 0) {
            	return "Thêm sản phẩm vào giỏ hàng thành công";
            }
            else {
            	return "Không thêm được sản phẩm vào giỏ hàng";
            }
        } catch (SQLException e) {
            ErrorDAO.insertError(conn, "Error(AddToCart)", e.getMessage());
            return "Lỗi thêm sản phẩm vào giỏ hàng";
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing CallableStatement(AddToCart)", e.getMessage());
                }
            }
        }
    }
	
	public static Map<Integer, List<Cart>> getCartItems(Connection conn, int userId, int status){
        Map<Integer, List<Cart>> mapCart = new HashMap<>();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        String sql = "SELECT r.Name, i.Name, i.Image, i.Price, ci.quantity, c.id, i.ItemId "
                   + "FROM cart c "
                   + "JOIN cart_item ci ON c.id = ci.cart_id "
                   + "JOIN restaurant r ON c.restaurant_id = r.RestaurantId "
                   + "JOIN item i ON ci.item_id = i.ItemId "
                   + "WHERE c.users_id = ? and c.status = ?";

        try{
        	ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, userId);
            ptmt.setInt(2, status);
            
            rs = ptmt.executeQuery();
            while (rs.next()) {
                Cart cartItem = new Cart();
                cartItem.setNameRestaurant(rs.getString("r.Name"));
                cartItem.setNameItem(rs.getString("i.Name"));
                cartItem.setPriceItem(rs.getInt("i.Price"));
                cartItem.setQuantityItem(rs.getInt("ci.quantity"));
                cartItem.setUrlItem(rs.getString("i.Image"));
                cartItem.setCartId(rs.getInt("c.id"));
                cartItem.setItemId(rs.getInt("i.ItemId"));
                if (!mapCart.containsKey(rs.getInt("c.id"))) {
                    mapCart.put(rs.getInt("c.id"), new ArrayList<>());
                }
                
                mapCart.get(rs.getInt("c.id")).add(cartItem);
            }
        } catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorGetCartItems", e.getMessage());
        } finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(getCartItems)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(getCartItems)", e.getMessage());
                }
            }
        }
        return mapCart;
    }
	
	//cập nhật số lượng item ở giỏ hàng chưa xác nhận
	public static void UpdateQuantityItemStatus0(Connection conn, int cartId, int itemId, int newQuantity) {
    	CallableStatement cstmt = null;
        try {
        	cstmt = conn.prepareCall("{CALL update_cart_item_quantity(?, ?, ?)}");
        	
        	cstmt.setInt(1, cartId);
        	cstmt.setInt(2, itemId);
        	cstmt.setInt(3, newQuantity);
        	cstmt.executeUpdate();

        } catch (SQLException e) {
            ErrorDAO.insertError(conn, "Error(UpdateQuantityItemStatus0)", e.getMessage());
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing CallableStatement(UpdateQuantityItemStatus0)", e.getMessage());
                }
            }
        }
    }
	
	//cập nhật trạng thái đơn hàng
	public static void UpdateCartStatus0123(Connection conn, int cartId, int newStatus) {
    	CallableStatement cstmt = null;
        try {
        	cstmt = conn.prepareCall("{CALL update_order_status(?,?)}");
        	
        	cstmt.setInt(1, cartId);
        	cstmt.setInt(2, newStatus);
        	cstmt.executeUpdate();

        } catch (SQLException e) {
            ErrorDAO.insertError(conn, "Error(UpdateCartStatus0123)", e.getMessage());
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing CallableStatement(UpdateCartStatus0123)", e.getMessage());
                }
            }
        }
    }
	
	//xóa item ra khỏi cart (giỏ hàng trạng thái 0)
	public static void DeleteCartItemStatus0(Connection conn, int cartId, int itemId) {
		PreparedStatement ptmt = null;
		String sql = "DELETE FROM cart_item WHERE cart_id = ? AND item_id = ?";
        try {
        	ptmt = conn.prepareStatement(sql);
        	ptmt.setInt(1, cartId);
        	ptmt.setInt(2,  itemId);
        	ptmt.executeUpdate();

        } catch (SQLException e) {
            ErrorDAO.insertError(conn, "Error(DeleteCartItemStatus0)", e.getMessage());
        } finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing CallableStatement(DeleteCartItemStatus0)", e.getMessage());
                }
            }
        }
    }
	
	//lấy dữ liệu cart cho phía nhà hàng
	public static Map<Integer, List<Cart>> getCartItemsFromRestaurant(Connection conn, int restaurantId, int status){
        Map<Integer, List<Cart>> mapCart = new HashMap<>();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        String sql = "SELECT r.Name, i.Name, i.Image, i.Price, ci.quantity, c.id, i.ItemId, u.nameUser, u.phoneUser, u.addressUser "
        		+ "FROM cart c "
        		+ "JOIN cart_item ci ON c.id = ci.cart_id "
        		+ "JOIN restaurant r ON c.restaurant_id = r.RestaurantId "
        		+ "JOIN item i ON ci.item_id = i.ItemId "
        		+ "JOIN user u ON u.userId = c.users_id "
        		+ "WHERE c.restaurant_id = ? AND c.status = ?";

        try{
        	ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, restaurantId);
            ptmt.setInt(2, status);
            
            rs = ptmt.executeQuery();
            while (rs.next()) {
                Cart cartItem = new Cart();
                cartItem.setNameRestaurant(rs.getString("r.Name"));
                cartItem.setNameItem(rs.getString("i.Name"));
                cartItem.setPriceItem(rs.getInt("i.Price"));
                cartItem.setQuantityItem(rs.getInt("ci.quantity"));
                cartItem.setUrlItem(rs.getString("i.Image"));
                cartItem.setCartId(rs.getInt("c.id"));
                cartItem.setItemId(rs.getInt("i.ItemId"));
                cartItem.setFullNameUser(rs.getString("u.nameUser"));
                cartItem.setPhoneUser(rs.getString("u.phoneUser"));
                cartItem.setAddressUser(rs.getString("u.addressUser"));
                
                if (!mapCart.containsKey(rs.getInt("c.id"))) {
                    mapCart.put(rs.getInt("c.id"), new ArrayList<>());
                }
                
                mapCart.get(rs.getInt("c.id")).add(cartItem);
            }
        } catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorGetCartItems", e.getMessage());
        } finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(getCartItems)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(getCartItems)", e.getMessage());
                }
            }
        }
        return mapCart;
    }
}
