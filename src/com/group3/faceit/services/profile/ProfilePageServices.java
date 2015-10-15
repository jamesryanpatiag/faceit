package com.group3.faceit.services.profile;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group3.faceit.common.AbstractDAO;
import com.group3.faceit.dao.*;
import com.group3.faceit.model.newsfeed.CommentModel;
import com.group3.faceit.model.newsfeed.PostModel;

public class ProfilePageServices extends AbstractDAO {
	private profilesDAO ProfileDAO = null;

	public ProfilePageServices() {
		ProfileDAO = new profilesDAO();
	}
	
	public List<PostModel> getPosts(int sessionUserId) {
		List<PostModel> posts = new ArrayList<PostModel>();
		try {
			Connection con = getConnection();
			posts = ProfileDAO.getPosts(sessionUserId, con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}
}
