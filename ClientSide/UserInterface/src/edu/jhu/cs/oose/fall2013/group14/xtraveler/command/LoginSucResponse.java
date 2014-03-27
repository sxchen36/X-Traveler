package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

import android.content.Context;
import android.content.Intent;
/**
 * Called when ready to switch to main page
 * @author angeld
 *
 */
public class LoginSucResponse implements Response{
	private String username;
	private JSONObject obj;
	/**
	 * Constructor
	 * @param json JSON information needed to pass to next page
	 */
	public LoginSucResponse(JSONObject json){
		obj = json;
	}
	/**
	 * Switch to next page
	 * @param from from Activity.this
	 * @param to to Activity.class
	 */
	public void run(Context from, Class to){
		/* Switch to next page */
	    Intent intent = new Intent();
	    intent.setClass(from, to);
	    intent.putExtra(JsonKeys.INFO_BETWEEN_PAGE,obj.toString());
	    from.startActivity(intent);  
	}
}
