package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Comment;

public class CommentDAO {
	public static void insertCommentCourse(HttpServletRequest request, Connection conn, Comment cmt, int userId, int courseId){
		PreparedStatement ptmt = null;
		
		String sql = "insert into comment(courseId,userId,content,createdAt) values (?,?,?,?)";
		try {
			ptmt = conn.prepareStatement(sql);
			String content = cmt.getContent();
			// Lấy thời gian hiện tại và chuyển đổi thành Timestamp
	        Timestamp createAt = Timestamp.from(Instant.now());
			
			ptmt.setInt(1, courseId);
			ptmt.setInt(2,  userId);
			ptmt.setString(3, content);
			ptmt.setTimestamp(4, createAt);
			
			ptmt.executeUpdate();
		}catch(SQLException e) {
			request.setAttribute("messageErrorInsertCommentCourse", e.getMessage());
		}
	}
	
	public static List<Comment> DisplayCommentCourse(HttpServletRequest request, Connection conn, int courseId){
		List<Comment> listCmt = new ArrayList<Comment>();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = 	"select comment.commentId, comment.courseId, comment.userId, profile.fullName, profile.avatarURL, comment.content, comment.createdAt "
					+	"from comment "
					+	"inner join profile on comment.userId = profile.profileId "
					+	"where comment.courseId = ? and comment.postId is null "
					+ 	"order by comment.commentId asc";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, courseId);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				Comment cmt = new Comment();
				cmt.setCommentId(rs.getInt("comment.commentId"));
				cmt.setPostId(rs.getInt("comment.courseId"));
				cmt.setUserName(rs.getString("profile.fullName"));
				cmt.setUserAvatarURL(rs.getString("profile.avatarURL"));
				cmt.setContent(rs.getString("comment.content"));
				cmt.setCreatedAt(rs.getString("comment.createdAt"));
				cmt.setUserId(rs.getInt("comment.userId"));;
				listCmt.add(cmt);
			}
		} catch (SQLException e) {
			request.setAttribute("messageErrorDisplayCommentCourse", e.getMessage());
		}
		return listCmt;
	}
	
	public static void DeleteCommentCourse(HttpServletRequest request, Connection conn, int commentId) {
		PreparedStatement ptmt = null;
		String sql = "delete from comment where commentId = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, commentId);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			request.setAttribute("messageErrorDeleteCommentCourse", e.getMessage());
		}
	}
}
