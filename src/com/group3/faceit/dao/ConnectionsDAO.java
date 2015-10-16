package com.group3.faceit.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				modelconn.setStatus(rs.getString("status"));
				modelconn.setFullname(rs.getString("fullname"));
				modelconn.setUserid(rs.getInt("userid"));
				modelconn.setId(rs.getInt("connectionid"));
				connect.add(modelconn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	public List<ConnectionsModel> getPendingConnections(int sessionUserId, Connection conn){
		List<ConnectionsModel> connect = new ArrayList<ConnectionsModel>();
		
		query = "{CALL getPendingConnections(?)}"; //SHOULD HAVE PARAMETER SESSION.USERID
		
		try {
			cst = conn.prepareCall(query);
			cst.setInt(1, sessionUserId);
			rs = cst.executeQuery();
			while (rs.next()){
				ConnectionsModel modelconn = new ConnectionsModel();
				modelconn.setAddress(rs.getString("address"));
				modelconn.setStatus(rs.getString("status"));
				modelconn.setFullname(rs.getString("fullname"));
				modelconn.setUserid(rs.getInt("userid"));
				modelconn.setId(rs.getInt("connectionid"));
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
		query = "SELECT COUNT(*) FROM connections WHERE ((user_id_one = ? AND user_id_two = ?) OR (user_id_two = ? AND user_id_one = ?)) AND status != 'INACTIVE'";
		
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
	
	public String getConnectionStatus(int sessionUserId, int userId, Connection conn){
		String status = "";	
		query = "SELECT status FROM connections WHERE user_id_one = ? AND user_id_two = ?";
		
		try {
			pst = conn.prepareCall(query);;
			pst.setInt(1, userId);
			pst.setInt(2, sessionUserId);
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
	
	
	public int countPendingRequests(int sessionUserId, Connection conn){
		int count = 0;	
		query = "SELECT COUNT(*) FROM connections WHERE user_id_two = ? AND status='PENDING'";
		
		try {
			pst = conn.prepareCall(query);
			pst.setInt(1, sessionUserId);
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
	
	
	public void saveConnection(int sessionUserId, int userId, Connection conn){
		
		if (checkIfConnected(sessionUserId, userId, conn)!=0){
			if (getConnectionStatus(sessionUserId, userId, conn)=="ACTIVE"){
				deleteConnection(sessionUserId, userId, conn);
			}
			else{
				acceptConnection(sessionUserId, userId, conn);
			}
		}
		else{
			query = "INSERT INTO connections (user_id_one, user_id_two, status) VALUES (?,?,?)";
			
			try {
				pst = conn.prepareCall(query);
				pst.setInt(1, sessionUserId);
				pst.setInt(2, userId);
				pst.setString(3, "PENDING");
				pst.executeUpdate();
				int newid = Statement.RETURN_GENERATED_KEYS;
				
				createHistory("{ message:'add friend', user_id:" + sessionUserId + ", reference_id:" + newid + " }", sessionUserId, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
	
	public void acceptConnection(int connectionId, int sessionUserId, Connection conn){
		query = "UPDATE connections SET status = 'ACTIVE' WHERE id = ?";
		
		try {
			pst = conn.prepareCall(query);
			pst.setInt(1, connectionId);
			pst.executeUpdate();
			
			createHistory("{ message:'accepted friend', user_id:" + sessionUserId + ", reference_id:" + connectionId + " }", sessionUserId, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteConnection(int connectionId, int sessionUserId, Connection conn){
		query = "UPDATE connections SET status = 'INACTIVE' WHERE id = ?";
		
		try {
			pst = conn.prepareCall(query);
			pst.setInt(1, connectionId);
			pst.executeUpdate();
			
			createHistory("{ message:'deleted friend', user_id:" + sessionUserId + ", reference_id:" + connectionId + " }", sessionUserId, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
