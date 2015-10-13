package com.group3.faceit.model.newsfeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommentModel {
	private String fullname;
	private String datecreated;
	private String description;
	private int commentid;
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(String datecreated) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date past = null;
		try {
			past = format.parse(datecreated);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date now = new Date();
		String ago = "";
		
		long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
		if (minutes < 60){
			ago = minutes + " minutes ago";
		}
		else if(minutes >= 60 && minutes < 1440){
			ago = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago";
		}
		else if(minutes >= 1440 && minutes < 10080){
			ago = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago";
		}
		else{
			ago = format.format(past);
		}
		
		this.datecreated = ago;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

}
