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
	
	public void savePost(String description, int sessionUserId) {
		try {
			Connection con = getConnection();
			newsfeed.savePost(description, sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePost(int postId, int sessionUserId, String description) {
		try {
			Connection con = getConnection();
			newsfeed.updatePost(postId, sessionUserId, description, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePost(int postId, int sessionUserId) {
		try {
			Connection con = getConnection();
			newsfeed.deletePost(postId, sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<PostModel> getPosts(int sessionUserId) {
		List<PostModel> posts = new ArrayList<PostModel>();
		try {
			Connection con = getConnection();
			posts = newsfeed.getPosts(sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}
	
	
	public void saveLikePost(int postId, int sessionUserId) {
		try {
			Connection con = getConnection();
			newsfeed.saveLikePost(postId, sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public int countLikePost(int postId) {
		int count = 0;
		try {
			Connection con = getConnection();
			count = newsfeed.countLikePost(postId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
	//COMMENTS
	public List<CommentModel> getComments(int postId) {
		List<CommentModel> comments = new ArrayList<CommentModel>();
		try {
			Connection con = getConnection();
			comments = newsfeed.getComments(postId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	
	public void saveComment(int postId, int sessionUserId, String description) {
		try {
			Connection con = getConnection();
			newsfeed.saveComment(postId, sessionUserId, description, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateComment(int commentId, int sessionUserId, String description) {
		try {
			Connection con = getConnection();
			newsfeed.updateComment(commentId, sessionUserId, description, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteComment(int commentId, int sessionUserId) {
		try {
			Connection con = getConnection();
			newsfeed.deleteComment(commentId, sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int countComments(int postId) {
		int count = 0;
		try {
			Connection con = getConnection();
			count = newsfeed.countComments(postId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
	public void saveLikeComment(int commentId, int sessionUserId) {
		try {
			Connection con = getConnection();
			newsfeed.saveLikeComment(commentId, sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public int countLikeComment(int commentId) {
		int count = 0;
		try {
			Connection con = getConnection();
			count = newsfeed.countLikeComment(commentId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	


}
