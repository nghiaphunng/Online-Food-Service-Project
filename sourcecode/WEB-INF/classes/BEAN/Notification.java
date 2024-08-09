package BEAN;

public class Notification {
	private int notificationId;
	private int senderId;
	private int recipientId;
	private String title;
	private String content;
	private String SendingTime;
	private int status;
	private String nameLoginSender;
	private String fullNameSender;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNameLoginSender() {
		return nameLoginSender;
	}

	public void setNameLoginSender(String nameLoginSender) {
		this.nameLoginSender = nameLoginSender;
	}

	public String getFullNameSender() {
		return fullNameSender;
	}

	public void setFullNameSender(String fullNameSender) {
		this.fullNameSender = fullNameSender;
	}

	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendingTime() {
		return SendingTime;
	}
	public void setSendingTime(String sendingTime) {
		SendingTime = sendingTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
