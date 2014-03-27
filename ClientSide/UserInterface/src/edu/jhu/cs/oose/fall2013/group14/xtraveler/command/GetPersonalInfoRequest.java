package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONException;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
/**
 * Called when Client side needs personal information from Server
 * @author angeld
 */
public class GetPersonalInfoRequest implements Request {

	private JSONObject obj;
	private String sessionkey;
	/**
	 * Constructor
	 * @param _sessionkey user's unique session key
	 */
	public GetPersonalInfoRequest(String _sessionkey) {
		sessionkey = _sessionkey;
		obj = new JSONObject();
		try {
			obj.put(JsonKeys.PURPOSE, JsonKeyValues.GET_PERSONAL_INFO);
			obj.put(JsonKeys.FROM_PAGE, JsonKeyValues.MAIN_PAGE);
			obj.put(JsonKeys.SESSION_KEY, sessionkey);
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
