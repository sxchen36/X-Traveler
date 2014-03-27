package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;



import android.content.Context;
import android.content.Intent;
/**
 * Called when get correct response from Server
 * Ready to switch to next page
 * @author angeld
 *
 */
public class GetAttractionResponse implements Response{
	private JSONObject obj;
	
	/**
	 * Constructor
	 * @param json JSON need to pass to next page
	 */
	public GetAttractionResponse(JSONObject json){
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
