package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.User;

public class FollowDAO {
	//phương thức trả về số người theo dõi user
	public static int getFollowerCount(HttpServletRequest request, Connection conn, User user) {
		PreparedStatement ptmt = null;
		int userId = user.getUserId();
		String sql = "SELECT COUNT(*) AS followerCount FROM follow WHERE followingId = ?";
		try {
	        ptmt = conn.prepareStatement(sql);
	        ptmt.setInt(1, userId);
	        ResultSet rs = ptmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("followerCount");
	        }
	        
	        ptmt.close();
	        rs.close();
	    } catch (SQLException e) {
	        request.setAttribute("messageErroGetFollower", e.getMessage());
	    }
		return 0;
	}
	
	//phương thức trả về số lượt like từ ban đầu khi tạo tài khoản đến hiện tại của user
	public static int getLikeCountByUserId(HttpServletRequest request, Connection conn, User user) {
		PreparedStatement ptmt = null;
		int userId = user.getUserId();
        String sql = "SELECT postId FROM post WHERE userId = ?";
        int totalLike = 0;
        try {
        	ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, userId);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
            	int postId = rs.getInt("postId");
            	totalLike += getLikeForPost(request, conn, postId);     	
            }
        } catch (SQLException e) {
        	request.setAttribute("messageErroGetLikeCountByUserId", e.getMessage());
        }
        return totalLike;
    }
	
	//phương thức tính số lượt like từ 1 bài post của user
	public static int getLikeForPost(HttpServletRequest request, Connection conn, int postId) {
		PreparedStatement ptmt = null;
		String sql = "SELECT COUNT(*) AS likeCount FROM likepost WHERE postId = ?";
		try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, postId);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("likeCount");
            }
        } catch (SQLException e) {
        	request.setAttribute("messageErroGetLikeForPost", e.getMessage());
        }
		return 0;
	}
}
