package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * 
 * List the places with the similar feature of other places.
 * 
 * @author iamrick86
 * @version java7
 */
public class ShowCommonFeaturePlace extends SessionCommand {

	public ShowCommonFeaturePlace(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject commonR = new JSONObject();
		if (super.executeAuthenticated()) {
			// TODO Auto-generated method stub
			UserDAO userdao = context.getUserDAO();
			String username = j.getString(JsonKeys.CURRENT_USER);
			List<String> places = userdao.threeAttraction(username);

			commonR.put(JsonKeys.RES_SHOW_COMMON_VISITED_PLACE, places);
		}

		else {

			commonR.put(JsonKeys.RES_SHOW_COMMON_VISITED_PLACE,
					JsonKeyValues.LIST_FEATURE_PLACE_FAIL);

		}

		return commonR;
	}

}
