package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * 
 * Lists the user's wish place list
 * 
 * @author iamrick86
 * @version java7
 */
public class GetWishListCommand extends SessionCommand {

	/**
	 * 
	 * @param j
	 *            sent from client side
	 */
	public GetWishListCommand(JSONObject j) {
		super(j);
	}

	/**
	 * @param context
	 *            necessary execution environment needed for this command
	 * @return getWishListCommandResponse send back to client side
	 */
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {

		PlaceDAO placedao = context.getPlaceDAO();
		JSONObject getWishListCommandResponse = new JSONObject();
		if (super.executeAuthenticated()) {
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			User user = usersf.getUserSession(sessionkey).getUser();
			// it should be in transaction
			String username = user.getUserName();
			// String username = userdao.getName(user);

			List<String> placenames = placedao.listWishList(username);
			System.out.println(placenames.toString());

			try {
				getWishListCommandResponse.put(JsonKeys.RES_WISH_LIST,
						placenames);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return getWishListCommandResponse;
		} else {

			try {
				getWishListCommandResponse.put(JsonKeys.RES_WISH_LIST,
						JsonKeyValues.GET_WISH_FAIL);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return getWishListCommandResponse;
		}

	}
}