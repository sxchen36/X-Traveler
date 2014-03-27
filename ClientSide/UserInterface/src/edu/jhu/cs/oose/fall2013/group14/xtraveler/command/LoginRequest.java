package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONException;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
/**
 * Called when user require to login to the app. 
 * Send from Login Page to Server
 */
public class LoginRequest implements Request {

	private JSONObject obj;
	private String username;
	private String password;
	/**
	 * Constructor
	 * @param name username
	 * @param pwd user password
	 */
	public LoginRequest(String name, String pwd){
		obj = new JSONObject();
		username = name;
		password = pwd;
		try {
			obj.put(JsonKeys.PURPOSE, JsonKeyValues.LOGIN);
			obj.put(JsonKeys.FROM_PAGE,JsonKeyValues.LOGIN_ACTIVITY);
			obj.put(JsonKeys.CURRENT_USER, username);
			obj.put(JsonKeys.PASSWORD, password);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public JSONObject getJSON() {
		// TODO Auto-generated method stub
		return obj;
	}

}
