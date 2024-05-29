package BEAN;

public class Like {
	private int likeId;
	private int userId;
	private String userName;
	private int postId;
	private int commentId;
	private String createdAt;
	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Like(int likeId, int userId, String userName, int postId, int commentId, String createdAt) {
		super();
		this.likeId = likeId;
		this.userId = userId;
		this.userName = userName;
		this.postId = postId;
		this.commentId = commentId;
		this.createdAt = createdAt;
	}
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
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
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
