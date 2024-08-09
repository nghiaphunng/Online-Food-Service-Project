package BEAN;

public class User {
	private int userId;
	private String nameUser;
	private String emailUser;
	private String phoneUser;
	private String address;
	private int gender; // 1: nam 2 :nữ
	private int typeUser; //1: customer    2:restaurant 3:admin cao nhất    4:admin-phân quyền(cấp độ 2)
	private String nameLogin;
	private String passwordUser;
	private String urlAvatar;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUrlAvatar() {
		return urlAvatar;
	}

	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getPhoneUser() {
		return phoneUser;
	}
	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(int typeUser) {
		this.typeUser = typeUser;
	}
	public String getNameLogin() {
		return nameLogin;
	}
	public void setNameLogin(String nameLogin) {
		this.nameLogin = nameLogin;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
}
