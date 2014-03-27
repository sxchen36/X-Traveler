package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
/**
 * Request class which is called when user wants to 
 * add a place into his visited list. Called by Attraction.java class
 * @author angeld
 */
public class AddtoVisitedListRequest implements Request {
	String sessionkey;
	String attraction;
	JSONObject obj;
	/**
	 * Constructor
	 * @param sessionkey user's unique sessionkey from server
	 * @param string Attraction's name
	 */
	public AddtoVisitedListRequest(String sessionkey, String string) {
		this.sessionkey = sessionkey;
		attraction = string;
		obj = new JSONObject();
		
		try {
			obj.put(JsonKeys.SESSION_KEY, this.sessionkey);
			obj.put(JsonKeys.PLACE_NAME, this.attraction);
			obj.put(JsonKeys.PURPOSE,JsonKeyValues.ADD_TO_VISITED);
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
