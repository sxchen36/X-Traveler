package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;

/**
 * 
 * This will show us the user with similar wish travel place. This class is also
 * not finished yet. I have added it for future extension.
 * 
 * @author iamrick86
 * @version java7
 */
public class ShowCommonWishUser extends SessionCommand {

	public ShowCommonWishUser(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject showC = new JSONObject();
		if (super.executeAuthenticated()) {

		} else {

		}
		// TODO Auto-generated method stub
		return showC;
	}

}
