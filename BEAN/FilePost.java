package BEAN;

public class FilePost {
	private int fileId;
	private int postId;
	private String url;
	private String createdAt;
	public FilePost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FilePost(int fileId, int postId, String url, String createdAt) {
		super();
		this.fileId = fileId;
		this.postId = postId;
		this.url = url;
		this.createdAt = createdAt;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
