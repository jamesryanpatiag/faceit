package com.group3.faceit.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.group3.faceit.model.newsfeed.*;


public class NewsfeedDAO {	
	public static PreparedStatement pst;
	public static CallableStatement cst;
	public static ResultSet rs;
	public static String query = "";
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static final JDialog dialog = new JDialog();
	
	
//POSTS DATA MANIPULATION
	public void savePost(String description, int sessionUserId, Connection conn){
		query = "INSERT INTO posts (user_id_to, description, status) VALUES (?, ?, ?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, sessionUserId);
			pst.setString(2, description);
			pst.setString(3, "ACTIVE");
			pst.executeUpdate();
			int newid = Statement.RETURN_GENERATED_KEYS;
			
			createHistory("{ message:'posted', user_id:" + sessionUserId + ", reference_id:" + newid + " }", sessionUserId, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void updatePost(int postId, int sessionUserId, String description, Connection conn){
		query = "UPDATE posts SET description = ? WHERE id = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, description);
			pst.setInt(2, postId);
			pst.executeUpdate();
			
			createHistory("{ message:'updated post: " + description + "', user_id:" + sessionUserId + ", reference_id:" + postId + " }", sessionUserId, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void deletePost(int postId, int sessionUserId, Connection conn){
		query = "UPDATE posts SET status = 'INACTIVE' WHERE id = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, postId);
			pst.executeUpdate();
			
			createHistory("{ message:'deleted post', user_id:" + sessionUserId + ", reference_id:" + postId + " }", sessionUserId, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<PostModel> getPosts(int sessionUserId, Connection conn){
		List<PostModel> posts = new ArrayList<PostModel>();
		
		query = "{CALL newsfeedPosts(?)}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
			cst.setInt(1, sessionUserId);
			rs = cst.executeQuery();
			while (rs.next()){
				PostModel modelpost = new PostModel();
				modelpost.setDatecreated(rs.getString("datecreated"));
				modelpost.setDescription(rs.getString("description"));
				modelpost.setFullname(rs.getString("fullname"));
				modelpost.setPostid(rs.getInt("postid"));
				posts.add(modelpost);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}
	
	
	public void saveLikePost(int postId, int sessionUserId, Connection conn){
		if (checkLikePost(postId, sessionUserId, conn) == 0){
			query = "INSERT INTO likes_posts (post_id, user_id, status) VALUES (?, ?, ?)";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setInt(1, postId);
				pst.setInt(2, sessionUserId);
				pst.setString(3, "ACTIVE");
				pst.executeUpdate();
				
				createHistory("{ message:liked post, user_id:" + sessionUserId + ", reference_id:" + postId + " }", sessionUserId, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			int id = getLikePostId(postId, sessionUserId, conn);
			updateLikePostStatus(id, conn);	//to active
			createHistory("{ message:unliked post, user_id:" + sessionUserId + ", reference_id:" + postId + " }", sessionUserId, conn);
		}
	}
	
	
	private int getLikePostId(int postId, int userId, Connection conn){
		int id = 0;	
		query = "SELECT id FROM likes_posts WHERE post_id = ? AND user_id = ?";
		
		try {
			pst = conn.prepareCall(query);
			pst.setInt(1, postId);
			pst.setInt(2, userId);
			rs = pst.executeQuery();
			while (rs.next()){
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	
	private void updateLikePostStatus(int likeId, Connection conn){ 
		query = "UPDATE likes_posts SET status = 'INACTIVE' WHERE id = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, likeId);
			pst.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private int checkLikePost(int postId, int userId, Connection conn){
		int count = 0;	
		query = "SELECT COUNT(*) FROM likes_posts WHERE post_id = ? AND user_id = ? AND status='ACTIVE'";
		
		try {
			pst = conn.prepareCall(query);
			pst.setInt(1, postId);
			pst.setInt(2, userId);
			rs = pst.executeQuery();
			while (rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}	
	
	
	
	public int countLikePost(int postId, Connection conn){
		int count = 0;
		
		query = "SELECT COUNT(*) FROM likes_posts WHERE post_id = ? AND status='ACTIVE'";
		
		
		try {
			pst = conn.prepareCall(query);
			pst.setInt(1, postId);
			rs = pst.executeQuery();
			while (rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	

	
//COMMENT DATA MANIPULATION
	public List<CommentModel> getComments(int postId, Connection conn){
		List<CommentModel> comments = new ArrayList<CommentModel>();
		
		query = "{CALL getComments(?)}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
			cst.setInt(1, postId);
			rs = cst.executeQuery();
			while (rs.next()){
				CommentModel modelcomment = new CommentModel();
				modelcomment.setDatecreated(rs.getString("datecreated"));
				modelcomment.setDescription(rs.getString("description"));
				modelcomment.setFullname(rs.getString("fullname"));
				modelcomment.setCommentid(rs.getInt("commentid"));
				comments.add(modelcomment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	public void saveComment(int postId, int sessionUserId, String description, Connection conn){
			query = "INSERT INTO comments (post_id, user_id, description, status) VALUES (?, ?, ?, ?)";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setInt(1, postId);
				pst.setInt(2, sessionUserId);
				pst.setString(3, description);
				pst.setString(4, "ACTIVE");
				pst.executeUpdate();
				
				rs = pst.getGeneratedKeys();
				int newid = 0;
				if (rs.next())
					newid = rs.getInt(1);
				
				createHistory("{ message:'commented', user_id:" + sessionUserId + ", reference_id:" + newid + " }", sessionUserId, conn);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public void updateComment(int commentId, int sessionUserId, String description, Connection conn){
		query = "UPDATE comments SET description = ? WHERE id = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, description);
			pst.setInt(2, commentId);
			pst.executeUpdate();
			
			createHistory("{ message:'updated comment: " + description + "', user_id:" + sessionUserId + ", reference_id:" + commentId + " }", sessionUserId, conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deleteComment(int commentId, int sessionUserId, Connection conn){
		query = "UPDATE comments SET status = 'INACTIVE' WHERE id = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, commentId);
			pst.executeUpdate();
			
			createHistory("{ message:'deleted comment', user_id:" + sessionUserId + ", reference_id:" + commentId + " }", sessionUserId, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


		public void saveLikeComment(int commentId, int sessionUserId, Connection conn){
			if (checkLikeComment(commentId, sessionUserId, conn) == 0){
				query = "INSERT INTO likes_comments (comment_id, user_id, status) VALUES (?, ?, ?)";
				
				try {
					pst = conn.prepareStatement(query);
					pst.setInt(1, commentId);
					pst.setInt(2, sessionUserId);
					pst.setString(3, "ACTIVE");
					pst.executeUpdate();
					
					createHistory("{ message:'liked comment', user_id:" + sessionUserId + ", reference_id:" + commentId + " }", sessionUserId, conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				int id = getLikeCommentId(commentId, sessionUserId, conn);
				updateLikeCommentStatus(id, conn);	//to active
				createHistory("{ message:'unliked comment', user_id:" + sessionUserId + ", reference_id:" + commentId + " }", sessionUserId, conn);
			}
		}
		
		
		private int getLikeCommentId(int commentId, int userId, Connection conn){
			int id = 0;	
			query = "SELECT id FROM likes_comments WHERE comment_id = ? AND user_id = ?";
			
			try {
				pst = conn.prepareCall(query);
				pst.setInt(1, commentId);
				pst.setInt(2, userId);
				rs = pst.executeQuery();
				while (rs.next()){
					id = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return id;
		}
		
		public int countComments(int postId, Connection conn){
			int count = 0;
			
			query = "SELECT COUNT(*) FROM comments WHERE post_id = ? AND status='ACTIVE'";
			
			
			try {
				pst = conn.prepareCall(query);
				pst.setInt(1, postId);
				rs = pst.executeQuery();
				while (rs.next()){
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
		
		
		private void updateLikeCommentStatus(int likeId, Connection conn){ 
			query = "UPDATE likes_comments SET status = 'INACTIVE' WHERE id = ?";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setInt(1, likeId);
				pst.executeUpdate();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		private int checkLikeComment(int commentId, int userId, Connection conn){
			int count = 0;	
			query = "SELECT COUNT(*) FROM likes_comments WHERE comment_id = ? AND user_id = ? AND status='ACTIVE'";
			
			try {
				pst = conn.prepareCall(query);
				pst.setInt(1, commentId);
				pst.setInt(2, userId);
				rs = pst.executeQuery();
				while (rs.next()){
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
		
		
		public int countLikeComment(int commentId, Connection conn){
			int count = 0;
			
			query = "SELECT COUNT(*) FROM likes_comments WHERE comment_id = ? AND status='ACTIVE'";
			
			
			try {
				pst = conn.prepareCall(query);
				pst.setInt(1, commentId);
				rs = pst.executeQuery();
				while (rs.next()){
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
	
	
	
	
//HISTORY
	public void createHistory(String message, int userId, Connection conn){
		query = "INSERT INTO history (user_id, message, modifiedby) VALUES (?, ?, ?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, userId);
			pst.setString(2, message);
			pst.setInt(3, userId);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
