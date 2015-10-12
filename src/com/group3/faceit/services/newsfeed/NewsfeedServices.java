package com.group3.faceit.services.newsfeed;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.*;
import com.group3.faceit.model.newsfeed.CommentModel;
import com.group3.faceit.model.newsfeed.PostModel;

public class NewsfeedServices extends AbstractDAO {

	private NewsfeedDAO newsfeed = null;

	public NewsfeedServices() {
		newsfeed = new NewsfeedDAO();
	}
	
	public void savePost(String description) {
		try {
			Connection con = getConnection();
			newsfeed.savePost(description, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<PostModel> getPosts(int user_id) {
		List<PostModel> posts = new ArrayList<PostModel>();
		try {
			Connection con = getConnection();
			posts = newsfeed.getPosts(user_id, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}
	
	
	public void likePost(int postId) {
		try {
			Connection con = getConnection();
			newsfeed.likePost(postId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getLikeId(int postId, int userId) {
		int id = 0;
		try {
			Connection con = getConnection();
			id = newsfeed.getLikeId(postId, userId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void updateLike(int likeId) {
		try {
			Connection con = getConnection();
			newsfeed.updateLike(likeId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int checkLike(int postId, int userId) {
		int count = 0;
		try {
			Connection con = getConnection();
			count = newsfeed.checkLike(postId, userId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public String getLikeStatus(int postId) {
		String status = "";
		try {
			Connection con = getConnection();
			status = newsfeed.getLikeStatus(postId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public int countLikes(int postId) {
		int count = 0;
		try {
			Connection con = getConnection();
			count = newsfeed.countLikes(postId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	//COMMENTS
	public List<CommentModel> getComments(int post_id) {
		List<CommentModel> comments = new ArrayList<CommentModel>();
		try {
			Connection con = getConnection();
			comments = newsfeed.getComments(post_id, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	
	public void commentPost(int postId, String description) {
		try {
			Connection con = getConnection();
			newsfeed.commentPost(postId, description, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createHistory(String message, Connection conn) {
		try {
			Connection con = getConnection();
			newsfeed.createHistory(message, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
