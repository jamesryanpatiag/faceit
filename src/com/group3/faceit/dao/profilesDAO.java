package com.group3.faceit.dao;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.group3.faceit.model.newsfeed.PostModel;

public class profilesDAO {
	public static PreparedStatement pst;
	public static CallableStatement cst;
	public static ResultSet rs;
	public static String query = "";
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static final JDialog dialog = new JDialog();
	
	
	public List<PostModel> getPosts(int sessionUserId, Connection conn){
		List<PostModel> posts = new ArrayList<PostModel>();
		
		query = "{select * from posts where user_id_to=2}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
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
}
