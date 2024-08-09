package BEAN;

public class Restaurant {
	private int restaurantId;
	private int restaurantOwnerId;
	private String restaurantOwnerLoginName;
	private String restaurantOwnerFullName;
	private String restaurantName;
	private String restaurantLocation;
	private String cuisineTypeDESC;
	private int totalItemSalesCount;
	private String phoneRestaurant;
	private String imgRestaurant;
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getImgRestaurant() {
		return imgRestaurant;
	}

	public void setImgRestaurant(String imgRestaurant) {
		this.imgRestaurant = imgRestaurant;
	}

	public String getPhoneRestaurant() {
		return phoneRestaurant;
	}

	public void setPhoneRestaurant(String phoneRestaurant) {
		this.phoneRestaurant = phoneRestaurant;
	}

	public int getTotalItemSalesCount() {
		return totalItemSalesCount;
	}

	public void setTotalItemSalesCount(int totalItemSalesCount) {
		this.totalItemSalesCount = totalItemSalesCount;
	}

	public String getRestaurantOwnerLoginName() {
		return restaurantOwnerLoginName;
	}

	public void setRestaurantOwnerLoginName(String restaurantOwnerLoginName) {
		this.restaurantOwnerLoginName = restaurantOwnerLoginName;
	}

	public String getRestaurantOwnerFullName() {
		return restaurantOwnerFullName;
	}

	public void setRestaurantOwnerFullName(String restaurantOwnerFullame) {
		this.restaurantOwnerFullName = restaurantOwnerFullame;
	}

	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public int getRestaurantOwnerId() {
		return restaurantOwnerId;
	}
	public void setRestaurantOwnerId(int restaurantOwnerId) {
		this.restaurantOwnerId = restaurantOwnerId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantLocation() {
		return restaurantLocation;
	}
	public void setRestaurantLocation(String restaurantLocation) {
		this.restaurantLocation = restaurantLocation;
	}
	public String getCuisineTypeDESC() {
		return cuisineTypeDESC;
	}
	public void setCuisineTypeDESC(String cuisineTypeDESC) {
		this.cuisineTypeDESC = cuisineTypeDESC;
	}
}
