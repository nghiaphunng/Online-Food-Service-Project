package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Restaurant;
import BEAN.User;

public class AdminDAO {
	//trả về số lượng tài khoản: khách hàng, nhà hàng
	public static int cntAccUser(HttpServletRequest request, Connection conn, int typeOfUser){
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) AS count FROM user WHERE user.typeOfUser = ?";		
		try {		
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, typeOfUser);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnCntAccUser", e.getMessage());
		}
		return 0;
	}
	
	//danh sách tài khoản
	public static List<User> DisplayUserList(HttpServletRequest request, Connection conn){
		List<User> listUser = new ArrayList<User>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select * from user where typeOfUser = 1 or typeOfUser = 2";
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setTypeUser(rs.getInt("typeOfUser"));
				user.setNameUser(rs.getString("nameUser"));
				user.setNameLogin(rs.getString("nameLogin"));
				user.setAddress(rs.getString("addressUser"));
				user.setPhoneUser(rs.getString("phoneUser"));
				user.setEmailUser(rs.getString("emailUser"));
				user.setGender(rs.getInt("gender"));
				
				listUser.add(user);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorDisplayCumstomerList", e.getMessage());
		} finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(DisplayUserList)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(DisplayUserList)", e.getMessage());
                }
            }
        }
		return listUser;
	}
	
	//danh sách nhà hàng
	public static List<Restaurant> DisplayRestaurantList(HttpServletRequest request, Connection conn){
		List<Restaurant> listRestaurant = new ArrayList<Restaurant>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select r.*, u.nameUser, u.nameLogin from restaurant r "
				+ "join user u on r.userId = u.userId "
				+ "where u.typeOfUser = 2";
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(rs.getInt("r.RestaurantId"));
				restaurant.setRestaurantOwnerId(rs.getInt("r.userId"));
				restaurant.setRestaurantOwnerFullName(rs.getString("u.nameUser"));
				restaurant.setRestaurantOwnerLoginName(rs.getString("u.nameLogin"));
				restaurant.setRestaurantName(rs.getString("r.Name"));
				restaurant.setCuisineTypeDESC(rs.getString("r.CuisineTypeDESC"));
				restaurant.setRestaurantLocation(rs.getString("r.Location"));
				restaurant.setImgRestaurant(rs.getString("r.img"));
				listRestaurant.add(restaurant);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorDisplayRestaurantList", e.getMessage());
		} finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(DisplayRestaurantList)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(DisplayRestaurantList)", e.getMessage());
                }
            }
        }
		return listRestaurant;
	}
		
	//xóa tài khoản theo userId
	public static void deleteUserId(Connection conn, int userId) {
		String sql = "DELETE FROM user WHERE userId = ?";
		PreparedStatement ptmt = null;
		
		try {
			conn.setAutoCommit(false);
			
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, userId);
			ptmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				ErrorDAO.insertError(conn, "Error rollback(deleteUserId)", e.getMessage());
			}
			ErrorDAO.insertError(conn, "messageErrorDeleteUserId", e.getMessage());
		} finally {
		    if (ptmt != null) {
		        try {
					ptmt.close();
				} catch (SQLException e) {
					ErrorDAO.insertError(conn, "Error closing PreparedStatement(deleteUserId)", e.getMessage());
				}
		    }
		    try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				ErrorDAO.insertError(conn, "Error commit(deleteUserId)", e.getMessage());
			} 
		}
	}
	
	//xóa tài khoản theo restaurantId
	public static void deleteRestaurantId(Connection conn, int RestaurantId) {
		String sql = "DELETE FROM restaurant WHERE RestaurantId = ?";
		PreparedStatement ptmt = null;
		
		try {
			conn.setAutoCommit(false);
			
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, RestaurantId);
			ptmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				ErrorDAO.insertError(conn, "Error rollback(deleteRestaurantId)", e.getMessage());
			}
			ErrorDAO.insertError(conn, "messageErrorDeleteRestaurantId", e.getMessage());
		} finally {
		    if (ptmt != null) {
		        try {
					ptmt.close();
				} catch (SQLException e) {
					ErrorDAO.insertError(conn, "Error closing PreparedStatement(deleteRestaurantId)", e.getMessage());
				}
		    }
		    try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				ErrorDAO.insertError(conn, "Error commit(deleteRestaurantId)", e.getMessage());
			} 
		}
	}
		
	//xóa lịch sử giao hàng của userId
	public static void deleteCartStatus3UserId(Connection conn, int userId){
        String sql = "DELETE FROM cart WHERE users_id = ? AND status = 3";

        PreparedStatement ptmt = null;
        
        try {
            conn.setAutoCommit(false);

            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, userId);
            ptmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            try {
				conn.rollback();
			} catch (SQLException e1) {
				ErrorDAO.insertError(conn, "Error rollback(deleteCartStatus3UserId)", e.getMessage());
			}
        } finally {
            if (ptmt != null) {
                try {
					ptmt.close();
				} catch (SQLException e) {
					ErrorDAO.insertError(conn, "Error closing PreparedStatement(deleteCartStatus3UserId)", e.getMessage());
				}
            }
            try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				ErrorDAO.insertError(conn, "Error commit(deleteCartStatus3UserId)", e.getMessage());
			}
        }
    }
	
	//danh sách user theo bộ lọc
	public static List<User> DisplayUserListFilter(HttpServletRequest request, Connection conn, int limit, String name, int type){
		List<User> listUser = new ArrayList<User>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select * from user where typeOfUser = ? ";
		if (name != null && !name.trim().isEmpty()) {
            sql += "and (nameUser like ? or nameLogin like ?) ";
        }
		sql += "limit ?";
		try {
			ptmt = conn.prepareStatement(sql);
			int cntPara = 1;
			ptmt.setInt(cntPara++, type);
			if (name != null && !name.trim().isEmpty()) {
				ptmt.setString(cntPara++, "%" + name + "%");
				ptmt.setString(cntPara++, "%" + name + "%");
	        }
			ptmt.setInt(cntPara++, limit);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setTypeUser(rs.getInt("typeOfUser"));
				user.setNameUser(rs.getString("nameUser"));
				user.setNameLogin(rs.getString("nameLogin"));
				user.setAddress(rs.getString("addressUser"));
				user.setPhoneUser(rs.getString("phoneUser"));
				user.setEmailUser(rs.getString("emailUser"));
				user.setGender(rs.getInt("gender"));
				
				listUser.add(user);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorDisplayUserListFilter", e.getMessage());
		} finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(DisplayUserListFilter)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(DisplayUserListFilter)", e.getMessage());
                }
            }
        }
		return listUser;
	}
	
	//danh sách nhà hàng theo filter
	public static List<Restaurant> DisplayRestaurantFilter(HttpServletRequest request, Connection conn, String name, int limit){
		List<Restaurant> listRestaurant = new ArrayList<Restaurant>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select r.*, u.nameUser, u.nameLogin from restaurant r "
				+ "join user u on r.userId = u.userId "
				+ "where u.typeOfUser = 2 ";
		if (name != null && !name.trim().isEmpty()) {
            sql += "and (r.Name like ? or r.Location like ?) ";
        }
		sql += "limit ?";
		try {
			ptmt = conn.prepareStatement(sql);
			int cntPara = 1;
			if (name != null && !name.trim().isEmpty()) {
				ptmt.setString(cntPara++, "%" + name + "%");
				ptmt.setString(cntPara++, "%" + name + "%");
	        }
			ptmt.setInt(cntPara++, limit);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(rs.getInt("r.RestaurantId"));
				restaurant.setRestaurantOwnerId(rs.getInt("r.userId"));
				restaurant.setRestaurantOwnerFullName(rs.getString("u.nameUser"));
				restaurant.setRestaurantOwnerLoginName(rs.getString("u.nameLogin"));
				restaurant.setRestaurantName(rs.getString("r.Name"));
				restaurant.setCuisineTypeDESC(rs.getString("r.CuisineTypeDESC"));
				restaurant.setRestaurantLocation(rs.getString("r.Location"));
				restaurant.setImgRestaurant(rs.getString("r.img"));
				listRestaurant.add(restaurant);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorDisplayRestaurantList", e.getMessage());
		} finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(DisplayRestaurantList)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(DisplayRestaurantList)", e.getMessage());
                }
            }
        }
		return listRestaurant;
	}
	
	//danh sách nhà hàng theo filter
	public static Restaurant getRestaurant(HttpServletRequest request, Connection conn, int userId){
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select r.*, u.nameUser, u.nameLogin from restaurant r "
				+ "join user u on r.userId = u.userId "
				+ "where u.typeOfUser = 2 and u.userId = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, userId);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(rs.getInt("r.RestaurantId"));
				restaurant.setRestaurantOwnerId(rs.getInt("r.userId"));
				restaurant.setRestaurantOwnerFullName(rs.getString("u.nameUser"));
				restaurant.setRestaurantOwnerLoginName(rs.getString("u.nameLogin"));
				restaurant.setRestaurantName(rs.getString("r.Name"));
				restaurant.setCuisineTypeDESC(rs.getString("r.CuisineTypeDESC"));
				restaurant.setRestaurantLocation(rs.getString("r.Location"));
				restaurant.setImgRestaurant(rs.getString("r.img"));
				
				return restaurant;
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorGetRestaurant", e.getMessage());
		} finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing PreparedStatement(getRestaurant)", e.getMessage());
                }
            }
            if (rs != null) {
                try {
                	rs.close();
                } catch (SQLException e) {
                    ErrorDAO.insertError(conn, "Error closing ResultSet(getRestaurant)", e.getMessage());
                }
            }
        }
		return null;
	}
		
}
