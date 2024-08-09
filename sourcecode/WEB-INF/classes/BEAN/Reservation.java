package BEAN;

public class Reservation {
	private int ReservationId;
	private int CustomerId;
	private int RestaurantId;
	private int NumberOfGuests;
	private String ReservationDate;
	private String AdditionalReminder;
	private String fullNameCustomer;
	private String NameRestaurant;
	private String phoneRestaurant;
	private String phoneCustomer;
	int Status; //0 : nhà hàng chưa xác nhận   1: nhà hàng đã xác nhận
	private String locationRestaurant;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLocationRestaurant() {
		return locationRestaurant;
	}

	public void setLocationRestaurant(String locationRestaurant) {
		this.locationRestaurant = locationRestaurant;
	}

	public String getPhoneCustomer() {
		return phoneCustomer;
	}

	public void setPhoneCustomer(String phoneCustomer) {
		this.phoneCustomer = phoneCustomer;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getNameRestaurant() {
		return NameRestaurant;
	}

	public void setNameRestaurant(String nameRestaurant) {
		NameRestaurant = nameRestaurant;
	}

	public String getPhoneRestaurant() {
		return phoneRestaurant;
	}

	public void setPhoneRestaurant(String phoneRestaurant) {
		this.phoneRestaurant = phoneRestaurant;
	}

	public int getReservationId() {
		return ReservationId;
	}
	public void setReservationId(int reservationId) {
		ReservationId = reservationId;
	}
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}
	public int getRestaurantId() {
		return RestaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		RestaurantId = restaurantId;
	}
	public int getNumberOfGuests() {
		return NumberOfGuests;
	}
	public void setNumberOfGuests(int numberOfGuests) {
		NumberOfGuests = numberOfGuests;
	}
	public String getReservationDate() {
		return ReservationDate;
	}
	public void setReservationDate(String reservationDate) {
		ReservationDate = reservationDate;
	}
	public String getAdditionalReminder() {
		return AdditionalReminder;
	}
	public void setAdditionalReminder(String additionalReminder) {
		AdditionalReminder = additionalReminder;
	}
	public String getFullNameCustomer() {
		return fullNameCustomer;
	}
	public void setFullNameCustomer(String fullNameCustomer) {
		this.fullNameCustomer = fullNameCustomer;
	}
}
