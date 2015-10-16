package com.group3.faceit.services.profile;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.*;
import com.group3.faceit.model.newsfeed.CommentModel;
import com.group3.faceit.model.newsfeed.PostModel;
import com.group3.faceit.model.user.UserModel;

public class ProfilePageServices extends AbstractDAO {
	
	private profilesDAO profileDAO = null;
	private UserDAO userDAO = null;

	public ProfilePageServices() {
		profileDAO = new profilesDAO();
		userDAO = new UserDAO();
	}
	
	public UserModel getUser(int sessionUserId) {
		UserModel user = new UserModel();
		try {
			Connection con = getConnection();
			user = userDAO.getUserProfileByUserId(sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	
	public void savePost(String description, int sessionUserId) {
		try {
			Connection con = getConnection();
			profileDAO.savePost(description, sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePost(int postId, int sessionUserId, String description) {
		try {
			Connection con = getConnection();
			profileDAO.updatePost(postId, sessionUserId, description, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePost(int postId, int sessionUserId) {
		try {
			Connection con = getConnection();
			profileDAO.deletePost(postId, sessionUserId, con);
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
			posts = profileDAO.getOwnPosts(sessionUserId, con);
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
			profileDAO.saveLikePost(postId, sessionUserId, con);
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
			count = profileDAO.countLikePost(postId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int checkLikePost(int postId, int sessionUserId) {
		int count = 0;
		try {
			Connection con = getConnection();
			count = profileDAO.checkLikePost(postId, sessionUserId, con);
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
			comments = profileDAO.getComments(postId, con);
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
			profileDAO.saveComment(postId, sessionUserId, description, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateComment(int commentId, int sessionUserId, String description) {
		try {
			Connection con = getConnection();
			profileDAO.updateComment(commentId, sessionUserId, description, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteComment(int commentId, int sessionUserId) {
		try {
			Connection con = getConnection();
			profileDAO.deleteComment(commentId, sessionUserId, con);
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
			count = profileDAO.countComments(postId, con);
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
			profileDAO.saveLikeComment(commentId, sessionUserId, con);
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
			count = profileDAO.countLikeComment(commentId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int checkLikeComment(int commentId, int sessionUserId) {
		int count = 0;
		try {
			Connection con = getConnection();
			count = profileDAO.checkLikeComment(commentId, sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
