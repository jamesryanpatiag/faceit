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
	private static int user_id = 1;	//MUST BE SESSION.USERID
	
	
	//POSTS
	public void savePost(String description, Connection conn){
		query = "INSERT INTO posts (user_id_to, description, status) VALUES (?, ?, ?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, user_id);
			pst.setString(2, description);
			pst.setString(3, "ACTIVE");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<PostModel> getPosts(int user_id, Connection conn){
		List<PostModel> posts = new ArrayList<PostModel>();
		
		query = "{CALL newsfeedPosts}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
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
	
	
	//LIKES
	public void likePost(int postId, Connection conn){
		if (checkLike(postId, user_id, conn) == 0){
			query = "INSERT INTO likes (post_id, user_id, status) VALUES (?, ?, ?)";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setInt(1, postId);
				pst.setInt(2, user_id);
				pst.setString(3, "ACTIVE");
				pst.executeUpdate();
				
				createHistory("{ message:liked, user_id:" + user_id + ", post_id:" + postId + " }", conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			int id = getLikeId(postId, user_id, conn);
			updateLike(id, conn);	//to active
		}
	}
	
	
	public int getLikeId(int postId, int userId, Connection conn){
		int id = 0;	
		query = "SELECT id FROM likes WHERE post_id = ? AND user_id = ?";
		
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
	
	
	public void updateLike(int likeId, Connection conn){ 
		query = "UPDATE likes SET status = 'INACTIVE' WHERE id = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, likeId);
			pst.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int checkLike(int postId, int userId, Connection conn){
		int count = 0;	
		query = "SELECT COUNT(*) FROM likes WHERE post_id = ? AND user_id = ? AND status='ACTIVE'";
		
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
	
	
	public String getLikeStatus(int likeId, Connection conn){
		String status = "";
		query = "SELECT status FROM likes WHERE id = ?";
		
		try {
			pst = conn.prepareCall(query);
			pst.setInt(1, likeId);
			rs = pst.executeQuery();
			while (rs.next()){
				status = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(status);
		return status;
	}
	
	
	
	public int countLikes(int postId, Connection conn){
		int count = 0;
		
		query = "SELECT COUNT(*) FROM likes WHERE post_id = ? AND status='ACTIVE'";
		
		
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
	
	
	//COMMENTS
	public List<CommentModel> getComments(int post_id, Connection conn){
		List<CommentModel> comments = new ArrayList<CommentModel>();
		
		query = "{CALL getComments(?)}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
			cst.setInt(1, post_id);
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
	
	public void commentPost(int postId, String description, Connection conn){
			query = "INSERT INTO comments (post_id, user_id, description, status) VALUES (?, ?, ?, ?)";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setInt(1, postId);
				pst.setInt(2, user_id);
				pst.setString(3, description);
				pst.setString(4, "ACTIVE");
				pst.executeUpdate();
				
				createHistory("{ message:commented, user_id:" + user_id + ", post_id:" + postId + " }", conn);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	//HISTORY
	public void createHistory(String message, Connection conn){
		query = "INSERT INTO history (user_id, message, modifiedby) VALUES (?, ?, ?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, user_id);
			pst.setString(2, message);
			pst.setInt(3, user_id);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
