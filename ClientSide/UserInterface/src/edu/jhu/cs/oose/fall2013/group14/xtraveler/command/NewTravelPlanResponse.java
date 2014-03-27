package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

import android.content.Context;
import android.content.Intent;
/**
 * Called when ready to switch to TravelPlanView page
 * @author angeld
 */
public class NewTravelPlanResponse implements Response{
	private JSONObject obj;
	
	/**
	 * Constructor
	 * @param json JSON need to pass to next page
	 */
	public NewTravelPlanResponse(JSONObject json){
		obj = json;
	}
	@Override
	public void run(Context from, Class to){
		/* Switch to next page */
		Intent intent = new Intent();
		intent.putExtra(JsonKeys.INFO_BETWEEN_PAGE,obj.toString());
		intent.setClass(from, to);
		from.startActivity(intent);   
	}
}
