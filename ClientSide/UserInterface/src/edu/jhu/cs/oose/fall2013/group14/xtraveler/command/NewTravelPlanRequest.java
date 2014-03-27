package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONException;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
/**
 * Called when user asks to create a new travel plan
 * Send from Personal Page to Server when "New Plan" button is clicked
 * @author angeld
 *
 */
public class NewTravelPlanRequest implements Request {

	private JSONObject obj;
	private String sessionkey;
	private String plan_name;
	private String number_people;
	private String days;
	private String des;
	private String budget;

	

	/**
	 * Constructor
	 * @param _sessionkey user's unique session key
	 * @param plan_name travel plan's name
	 * @param numberPeople number of people
	 * @param day number of days
	 * @param Budget number of budget
	 * @param des Other descriptions
	 */
	public NewTravelPlanRequest(String _sessionkey, String plan_name,String numberPeople, 
			String day,  String Budget, String des) {
		sessionkey = _sessionkey;
		this.plan_name = plan_name;
		this.number_people = numberPeople;
		this.days= day;
		this.des = des;
		this.budget = Budget;
		
		obj = new JSONObject();
		
		try {
			obj.put(JsonKeys.PURPOSE, JsonKeyValues.WRITE_YOUR_PLAN);
			//obj.put(JsonKeys.FROM_PAGE, JsonKeyValues.);
			obj.put(JsonKeys.SESSION_KEY, sessionkey);
			obj.put(JsonKeys.PLAN_NAME, this.plan_name);
			obj.put(JsonKeys.PLAN_PEOPLE, this.number_people);
			obj.put(JsonKeys.PLAN_DAYS, this.days);
			obj.put(JsonKeys.PLAN_BUDGET, this.budget);
			obj.put(JsonKeys.PLAN_DESCRIPTION, this.des);
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
