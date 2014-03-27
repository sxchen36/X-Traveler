package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;

/**
 * List the user with similar travel interest. This is not finished yet, we will
 * extend it for feature use.
 * 
 * @author iamrick86
 * @version java7
 */
public class ShowCommonInterestUser extends SessionCommand {

	public ShowCommonInterestUser(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		// TODO Auto-generated method stub
		JSONObject res = new JSONObject();
		if (super.executeAuthenticated()) {

		} else {

		}
		return res;
	}

}
