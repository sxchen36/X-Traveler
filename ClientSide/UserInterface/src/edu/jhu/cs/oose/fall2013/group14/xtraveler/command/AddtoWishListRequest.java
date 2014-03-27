package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
/**
 * Request class which is called when user wants to 
 * add a place into his wish list. Called by Attraction.java class
 * @author angeld
 */
public class AddtoWishListRequest implements Request {
	String sessionkey;
	String attraction;
	JSONObject obj;
	/**
	 * Constructor
	 * @param sessionkey user's unique sessionkey from server
	 * @param string Attraction's name
	 */
	public AddtoWishListRequest(String sessionkey, String string) {
		// TODO Auto-generated constructor stub
		obj = new JSONObject();
		this.sessionkey = sessionkey;
		attraction = string;
		try{
			obj.put(JsonKeys.PURPOSE, JsonKeyValues.ADD_TO_WISHLIST);
			obj.put(JsonKeys.SESSION_KEY, this.sessionkey);
			obj.put(JsonKeys.PLACE_NAME, attraction);
		} catch(JSONException e){
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getJSON() {
		// TODO Auto-generated method stub
		return obj;
	}
	

}
