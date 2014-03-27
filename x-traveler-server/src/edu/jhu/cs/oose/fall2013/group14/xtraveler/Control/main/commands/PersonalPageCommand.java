package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * Load the personal page for client, provide the wish list, visited list and
 * travel plan of the user
 * 
 * @author iamrick86
 * @version java7
 */
public class PersonalPageCommand extends SessionCommand {

	private GetWishListCommand getWishListCommand;
	private GetVisitedListCommand getVisitedListCommand;

	// we still need a GetTravelPlan member

	public PersonalPageCommand(JSONObject j) {
		super(j);

		getWishListCommand = new GetWishListCommand(j);
		getVisitedListCommand = new GetVisitedListCommand(j);

	}

	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject personalPageCommandResponse = new JSONObject();

		if (super.executeAuthenticated()) {

			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			User user = usersf.getUserSession(sessionkey).getUser();
			String username = user.getUserName();

			personalPageCommandResponse.put(JsonKeys.RES_WISH_LIST,
					getWishListCommand.execute(context));
			personalPageCommandResponse.put(JsonKeys.RES_VISITED_LIST,
					getVisitedListCommand.execute(context));
			personalPageCommandResponse.put(JsonKeys.RES_PERSONAL_INFO,
					username);
			personalPageCommandResponse.put(JsonKeys.RES_PERSONAL_PAGE,
					JsonKeyValues.PERSONAL_PAGE_SUC);

		} else {
			personalPageCommandResponse.put(JsonKeys.RES_PERSONAL_PAGE,
					JsonKeyValues.PERSONAL_PAGE_FAIL);
		}
		return personalPageCommandResponse;
	}
}
