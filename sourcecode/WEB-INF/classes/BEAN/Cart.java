package BEAN;

public class Cart {
	private int cartId;
	private int itemId;
	private String nameRestaurant;
	private String nameItem;
	private String urlItem;
	private int priceItem;
	private int quantityItem;
	private String fullNameUser;
	private String phoneUser;
	private String addressUser;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFullNameUser() {
		return fullNameUser;
	}

	public void setFullNameUser(String fullNameUser) {
		this.fullNameUser = fullNameUser;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}

	public String getAddressUser() {
		return addressUser;
	}

	public void setAddressUser(String addressUser) {
		this.addressUser = addressUser;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getNameRestaurant() {
		return nameRestaurant;
	}
	public void setNameRestaurant(String nameRestaurant) {
		this.nameRestaurant = nameRestaurant;
	}
	public String getNameItem() {
		return nameItem;
	}
	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}
	public String getUrlItem() {
		return urlItem;
	}
	public void setUrlItem(String urlItem) {
		this.urlItem = urlItem;
	}
	public int getPriceItem() {
		return priceItem;
	}
	public void setPriceItem(int priceItem) {
		this.priceItem = priceItem;
	}
	public int getQuantityItem() {
		return quantityItem;
	}
	public void setQuantityItem(int quantityItem) {
		this.quantityItem = quantityItem;
	}
}
