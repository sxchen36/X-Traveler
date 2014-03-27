package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
/**
 * Request class which is called when 
 * Menu class need information of a specific City from Server
 * @author angeld
 *
 */
public class GetCityRequest implements Request{
	private JSONObject obj;
	private String sessionkey;
	private String state;
	private String city;
	/**
	 * Wrap request with a json
	 * @param username user session key get from server
	 * @param state the state user wants to search
	 * @param city the city user wants to search
	 */
	public GetCityRequest(String username, String state, String city){
		obj = new JSONObject();
		this.sessionkey = username;
		this.state = state;
		this.city = city;

		try {
			obj.put(JsonKeys.PURPOSE, JsonKeyValues.GET_CITY_ATTRACTION);
			obj.put(JsonKeys.SESSION_KEY, sessionkey);
			obj.put(JsonKeys.STATE, this.state);
			obj.put(JsonKeys.CITY, this.city);
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
