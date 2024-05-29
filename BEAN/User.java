package BEAN;

public class User {
	private int userId;
	private String fullName;
	private String userName;
	private String birthday;
	private String userEmail;
	private String userPassWord;
	private int userCategoryId;
	private int userMoney;
	private int gender;
	private String userAvatarURL;
	private String createdAt;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int userId, String fullName, String userName, String birthday, String userEmail, String userPassWord,
			int userCategoryId, int userMoney, int gender, String userAvatarURL, String createdAt) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.birthday = birthday;
		this.userEmail = userEmail;
		this.userPassWord = userPassWord;
		this.userCategoryId = userCategoryId;
		this.userMoney = userMoney;
		this.gender = gender;
		this.userAvatarURL = userAvatarURL;
		this.createdAt = createdAt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassWord() {
		return userPassWord;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	public int getUserCategoryId() {
		return userCategoryId;
	}
	public void setUserCategoryId(int userCategoryId) {
		this.userCategoryId = userCategoryId;
	}
	public int getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(int userMoney) {
		this.userMoney = userMoney;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getUserAvatarURL() {
		return userAvatarURL;
	}
	public void setUserAvatarURL(String userAvatarURL) {
		this.userAvatarURL = userAvatarURL;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
