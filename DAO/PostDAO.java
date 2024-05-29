package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class PostDAO {
	public static int findPostId(HttpServletRequest request, Connection conn, int userId, int postOf){
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "select postId from post where postOf = ? and userId = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, postOf);
			ptmt.setInt(2, userId);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				return rs.getInt("postId");
			}
		} catch (SQLException e) {
			request.setAttribute("messageErrorFindPostId", e.getMessage());
		}
		return -1;
	}
}
