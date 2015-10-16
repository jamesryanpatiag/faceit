package com.group3.faceit.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group3.faceit.model.connections.ConnectionsModel;

public class ConnectionsDAO {
	public static PreparedStatement pst;
	public static CallableStatement cst;
	public static ResultSet rs;
	public static String query = "";
	
	public List<ConnectionsModel> getFriends(int sessionUserId, Connection conn){
		List<ConnectionsModel> connect = new ArrayList<ConnectionsModel>();
		
		query = "{CALL getConnections(?)}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
			cst.setInt(1, sessionUserId);
			rs = cst.executeQuery();
			while (rs.next()){
				ConnectionsModel modelconn = new ConnectionsModel();
				modelconn.setAddress(rs.getString("address"));
				modelconn.setFullname(rs.getString("fullname"));
				modelconn.setId(rs.getInt("id"));
				connect.add(modelconn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	
	public List<ConnectionsModel> getAllUsers(String search, Connection conn){
		List<ConnectionsModel> connect = new ArrayList<ConnectionsModel>();
		
		query = "{CALL getAllUsers(?)}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
			cst.setString(1, search);
			rs = cst.executeQuery();
			while (rs.next()){
				ConnectionsModel modelconn = new ConnectionsModel();
				modelconn.setAddress(rs.getString("address"));
				modelconn.setFullname(rs.getString("fullname"));
				modelconn.setId(rs.getInt("id"));
				connect.add(modelconn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	
	public int checkIfConnected(int sessionUserId, int userId, Connection conn){
		int count = 0;	
		query = "SELECT COUNT(*) FROM connections WHERE ((user_id_one = ? AND user_id_two = ?) OR (user_id_two = ? AND user_id_one = ?)) AND status='ACTIVE'";
		
		try {
			pst = conn.prepareCall(query);
			pst.setInt(1, sessionUserId);
			pst.setInt(2, userId);
			pst.setInt(3, userId);
			pst.setInt(4, sessionUserId);
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
	

}
