package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;
import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
/**
 * Request class which is called before switch to Attraction class
 * @author angeld
 *
 */
public class GetAttractionRequest implements Request{
	private JSONObject obj;
	private String sessionkey;
	private String city;
	private String place;
	/**
	 * Constructor
	 * @param sessionkey user's unique sessionkey from server
	 * @param city City name
	 * @param place Attraction name
	 */
	public GetAttractionRequest(String sessionkey, String city, String place){
		obj = new JSONObject();
		this.sessionkey = sessionkey;
		this.city = city;
		this.place = place;
		try{
			obj.put(JsonKeys.PURPOSE, JsonKeyValues.GET_PLACE_DETAILS);
			obj.put(JsonKeys.SESSION_KEY, this.sessionkey);
			obj.put(JsonKeys.CITY, this.city);
			obj.put(JsonKeys.PLACE_NAME, this.place);
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
