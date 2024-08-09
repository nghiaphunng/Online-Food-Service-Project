package BEAN;

public class Item {
	private int itemId;
	private int restaurantId;
	private String name;
	private String description;
	private int price;
	private int itemType; // 0: táta cả   1: ăn   2:uống 3:cả ăn và uống
	private String searchSuggestion;
	private String image;
	private int numberOfItem;
	private int salesCount;
	private String locationRestaurant;
	private String nameRestaurant;
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNameRestaurant() {
		return nameRestaurant;
	}

	public void setNameRestaurant(String nameRestaurant) {
		this.nameRestaurant = nameRestaurant;
	}

	public String getLocationRestaurant() {
		return locationRestaurant;
	}

	public void setLocationRestaurant(String locationRestaurant) {
		this.locationRestaurant = locationRestaurant;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public String getSearchSuggestion() {
		return searchSuggestion;
	}
	public void setSearchSuggestion(String searchSuggestion) {
		this.searchSuggestion = searchSuggestion;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}
	public int getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}
}
