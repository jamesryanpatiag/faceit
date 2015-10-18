package com.group3.faceit.common;
import com.group3.faceit.model.user.*;
import java.util.ArrayList;
import java.util.List;

public class StringRefractor {

	public String convertUserModelToJSON(List<UserModel> userModels){
		String jsonVal = "";
		jsonVal = "[";
		int counter = 1;
		for(UserModel userModel : userModels){
			jsonVal += createJSONRow(userModel);
			if(!(userModels.size() == counter)){
				jsonVal += ",";
			}
			counter++;
		}
		jsonVal += "]";
		return jsonVal;
	}
	
	private String createJSONRow(UserModel userModel){		
		return "{ \"user_id\" : \"" + userModel.getUser_id() + "\", \"fullname\": \"" + userModel.getFirstname() + " " + userModel.getMiddlename() + " " + userModel.getLastname() + "\" }";
	}
	
}
