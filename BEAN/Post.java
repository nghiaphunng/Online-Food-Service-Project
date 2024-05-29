package BEAN;

public class Post {
	private int postId;
	private int userId;
	private String userName;
	private int visibilityId;
	private String title;
	private String content;
	private String createdAt;
	private int countLike;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(int postId, int userId, String userName, int visibilityId, String title, String content,
			String createdAt, int countLike) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.userName = userName;
		this.visibilityId = visibilityId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.countLike = countLike;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getVisibilityId() {
		return visibilityId;
	}
	public void setVisibilityId(int visibilityId) {
		this.visibilityId = visibilityId;
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
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public int getCountLike() {
		return countLike;
	}
	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}
	
}
