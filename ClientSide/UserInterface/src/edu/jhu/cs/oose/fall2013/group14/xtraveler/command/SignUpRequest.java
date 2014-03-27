package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
/**
 * Called when a new user filled the form, ready to sign up in the Serverside
 * @author angeld
 *
 */
public class SignUpRequest implements Request {

	private JSONObject obj;
	private String username;
	private String password,sex,age,email;
	/**
	 * Constructor
	 * @param name username
	 * @param pwd password
	 * @param sex gender
	 * @param age age
	 * @param email email address
	 */
	public SignUpRequest(String name, String pwd, String sex, String age, String email){
		obj = new JSONObject();
		username = name;
		password = pwd;
		this.sex = sex;
		this.age = age;
		this.email = email;

		try {
			obj.put(JsonKeys.PURPOSE, JsonKeyValues.SIGNUP);
			obj.put(JsonKeys.FROM_PAGE,JsonKeyValues.LOGIN_ACTIVITY);
			obj.put(JsonKeys.CURRENT_USER, username);
			obj.put(JsonKeys.PASSWORD, password);
			obj.put(JsonKeys.AGE, this.age);
			obj.put(JsonKeys.EMAIL, this.email);
			obj.put(JsonKeys.SEX, this.sex);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getJSON() {
		// TODO Auto-generated method stub
		return obj;
	}

}
