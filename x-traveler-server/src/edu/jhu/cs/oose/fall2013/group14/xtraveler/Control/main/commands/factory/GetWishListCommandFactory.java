package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.GetWishListCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * Generates a get wish list command
 * 
 * @version java7
 * @author iamrick86
 * 
 */
public class GetWishListCommandFactory implements CommandFactory {
	/**
	 * @return get wish list command
	 */
	public String getCommandName() {
		return JsonKeyValues.GET_WISH_LIST;
	}

	public Command makeCommand(JSONObject j) {
		return new GetWishListCommand(j);
	}
}
