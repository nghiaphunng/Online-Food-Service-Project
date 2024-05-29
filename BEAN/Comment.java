package BEAN;

public class Comment {
	private int commentId;
	private int postId;
	private String userName;
	private int userId;
	private String userAvatarURL;
	private String createdAt;
	private String content;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int commentId, int postId, String userName, int userId, String userAvatarURL, String createdAt,
			String content) {
		super();
		this.commentId = commentId;
		this.postId = postId;
		this.userName = userName;
		this.userId = userId;
		this.userAvatarURL = userAvatarURL;
		this.createdAt = createdAt;
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAvatarURL() {
		return userAvatarURL;
	}

	public void setUserAvatarURL(String userAvatarURL) {
		this.userAvatarURL = userAvatarURL;
	}

	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
