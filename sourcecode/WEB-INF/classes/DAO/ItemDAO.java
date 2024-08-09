package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Item;
import BEAN.Restaurant;

public class ItemDAO {
	public static List<Item> listItemDefaultHome(HttpServletRequest request, Connection conn, int typeItem){
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";		
		try {		
			if(typeItem == 0) { //cả ăn và uống
				sql  = "select i.*, r.Location, r.Name from item i "
						+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
						+ "order by i.SalesCount desc, i.ItemId desc";
			}
			else if(typeItem == 1 || typeItem == 2) { //ăn hoặc uống
				sql  = "select i.*, r.Location, r.Name from item i "
						+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
						+ "where i.ItemType = ?  or i.ItemType = 3 "
						+ "order by i.SalesCount desc, i.ItemId desc";
			}
			else if(typeItem == 3) { //chỉ combo
				sql  = "select i.*, r.Location, r.Name from item i "
						+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
						+ "where i.ItemType = 3 "
						+ "order by i.SalesCount desc, i.ItemId desc";
			}
			ptmt = conn.prepareStatement(sql);
			if(typeItem == 1 || typeItem == 2) {
				ptmt.setInt(1, typeItem);
			}
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.ItemId"));
				item.setRestaurantId(rs.getInt("i.RestaurantId"));
				item.setName(rs.getString("i.Name"));
				item.setDescription(rs.getString("i.Description"));
				item.setPrice(rs.getInt("i.Price"));
				item.setItemType(rs.getInt("i.ItemType"));
				item.setSearchSuggestion(rs.getString("i.SearchSuggestion"));
				item.setImage(rs.getString("i.Image"));
				item.setNumberOfItem(rs.getInt("i.NumberOfItem"));
				item.setSalesCount(rs.getInt("i.SalesCount"));
				item.setLocationRestaurant(rs.getString("r.Location"));
				item.setNameRestaurant(rs.getString("r.Name"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnListItemDefaultHome", e.getMessage());
		}
		return itemList;
	}
	
	public static List<Item> SearchItemFilter(HttpServletRequest request, Connection conn, String location, String nameInput, int typeItem, int minPrice, int maxPrice){
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select i.*, r.Location, r.Name from item i "
				+ "join restaurant r ON i.RestaurantId = r.RestaurantId ";		
		try {		
			if(typeItem == 0) { //cả ăn và uống và combo
				if (nameInput != null && !nameInput.trim().isEmpty()) {
	                sql += "where (r.Location like ?) and (i.Price between ? and ?) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) ";
	            }
				else {
					sql += "where (r.Location like ?) and (i.Price between ? and ?) ";
				}
			}
			else if(typeItem == 1 || typeItem == 2) { //ăn hoặc uống
				if (nameInput != null && !nameInput.trim().isEmpty()) {
	                sql += "where (r.Location like ?) and (i.Price between ? and ?) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) and (i.ItemType = ? or i.ItemType = 3) ";
	            }
				else {
					sql += "where (r.Location like ?) and (i.Price between ? and ?) and (i.ItemType = ? or i.ItemType = 3) ";
				}
			}
			else if(typeItem == 3) { //chỉ combo
				if (nameInput != null && !nameInput.trim().isEmpty()) {
	                sql += "where (r.Location like ?) and (i.Price between ? and ?) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) and (i.ItemType = 3) ";
	            }
				else {
					sql += "where (r.Location like ?) and (i.Price between ? and ?) and (i.ItemType = 3) ";
				}
			}
			
			sql += "order by i.SalesCount desc, i.ItemId desc";		
			ptmt = conn.prepareStatement(sql);
			
			int cntParameter = 1;
			ptmt.setString(cntParameter++, "%" + location + "%");
			ptmt.setInt(cntParameter++, minPrice);
			ptmt.setInt(cntParameter++, maxPrice);
			
			if (nameInput != null && !nameInput.trim().isEmpty()) {
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
			}
			
			if(typeItem == 1 || typeItem == 2) {
				ptmt.setInt(cntParameter++, typeItem);
			}
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.ItemId"));
				item.setRestaurantId(rs.getInt("i.RestaurantId"));
				item.setName(rs.getString("i.Name"));
				item.setDescription(rs.getString("i.Description"));
				item.setPrice(rs.getInt("i.Price"));
				item.setItemType(rs.getInt("i.ItemType"));
				item.setSearchSuggestion(rs.getString("i.SearchSuggestion"));
				item.setImage(rs.getString("i.Image"));
				item.setNumberOfItem(rs.getInt("i.NumberOfItem"));
				item.setSalesCount(rs.getInt("i.SalesCount"));
				item.setLocationRestaurant(rs.getString("r.Location"));
				item.setNameRestaurant(rs.getString("r.Name"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnSearchItem", e.getMessage());
		}
		return itemList;
	}
	
	public static List<Item> SearchItemFilterByLowerBoundPrice(HttpServletRequest request, Connection conn, String location, String nameInput, int typeItem, int lowerBoundPrice){
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select i.*, r.Location, r.Name from item i "
				+ "join restaurant r ON i.RestaurantId = r.RestaurantId ";		
		try {		
			if(typeItem == 0) { //cả ăn và uống và combo
				if (nameInput != null && !nameInput.trim().isEmpty()) {
					sql += "where (r.Location like ?) and (i.Price < ?) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) ";
				}
				else {
					sql += "where (r.Location like ?) and (i.Price < ?) ";
				}
			}
			else if(typeItem == 1 || typeItem == 2) { //ăn hoặc uống
				if (nameInput != null && !nameInput.trim().isEmpty()) {
					sql += "where (r.Location like ?) and (i.Price < ?) and (i.ItemType = ? or i.ItemType = 3) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) ";
				}
				else {
					sql += "where (r.Location like ?) and (i.Price < ?) and (i.ItemType = ? or i.ItemType = 3) ";
				}
			}
			else if(typeItem == 3) { //chỉ combo
				if (nameInput != null && !nameInput.trim().isEmpty()) {
					sql += "where (r.Location like ?) and (i.Price < ?) and (i.ItemType = 3) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) ";
				}
				else {
					sql += "where (r.Location like ?) and (i.Price < ?) and (i.ItemType = 3) ";
				}
			}
			
			sql += "order by i.SalesCount desc, i.ItemId desc";
			ptmt = conn.prepareStatement(sql);
			
			int cntParameter = 1;
			ptmt.setString(cntParameter++, "%" + location + "%");
			ptmt.setInt(cntParameter++, lowerBoundPrice);
			
			if(typeItem == 1 || typeItem == 2) {
				ptmt.setInt(cntParameter++, typeItem);
			}
			
			if (nameInput != null && !nameInput.trim().isEmpty()) {
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
			}
			
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.ItemId"));
				item.setRestaurantId(rs.getInt("i.RestaurantId"));
				item.setName(rs.getString("i.Name"));
				item.setDescription(rs.getString("i.Description"));
				item.setPrice(rs.getInt("i.Price"));
				item.setItemType(rs.getInt("i.ItemType"));
				item.setSearchSuggestion(rs.getString("i.SearchSuggestion"));
				item.setImage(rs.getString("i.Image"));
				item.setNumberOfItem(rs.getInt("i.NumberOfItem"));
				item.setSalesCount(rs.getInt("i.SalesCount"));
				item.setLocationRestaurant(rs.getString("r.Location"));
				item.setNameRestaurant(rs.getString("r.Name"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnSearchItemByLowerBoundPrice", e.getMessage());
		}
		return itemList;
	}
	
	public static List<Item> SearchItemFilterByUpperBoundPrice(HttpServletRequest request, Connection conn, String location, String nameInput, int typeItem, int upperBoundPrice){
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select i.*, r.Location, r.Name from item i "
				+ "join restaurant r ON i.RestaurantId = r.RestaurantId ";		
		try {		
			if(typeItem == 0) { //cả ăn và uống và combo
				if (nameInput != null && !nameInput.trim().isEmpty()) {
					sql += "where (r.Location like ?) and (i.Price > ?) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) ";
				}
				else {
					sql += "where (r.Location like ?) and (i.Price > ?) ";
				}
			}
			else if(typeItem == 1 || typeItem == 2) { //ăn hoặc uống
				if (nameInput != null && !nameInput.trim().isEmpty()) {
					sql += "where (r.Location like ?) and (i.Price > ?) and (i.ItemType = ? or i.ItemType = 3) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) ";
				}
				else {
					sql += "where (r.Location like ?) and (i.Price > ?) and (i.ItemType = ? or i.ItemType = 3) ";
				}
			}
			else if(typeItem == 3) { //chỉ combo
				if (nameInput != null && !nameInput.trim().isEmpty()) {
					sql += "where (r.Location like ?) and (i.Price > ?) and (i.ItemType = 3) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) ";
				}
				else {
					sql += "where (r.Location like ?) and (i.Price > ?) and (i.ItemType = 3) ";
				}
			}
			
			sql += "order by i.SalesCount desc, i.ItemId desc";
			ptmt = conn.prepareStatement(sql);
			
			int cntParameter = 1;
			ptmt.setString(cntParameter++, "%" + location + "%");
			ptmt.setInt(cntParameter++, upperBoundPrice);
			
			if(typeItem == 1 || typeItem == 2) {
				ptmt.setInt(cntParameter++, typeItem);
			}
			
			if (nameInput != null && !nameInput.trim().isEmpty()) {
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
				ptmt.setString(cntParameter++, "%" + nameInput + "%");
			}
			
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.ItemId"));
				item.setRestaurantId(rs.getInt("i.RestaurantId"));
				item.setName(rs.getString("i.Name"));
				item.setDescription(rs.getString("i.Description"));
				item.setPrice(rs.getInt("i.Price"));
				item.setItemType(rs.getInt("i.ItemType"));
				item.setSearchSuggestion(rs.getString("i.SearchSuggestion"));
				item.setImage(rs.getString("i.Image"));
				item.setNumberOfItem(rs.getInt("i.NumberOfItem"));
				item.setSalesCount(rs.getInt("i.SalesCount"));
				item.setLocationRestaurant(rs.getString("r.Location"));
				item.setNameRestaurant(rs.getString("r.Name"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnSearchItemByUpperBoundPrice", e.getMessage());
		}
		return itemList;
	}
	
	public static List<Item> SearchItemByLocation(HttpServletRequest request, Connection conn, String location, int typeItem){
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql  = "select i.*, r.Location, r.Name from item i "
				+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
				+ "where (r.Location like ?) and (i.ItemType = ?  or i.ItemType = 3) "
				+ "order by i.SalesCount desc, i.ItemId desc";	
		try {
			if(typeItem == 0) { //cả ăn và uống
				sql  = "select i.*, r.Location, r.Name from item i "
						+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
						+ "where (r.Location like ?) "
						+ "order by i.SalesCount desc, i.ItemId desc";
			}
			else if(typeItem == 1 || typeItem == 2) { //ăn hoặc uống
				sql = "select i.*, r.Location, r.Name from item i "
				+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
				+ "where (r.Location like ?) and (i.ItemType = ?  or i.ItemType = 3) "
				+ "order by i.SalesCount desc, i.ItemId desc";
			}
			else if(typeItem == 3) { //chỉ combo
				sql  = "select i.*, r.Location, r.Name from item i "
						+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
						+ "where (r.Location like ?) and (i.ItemType = 3) "
						+ "order by i.SalesCount desc, i.ItemId desc";
			}
			
			ptmt = conn.prepareStatement(sql);
			
			ptmt.setString(1, "%" + location + "%");
			
			if(typeItem == 1 || typeItem == 2) {
				ptmt.setInt(2, typeItem);
			}
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.ItemId"));
				item.setRestaurantId(rs.getInt("i.RestaurantId"));
				item.setName(rs.getString("i.Name"));
				item.setDescription(rs.getString("i.Description"));
				item.setPrice(rs.getInt("i.Price"));
				item.setItemType(rs.getInt("i.ItemType"));
				item.setSearchSuggestion(rs.getString("i.SearchSuggestion"));
				item.setImage(rs.getString("i.Image"));
				item.setNumberOfItem(rs.getInt("i.NumberOfItem"));
				item.setSalesCount(rs.getInt("i.SalesCount"));
				item.setLocationRestaurant(rs.getString("r.Location"));
				item.setNameRestaurant(rs.getString("r.Name"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnSearchItemByLocation", e.getMessage());
		}
		return itemList;
	}
	
	public static List<Item> SearchItemByNameInput(HttpServletRequest request, Connection conn, String location, String nameInput, int typeItem){
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql  = "";	
		try {
			if(typeItem == 0) { //cả ăn và uống
				sql  = "select i.*, r.Location, r.Name from item i "
						+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
						+ "where (r.Location like ?) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) "
						+ "order by i.SalesCount desc, i.ItemId desc";
			}
			else if(typeItem == 1 || typeItem == 2) { //ăn hoặc uống
				sql = "select i.*, r.Location, r.Name from item i "
				+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
				+ "where (r.Location like ?) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) and (i.ItemType = ?  or i.ItemType = 3) "
				+ "order by i.SalesCount desc, i.ItemId desc";
			}
			else if(typeItem == 3) { //chỉ combo
				sql  = "select i.*, r.Location, r.Name from item i "
						+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
						+ "where (r.Location like ?) and (i.Name like ? or i.Description like ? or i.SearchSuggestion like ? or r.Name like ?) and (i.ItemType = 3) "
						+ "order by i.SalesCount desc, i.ItemId desc";
			}
			
			ptmt = conn.prepareStatement(sql);
			
			ptmt.setString(1, "%" + location + "%");
			ptmt.setString(2, "%" + nameInput + "%");
			ptmt.setString(3, "%" + nameInput + "%");
			ptmt.setString(4, "%" + nameInput + "%");
			ptmt.setString(5, "%" + nameInput + "%");
			
			if(typeItem == 1 || typeItem == 2) {
				ptmt.setInt(6, typeItem);
			}
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.ItemId"));
				item.setRestaurantId(rs.getInt("i.RestaurantId"));
				item.setName(rs.getString("i.Name"));
				item.setDescription(rs.getString("i.Description"));
				item.setPrice(rs.getInt("i.Price"));
				item.setItemType(rs.getInt("i.ItemType"));
				item.setSearchSuggestion(rs.getString("i.SearchSuggestion"));
				item.setImage(rs.getString("i.Image"));
				item.setNumberOfItem(rs.getInt("i.NumberOfItem"));
				item.setSalesCount(rs.getInt("i.SalesCount"));
				item.setLocationRestaurant(rs.getString("r.Location"));
				item.setNameRestaurant(rs.getString("r.Name"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnSearchItemByLocation", e.getMessage());
		}
		return itemList;
	}
	
	//sản phẩm theo restaurantId theo loại sản phẩm
	public static List<Item> listItemRestaurantId(HttpServletRequest request, Connection conn, int restaurantId, int typeItem){
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select i.*, r.Location, r.Name from item i "
				+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
				+ "where r.RestaurantId = ? and i.ItemType = ? "
				+ "order by i.SalesCount desc, i.ItemId desc";	
		try {		
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, restaurantId);
			ptmt.setInt(2,  typeItem);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.ItemId"));
				item.setRestaurantId(rs.getInt("i.RestaurantId"));
				item.setName(rs.getString("i.Name"));
				item.setDescription(rs.getString("i.Description"));
				item.setPrice(rs.getInt("i.Price"));
				item.setItemType(rs.getInt("i.ItemType"));
				item.setSearchSuggestion(rs.getString("i.SearchSuggestion"));
				item.setImage(rs.getString("i.Image"));
				item.setNumberOfItem(rs.getInt("i.NumberOfItem"));
				item.setSalesCount(rs.getInt("i.SalesCount"));
				item.setLocationRestaurant(rs.getString("r.Location"));
				item.setNameRestaurant(rs.getString("r.Name"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnListItemRestaurantId", e.getMessage());
		}
		return itemList;
	}
	
	//sản phẩm theo restaurantId (tất cả)
	public static List<Item> listTotalItemRestaurantId(HttpServletRequest request, Connection conn, int restaurantId){
		List<Item> itemList = new ArrayList<Item>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select i.*, r.Name from item i "
				+ "join restaurant r ON i.RestaurantId = r.RestaurantId "
				+ "where r.RestaurantId = ?";
		try {		
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, restaurantId);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("i.ItemId"));
				item.setRestaurantId(rs.getInt("i.RestaurantId"));
				item.setName(rs.getString("i.Name"));
				item.setDescription(rs.getString("i.Description"));
				item.setPrice(rs.getInt("i.Price"));
				item.setItemType(rs.getInt("i.ItemType"));
				item.setSearchSuggestion(rs.getString("i.SearchSuggestion"));
				item.setImage(rs.getString("i.Image"));
				item.setNumberOfItem(rs.getInt("i.NumberOfItem"));
				item.setSalesCount(rs.getInt("i.SalesCount"));
				item.setNameRestaurant(rs.getString("r.Name"));
				itemList.add(item);
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnListTotalItemRestaurantId", e.getMessage());
		}
		return itemList;
	}
		
	//trả về thông tin của restaurantId;
	public static Restaurant getRestaurant(Connection conn, int restaurantId) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Restaurant restaurant = new Restaurant();
		String sql = "SELECT r.*, SUM(i.SalesCount) AS totalSalesCount, u.nameUser, u.phoneUser FROM restaurant r "
				+ "JOIN item i ON r.RestaurantId = i.RestaurantId "
				+ "JOIN user u ON r.userId = u.userId "
				+ "WHERE r.RestaurantId = ?";
		try {		
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, restaurantId);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				restaurant.setCuisineTypeDESC(rs.getString("r.CuisineTypeDESC"));
				restaurant.setRestaurantId(rs.getInt("r.RestaurantId"));
				restaurant.setRestaurantLocation(rs.getString("r.Location"));
				restaurant.setRestaurantName(rs.getString("r.Name"));
				restaurant.setTotalItemSalesCount(rs.getInt("totalSalesCount"));
				restaurant.setRestaurantOwnerFullName(rs.getString("u.nameUser"));
				restaurant.setPhoneRestaurant(rs.getString("u.phoneUser"));
				restaurant.setImgRestaurant(rs.getString("r.img"));
			}
		} catch (SQLException e) {
			ErrorDAO.insertError(conn, "messageErrorReturnGetRestaurant", e.getMessage());
		}
		return restaurant;
	}
}
